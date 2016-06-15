package com.example.bogdan.testtest.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.view.View;

import com.example.bogdan.testtest.App;
import com.example.bogdan.testtest.Constants;
import com.example.bogdan.testtest.utils.ImageUtils;
import com.example.bogdan.testtest.R;
import com.example.bogdan.testtest.di.NewsPageModule;
import com.example.bogdan.testtest.presenter.NewsPagePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 10.06.16
 */
public class NewsActivity extends AppCompatActivity implements NewsPageView, View.OnClickListener{
    private static final int LAYOUT = R.layout.news_layout;
    private static final int BACK_BTN_ID = 1;

    @BindView(R.id.newsContainer) RelativeLayout container;
    @BindView(R.id.newsListView) ListView listView;
    @BindView(R.id.newsBest) ImageView newsBest;

    private NewsAdapter adapter;

    @Inject
    NewsPagePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().plus(new NewsPageModule(this)).inject(this);
        setContentView(LAYOUT);
        ButterKnife.bind(this);
        setupComponent();

        adapter = new NewsAdapter(this);
        listView.setAdapter(adapter);
        presenter.onCreate(savedInstanceState);
    }

    private void setupComponent() {
        Resizer.into(this);

        setBackBtn();
        container.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtils.decodeBitmap(this, R.drawable.news)));

        Resizer.setPosition(listView, Constants.NEWS.L_MARGIN.LIST_VIEW, Constants.NEWS.T_MARGIN.LIST_VIEW);

        Resizer.configureView(newsBest,
                Constants.NEWS.WIDTH.NEWS_BEST,
                Constants.NEWS.HEIGHT.NEWS_BEST);
        Resizer.setPosition(newsBest,
                Constants.NEWS.L_MARGIN.NEWS_BEST,
                Constants.NEWS.T_MARGIN.NEWS_BEST);
        newsBest.setImageBitmap(ImageUtils.decodeBitmap(this, R.drawable.news_best));
    }

    private void setBackBtn() {
        Resizer.into(this);
        View view = new View(this);
        view.setId(BACK_BTN_ID);
        Resizer.configureView(view,
                Constants.NEWS.WIDTH.BACK_BTN,
                Constants.NEWS.HEIGHT.BACK_BTN);
        container.addView(view);
        view.setOnClickListener(this);
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
            adapter.addNewsItem(newsItem);
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
