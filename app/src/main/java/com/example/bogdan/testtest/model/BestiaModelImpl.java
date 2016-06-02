package com.example.bogdan.testtest.model;

import android.util.Base64;

import com.example.bogdan.testtest.App;
import com.example.bogdan.testtest.api.BestiaApi;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public class BestiaModelImpl implements BestiaModel {
    private final Observable.Transformer schedulersTransformer;

    @Inject
    BestiaApi apiInterface;

    public BestiaModelImpl() {
        App.getAppComponent().inject(this);
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<List<Base64>> getMainImageList() {
        return apiInterface
                .getMainImages()
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<Base64>> getNewsImageList() {
        return apiInterface
                .getNewsImages()
                .compose(applySchedulers());
    }

    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }


}
