package com.example.bogdan.testtest.di;

import android.app.Application;

import com.example.bogdan.testtest.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
@Module
public class AppModule {
    private App mApplication;

    public AppModule(App application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    Application provideAppication() {
        return mApplication;
    }

}
