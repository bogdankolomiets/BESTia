package com.example.bogdan.testtest.presenter;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.model.BitmapMapper;
import com.example.bogdan.testtest.view.MainPageView;
import com.example.bogdan.testtest.view.View;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.functions.Func1;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public class MainPagePresenterImpl extends BasePresenter implements MainPagePresenter {
    private MainPageView mView;
    private BitmapMapper mapper = new BitmapMapper();

    @Inject
    public MainPagePresenterImpl(BestiaModel bestiaModel, MainPageView view) {
        super(bestiaModel);
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

                    }

                    @Override
                    public void onNext(List<Bitmap> bitmaps) {
                        if (bitmaps != null) {
                            mView.showPoster(bitmaps);
                        }
                    }
                });
    }
}
