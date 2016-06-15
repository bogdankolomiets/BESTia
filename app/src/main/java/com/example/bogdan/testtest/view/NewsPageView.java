package com.example.bogdan.testtest.view;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public interface NewsPageView extends View{
    int BACK_BTN_ID = 1;

    void showNews(List<Bitmap> newsList);

    Context getContext();

    void showMainPage();

    void showLoad();

    void hideLoad();
}
