package com.example.bogdan.testtest.presenter;

import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.view.NewsPageView;
import com.example.bogdan.testtest.view.View;

import javax.inject.Inject;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public class NewsPagePresenterImpl extends BasePresenter implements NewsPagePresenter {
    private NewsPageView mView;

    @Inject
    public NewsPagePresenterImpl(BestiaModel bestiaModel, NewsPageView view) {
        super(bestiaModel);
        mView = view;
    }

    @Override
    protected View getView() {
        return mView;
    }
}
