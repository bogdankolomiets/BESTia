package com.example.bogdan.testtest;

import android.app.Application;

import com.example.bogdan.testtest.di.component.AppComponent;
import com.example.bogdan.testtest.di.module.AppModule;
import com.example.bogdan.testtest.di.component.DaggerAppComponent;

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
        System.out.println(mAppComponent);
    }

    private void resolveDependencies() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
