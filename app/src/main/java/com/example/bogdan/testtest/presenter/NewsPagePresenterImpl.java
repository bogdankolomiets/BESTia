package com.example.bogdan.testtest.presenter;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.model.BitmapMapper;
import com.example.bogdan.testtest.view.NewsPageView;
import com.example.bogdan.testtest.view.View;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public class NewsPagePresenterImpl extends BasePresenter implements NewsPagePresenter {
    private NewsPageView mView;

    @Inject
    public NewsPagePresenterImpl(BestiaModel bestiaModel, BitmapMapper mapper, NewsPageView view) {
        super(bestiaModel, mapper);
        mView = view;
    }

    @Override
    protected View getView() {
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mView.showLoad();
        mBestiaModel.getNewsImageList()
                .map(mapper)
                .subscribe(new Observer<List<Bitmap>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoad();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoad();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Bitmap> bitmaps) {
                        if (bitmaps != null) {
                            mView.showNews(bitmaps);
                        }
                    }
                });
    }

    @Override
    public void onMainPageClick() {
        mView.showMainPage();
    }
}
