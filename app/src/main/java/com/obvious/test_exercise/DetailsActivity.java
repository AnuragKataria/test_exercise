package com.obvious.test_exercise;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.obvious.test_exercise.adapters.DetailViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private DetailViewAdapter detailViewAdapter;
    private Context context;
    private int position;
    private ArrayList<HashMap<String, String>> imagesArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        context = this;
        imagesArrayList = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("imageList");
        position = getIntent().getIntExtra("position", 0);

        detailViewAdapter = new DetailViewAdapter(imagesArrayList, context);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(detailViewAdapter);
        viewPager.setCurrentItem(position);
        //viewPager.setPadding(130, 0, 130, 0);

    }
}
