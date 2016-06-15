package com.example.bogdan.testtest.di.component;

import com.example.bogdan.testtest.di.module.AppModule;
import com.example.bogdan.testtest.di.module.MainPageModule;
import com.example.bogdan.testtest.di.module.NewsPageModule;

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

    NewsPageComponent plus(NewsPageModule module);
}
