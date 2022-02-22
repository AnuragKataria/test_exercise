package com.obvious.test_exercise;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.obvious.test_exercise.adapters.DetailViewAdapter;
import com.obvious.test_exercise.model.DataModel;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Context context;
    private int position;
    private ArrayList<DataModel> imagesArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        context = this;
        imagesArrayList = (ArrayList<DataModel>) getIntent().getSerializableExtra("imageList");
        position = getIntent().getIntExtra("position", 0);
        //init Viewpager Adapter
        DetailViewAdapter detailViewAdapter = new DetailViewAdapter(imagesArrayList, context);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(detailViewAdapter);
        viewPager.setCurrentItem(position);
    }
}
