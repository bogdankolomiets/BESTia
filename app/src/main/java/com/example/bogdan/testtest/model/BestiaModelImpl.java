package com.example.bogdan.testtest.model;

import com.example.bogdan.testtest.api.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
@Singleton
public class BestiaModelImpl implements BestiaModel {
    private final Observable.Transformer mSchedulersTransformer;
    private final ApiHelper mApiInterface;

    @Inject
    public BestiaModelImpl(ApiHelper apiInterface, Observable.Transformer schedulersTransformer) {
        mApiInterface = apiInterface;
        mSchedulersTransformer = schedulersTransformer;
    }

    @Override
    public Observable<List<String>> getMainImageList() {
        return mApiInterface
                .getMainImages()
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<String>> getNewsImageList() {
        return mApiInterface
                .getNewsImages()
                .compose(applySchedulers());
    }

    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) mSchedulersTransformer;
    }


}
