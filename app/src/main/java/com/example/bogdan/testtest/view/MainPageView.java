package com.example.bogdan.testtest.view;

import android.graphics.Bitmap;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public interface MainPageView extends View{
    int FACEBOOK = 1;
    int TWITTER = 2;
    int GOOGLE = 3;
    int INSTAGRAM = 4;
    int NEWS = 5;

    void showPoster(List<Bitmap> posters);

    void showNews();

    void openSocialPage(String url);
}
