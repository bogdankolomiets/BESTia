package com.example.bogdan.testtest.presenter;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.bogdan.testtest.Constants;
import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.model.BitmapMapper;
import com.example.bogdan.testtest.view.MainPageView;
import com.example.bogdan.testtest.view.View;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public class MainPagePresenterImpl extends BasePresenter implements MainPagePresenter {
    private MainPageView mView;

    @Inject
    public MainPagePresenterImpl(BestiaModel bestiaModel, BitmapMapper mapper, MainPageView view) {
        super(bestiaModel, mapper);
        mView = view;
    }

    @Override
    protected View getView() {
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mBestiaModel
                .getMainImageList()
                .map(mapper)
                .subscribe(new Observer<List<Bitmap>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Bitmap> bitmaps) {
                        if (bitmaps != null) {
                            mView.showPoster(bitmaps);
                        }
                    }
                });
    }

    @Override
    public void onNewsClick() {
        mView.showNews();
    }

    @Override
    public void onSocialPageClick(int btnId) {
        switch (btnId) {
            case MainPageView.FACEBOOK:
                mView.openSocialPage(Constants.HTTP.FACEBOOK);
                break;
            case MainPageView.TWITTER:
                //do something
                break;
            case MainPageView.GOOGLE:
                mView.openSocialPage(Constants.HTTP.GOOGLE);
                break;
            case MainPageView.INSTAGRAM:
                mView.openSocialPage(Constants.HTTP.INSTAGRAM);
                break;

        }
    }
}
