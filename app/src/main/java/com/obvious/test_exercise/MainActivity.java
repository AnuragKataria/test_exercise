package com.obvious.test_exercise;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.obvious.test_exercise.adapters.ImagesAdapter;
import com.obvious.test_exercise.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Context context;
    private ArrayList<HashMap<String, String>> imagesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        // setting layout manager with 3 cells in a row on recyclerView
        recyclerView.setLayoutManager(gridLayoutManager);
        try {
            String jsonString = Util.loadJSONFromAsset(context);
            JSONArray jsonArray = new JSONArray(jsonString);
            imagesArrayList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                HashMap<String, String> imagesHashMap = new HashMap<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String copyright = "NA";
                if (jsonObject.has("copyright")) {
                    copyright = jsonObject.getString("copyright");

                }
                String date = jsonObject.getString("date");
                String explanation = jsonObject.getString("explanation");
                String hdurl = jsonObject.getString("hdurl");
                String media_type = jsonObject.getString("media_type");
                String service_version = jsonObject.getString("service_version");
                String title = jsonObject.getString("title");
                String url = jsonObject.getString("url");

                imagesHashMap.put("copyright", copyright);
                imagesHashMap.put("date", date);
                imagesHashMap.put("explanation", explanation);
                imagesHashMap.put("hdurl", hdurl);
                imagesHashMap.put("media_type", media_type);
                imagesHashMap.put("service_version", service_version);
                imagesHashMap.put("title", title);
                imagesHashMap.put("url", url);
                imagesArrayList.add(imagesHashMap);
            }
            // reversing the array to make latest entries come at first position
            Collections.reverse(imagesArrayList);
            // initalising adapter class
            ImagesAdapter imagesAdapter = new ImagesAdapter(context, imagesArrayList);
            //setting adapter on recycleview
            recyclerView.setAdapter(imagesAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // calling details activity here from #ImagesAdapter class
    public void startNewActivity(int adapterPosition, View imageView) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("imageList", imagesArrayList);
        intent.putExtra("position", adapterPosition);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, (View) imageView, "animation");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onBackPressed() {
        exitFromapp();

    }

    // App exit confirmation dialog
    private void exitFromapp() {
        new AlertDialog.Builder(this)
                .setMessage(context.getResources().getString(R.string.exit_confirmation))
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(context.getResources().getString(R.string.no), null)
                .show();
    }
}