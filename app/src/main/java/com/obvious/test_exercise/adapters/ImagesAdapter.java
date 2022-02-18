package com.obvious.test_exercise.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.obvious.test_exercise.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private ArrayList<HashMap<String, String>> imagesArraylist;
    private Context context;

    public ImagesAdapter(Context context, ArrayList<HashMap<String, String>> imagesArraylist) {
        this.imagesArraylist = imagesArraylist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imageUrl = imagesArraylist.get(position).get("url");
        // Seting Image on ImageView
        Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return imagesArraylist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivImage);

        }
    }
}

