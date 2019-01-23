package com.project.transparentactivity;

import android.graphics.Bitmap;

public class ImageItem
{
    String image;
    String text;

    public ImageItem(String image, String text) {
        this.image = image;
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
