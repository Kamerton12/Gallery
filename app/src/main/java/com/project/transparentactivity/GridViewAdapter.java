package com.project.transparentactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter
{

    private Context context;
    private int layoutResourceId;
    private ArrayList data;
    private View arr[];

    public GridViewAdapter(Context context, int resource, ArrayList data)
    {
        super(context, resource, data);
        this.context = context;
        layoutResourceId = resource;
        this.data = data;
        arr = new View[data.size()];
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        ViewHolder holder = null;

        if(arr[position] == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image = row.findViewById(R.id.imageView);
            holder.imageTitle = row.findViewById(R.id.textView);
            row.setTag(holder);
            arr[position] = row;
        }
        else
        {
            row = arr[position];
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = (ImageItem) data.get(position);
        Log.d("PATH2", "main");

        class Task extends AsyncTask<Object, Void, Void>
        {
            @Override
            protected Void doInBackground(Object... objs)
            {
                Log.d("PATH2", "background");
                Bitmap full = BitmapFactory.decodeFile((String)objs[0]);
                if(full == null)
                    return null;
                if(full.getHeight() > full.getWidth())
                {
                    int off = (full.getHeight() - full.getWidth()) / 2;
                    full = Bitmap.createBitmap(full, 0, off, full.getWidth(), full.getHeight() - 2 * off);
                }
                else
                {
                    int off = (full.getWidth() - full.getHeight()) / 2;
                    full = Bitmap.createBitmap(full, off, 0, full.getWidth() - 2 * off, full.getHeight());
                }
                int h;
                int w = h = dpToPx(100);
                Bitmap bmp = Bitmap.createScaledBitmap(full, w, h, false);
                new Handler(Looper.getMainLooper()).post(() -> ((ImageView)objs[1]).setImageBitmap(bmp));
                new Handler(Looper.getMainLooper()).post(() -> ((TextView)objs[3]).setText((String)objs[2]));
                return null;
            }
        }
        new Task().execute(item.image, holder.image, Integer.toString(position)/*item.text*/, holder.imageTitle);
        return row;
    }

    class ViewHolder
    {
        TextView imageTitle;
        ImageView image;
    }
}
