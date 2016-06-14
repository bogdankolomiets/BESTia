package com.example.bogdan.testtest.di;

import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.presenter.NewsPagePresenter;
import com.example.bogdan.testtest.presenter.NewsPagePresenterImpl;
import com.example.bogdan.testtest.view.NewsAdapter;
import com.example.bogdan.testtest.view.NewsPageView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 10.06.16
 */
@Module
public class NewsPageModule {
    private NewsPageView mView;

    public NewsPageModule(NewsPageView view) {
        mView = view;
    }

    @Provides
    @ActivityScope
    public NewsPagePresenter provideNewsPagePresenter(BestiaModel model) {
        return new NewsPagePresenterImpl(model, mView);
    }
}
