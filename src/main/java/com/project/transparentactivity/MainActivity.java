package com.project.transparentactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridViewAdapter adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        adapter = new GridViewAdapter(this, R.layout.item_layout, getData());
        gridView.setAdapter(adapter);
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private ArrayList getData()
    {
        ArrayList<ImageItem> imageItems = new ArrayList<>();
        File mem = Environment.getExternalStorageDirectory();
        mem = new File(mem.getAbsolutePath() + "/DCIM/Camera");
        File d[] = mem.listFiles();
        int p = 0;
        for(File f: d)
        {
//            Bitmap full = BitmapFactory.decodeFile(f.getAbsolutePath());
//            if(full == null)
//                continue;
//
//            if(full.getHeight() > full.getWidth())
//            {
//                int off = (full.getHeight() - full.getWidth()) / 2;
//                full = Bitmap.createBitmap(full, 0, off, full.getWidth(), full.getHeight() - 2 * off);
//            }
//            else
//            {
//                int off = (full.getWidth() - full.getHeight()) / 2;
//                full = Bitmap.createBitmap(full, off, 0, full.getWidth() - 2 * off, full.getHeight());
//            }
//
//
//            int h;
//            int w = h = dpToPx(100);
//
//            Bitmap bmp = Bitmap.createScaledBitmap(full, w, h, false);

            imageItems.add(new ImageItem(f.getAbsolutePath(), Integer.toString(++p)));
            if(p > 30)
                break;
        }

        return imageItems;
    }
}
