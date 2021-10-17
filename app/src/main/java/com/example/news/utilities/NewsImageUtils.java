package com.example.news.utilities;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.news.R;

public class NewsImageUtils {
    public static void setImage(String imageUrl, ImageView imageView, Context context) {
        if (imageUrl == null) {
            imageView.setImageResource(R.drawable.news);
        } else {
            Glide.with(context).load(imageUrl).into(imageView);
        }
    }
}
