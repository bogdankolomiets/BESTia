package com.example.bogdan.testtest.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    MainPageComponent plus(MainPageModule module);
}
