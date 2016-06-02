package com.example.bogdan.testtest;

import android.app.Application;

import com.example.bogdan.testtest.di.ApiModule;
import com.example.bogdan.testtest.di.AppComponent;
import com.example.bogdan.testtest.di.AppModule;
import com.example.bogdan.testtest.di.DaggerAppComponent;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public class App extends Application {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        resolveDependencies();
    }

    private void resolveDependencies() {
        mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule(Constants.HTTP.BASE_URL))
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
