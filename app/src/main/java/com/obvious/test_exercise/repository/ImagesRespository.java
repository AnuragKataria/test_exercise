package com.obvious.test_exercise.repository;

import android.content.Context;

import com.obvious.test_exercise.model.DataModel;
import com.obvious.test_exercise.utils.Util;
import com.obvious.test_exercise.viewModel.ImageViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ImagesRespository {

    private ImageViewModel imageViewModel;

    public void getImagesArrayList(Context context) {
        try {
            String jsonString = Util.loadJSONFromAsset(context);
            JSONArray jsonArray = new JSONArray(jsonString);
            ArrayList<DataModel> imagesArrayList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {

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
                DataModel dataModel = new DataModel(copyright, date, explanation, hdurl, media_type, service_version, title, url);
                imagesArrayList.add(dataModel);
            }
            // reversing the array to make latest entries come at first position
            Collections.reverse(imagesArrayList);
            imageViewModel = (ImageViewModel) context;
            imageViewModel.getImagesArrayList(imagesArrayList);

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


}

