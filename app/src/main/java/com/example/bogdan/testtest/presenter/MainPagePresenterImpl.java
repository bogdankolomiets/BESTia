package com.example.bogdan.testtest.presenter;

import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.view.MainPageView;
import com.example.bogdan.testtest.view.View;

import javax.inject.Inject;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public class MainPagePresenterImpl extends BasePresenter implements MainPagePresenter {
    private MainPageView mView;

    @Inject
    public MainPagePresenterImpl(BestiaModel bestiaModel, MainPageView view) {
        super(bestiaModel);
        mView = view;
    }

    @Override
    protected View getView() {
        return mView;
    }
}
