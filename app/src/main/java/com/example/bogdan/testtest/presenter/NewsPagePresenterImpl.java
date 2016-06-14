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
    private BitmapMapper mapper = new BitmapMapper();
    @Inject
    public NewsPagePresenterImpl(BestiaModel bestiaModel, NewsPageView view) {
        super(bestiaModel);
        mView = view;
    }

    @Override
    protected View getView() {
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mBestiaModel.getNewsImageList()
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
                            mView.showNews(bitmaps);
                        }
                    }
                });
    }
}
