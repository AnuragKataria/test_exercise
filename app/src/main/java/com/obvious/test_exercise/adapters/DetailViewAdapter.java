package com.obvious.test_exercise.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.obvious.test_exercise.DetailsActivity;
import com.obvious.test_exercise.R;
import com.obvious.test_exercise.utils.ZoomView;

import java.util.ArrayList;
import java.util.HashMap;

import it.mike5v.viewmoretextview.ViewMoreTextView;


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
        View view = layoutInflater.inflate(R.layout.detail_row_item, container, false);
        ZoomView imageView = view.findViewById(R.id.image);
        TextView titleTV = view.findViewById(R.id.titleTV);
        ViewMoreTextView viewMoreTV = view.findViewById(R.id.viewMoreTV);
        titleTV.setText(imagesArrayList.get(position).get("title"));
        // append See less for collapsing if its OPened
        viewMoreTV.setText(imagesArrayList.get(position).get("explanation") + " See Less");
        // View more text functionality
        viewMoreTV.setAnimationDuration(500)
                .setEllipsizedText("View More")
                .setVisibleLines(3)
                .setIsExpanded(false)
                .setEllipsizedTextColor(ContextCompat.getColor(((DetailsActivity) context), android.R.color.holo_blue_dark));
        viewMoreTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMoreTV.toggle();
            }
        });
        // saving img url in string variable
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
