package com.example.bogdan.testtest.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bogdan.testtest.R;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 14.06.16
 */
public class MainActivityTest extends AppCompatActivity implements MainPageView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout_test);

    }

    @Override
    public void showPoster(List<Bitmap> posters) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
