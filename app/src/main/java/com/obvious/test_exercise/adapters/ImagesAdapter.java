package com.obvious.test_exercise.adapters;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.obvious.test_exercise.MainActivity;
import com.obvious.test_exercise.R;
import com.obvious.test_exercise.model.DataModel;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private ArrayList<DataModel> imagesArraylist;
    private Context context;

    public ImagesAdapter(Context context, ArrayList<DataModel> imagesArraylist) {
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
        String imageUrl = imagesArraylist.get(position).getUrl();
        holder.loadingView.setVisibility(View.VISIBLE);
        // Seting Image on ImageView
        Glide.with(context)
                .load(imageUrl)
                .apply(new RequestOptions().error(R.mipmap.error).override(150, 150))
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.loadingView.setVisibility(View.GONE);
                        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        holder.imageView.setBackgroundColor(Color.parseColor("#000000"));
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.loadingView.setVisibility(View.GONE);
                        holder.imageView.setBackgroundColor(Color.parseColor("#000000"));
                        return false;
                    }
                })
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity) context).startNewActivity(holder.getAdapterPosition(), holder.imageView);
            }
        });

    }


    @Override
    public int getItemCount() {
        return imagesArraylist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public AVLoadingIndicatorView loadingView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivImage);
            loadingView = itemView.findViewById(R.id.loadingView);
        }
    }
}

