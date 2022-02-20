package com.obvious.test_exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.obvious.test_exercise.adapters.ImagesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
        recyclerView.setLayoutManager(gridLayoutManager);

        try {
            String jsonString = loadJSONFromAsset();
            JSONArray jsonArray = new JSONArray(jsonString);
             imagesArrayList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                HashMap<String, String> imagesHashMap = new HashMap<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String copyright="NA";
                if(jsonObject.has("copyright")){
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
            ImagesAdapter imagesAdapter = new ImagesAdapter(context, imagesArrayList);
            recyclerView.setAdapter(imagesAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void startNewActivity(int adapterPosition, View imageView) {
        Toast.makeText(context, adapterPosition+ "", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("imageList", imagesArrayList);
        intent.putExtra("position", adapterPosition);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, (View)imageView, "animation");
        context.startActivity(intent);
    }
}