package com.example.bogdan.testtest.presenter;

import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.model.BitmapMapper;
import com.example.bogdan.testtest.view.View;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public abstract class BasePresenter {

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    protected final BestiaModel mBestiaModel;
    protected final BitmapMapper mapper;

    public BasePresenter(BestiaModel bestiaModel, BitmapMapper mapper) {
        mBestiaModel = bestiaModel;
        this.mapper = mapper;
    }

    protected void addSubscription(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    public void onStop() {
        mCompositeSubscription.clear();
    }

    protected abstract View getView();

    protected void onErr(Throwable e) {
        getView().showError(e.getMessage());
    }

}
