package com.example.bogdan.testtest.view;

import android.graphics.Bitmap;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public interface NewsPageView extends View {

    void showNews(List<Bitmap> newsList);
}
