package com.example.bogdan.testtest.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.view.View;

import com.example.bogdan.testtest.App;
import com.example.bogdan.testtest.ImageUtils;
import com.example.bogdan.testtest.R;
import com.example.bogdan.testtest.di.NewsPageModule;
import com.example.bogdan.testtest.presenter.NewsPagePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 10.06.16
 */
public class NewsActivity extends AppCompatActivity implements NewsPageView, View.OnClickListener{
    private final int LAYOUT = R.layout.news_layout;
    private final int BACK_BTN_ID = 1;
    private ListView mNewsListView;
    private NewsAdapter mNewsAdapter;
    private ImageView newsBest;
    private RelativeLayout container;

    @Inject
    NewsPagePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().plus(new NewsPageModule(this)).inject(this);
        setContentView(LAYOUT);
        container = (RelativeLayout) findViewById(R.id.container);
        mNewsListView = (ListView) findViewById(R.id.newsListView);
        newsBest = (ImageView) findViewById(R.id.newsBest);
        setupComponent();
        mNewsAdapter = new NewsAdapter(this);
        mNewsListView.setAdapter(mNewsAdapter);
        presenter.onCreate(savedInstanceState);
    }

    private void setupComponent() {
        Resizer.into(this);
        View view = new View(this);
        view.setId(BACK_BTN_ID);
        Resizer.configureView(view, 112, 1334);
        container.addView(view);
        view.setOnClickListener(this);
        container.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtils.decodeBitmap(this, R.drawable.news)));
        Resizer.setPosition(mNewsListView, 112, 0, 0, 0);
        Resizer.configureView(newsBest, 234, 183);
        Resizer.setPosition(newsBest, 0, 18, 0, 0);
        newsBest.setImageBitmap(ImageUtils.decodeBitmap(this, R.drawable.news_best));
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

    @Override
    public void showNews(List<Bitmap> newsList) {
        for (Bitmap newsItem : newsList) {
            System.out.println("Size = " + newsItem.getByteCount());
            mNewsAdapter.addNewsItem(newsItem);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case BACK_BTN_ID:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewsActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        finish();
    }
}
