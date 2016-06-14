package com.example.bogdan.testtest.api;

import android.content.Context;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 07.06.16
 */
@Singleton
public class ApiHelper {
    private final Context mContext;

    @Inject
    public ApiHelper(Context context) {
        mContext = context;
    }

    public Observable<List<String>> getMainImages() {
        System.out.println("Главный поток" + (Looper.myLooper() == Looper.getMainLooper()));
        return Observable.from(getImageList("uk-main.json")).toList();
    }

    public Observable<List<String>> getNewsImages() {
        return Observable.from(getImageList("uk-news.json")).toList();
    }

    private List<String> getImageList(String fileName) {
        List<String> imageList;
        try {
            JSONObject obj = new JSONObject(loadJson(fileName));
            JSONArray jsonArray = obj.getJSONObject("nfo").getJSONArray("nws");
            imageList = new ArrayList<>(jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject image = jsonArray.getJSONObject(i);
                imageList.add(image.getString("pst"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return imageList;
    }

    private String loadJson(String name) {
        String json;
        try {
            InputStream inputStream = mContext.getAssets().open(name);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }
}
