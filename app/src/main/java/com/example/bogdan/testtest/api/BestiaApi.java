package com.example.bogdan.testtest.api;

import android.util.Base64;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public interface BestiaApi {

    @GET("uk-main.json")
    Observable<List<Base64>> getMainImages();

    @GET("uk-news.json")
    Observable<List<Base64>> getNewsImages();
}
