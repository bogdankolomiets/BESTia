package com.example.bogdan.testtest.di;

import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.presenter.MainPagePresenter;
import com.example.bogdan.testtest.presenter.MainPagePresenterImpl;
import com.example.bogdan.testtest.view.MainActivity;
import com.example.bogdan.testtest.view.MainPageView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 07.06.16
 */
@Module
public class MainPageModule {
    private MainPageView mMainPage;

    public MainPageModule(MainPageView mainPage) {
        mMainPage = mainPage;
    }

    @Provides
    @ActivityScope
    public MainPagePresenter provideMainPagePresenter(BestiaModel bestiaModel) {
        return new MainPagePresenterImpl(bestiaModel, mMainPage);
    }
}
