package com.example.bogdan.testtest.di;

import com.example.bogdan.testtest.App;
import com.example.bogdan.testtest.model.BestiaModelImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    void inject(BestiaModelImpl bestiaModel);
}
