package com.obvious.test_exercise.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.obvious.test_exercise.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailViewAdapter extends PagerAdapter {

    private ArrayList<HashMap<String, String>> imagesArrayList;
    private LayoutInflater layoutInflater;
    private Context context;

    public DetailViewAdapter(ArrayList<HashMap<String, String>> imagesArrayList, Context context) {
        this.imagesArrayList = imagesArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagesArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView = view.findViewById(R.id.image);
        TextView  title = view.findViewById(R.id.title);
        TextView  desc = view.findViewById(R.id.desc);
        title.setText(imagesArrayList.get(position).get("title"));
        desc.setText(imagesArrayList.get(position).get("explanation"));
        String imageUrl = imagesArrayList.get(position).get("url");
        // Seting Image on ImageView
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
