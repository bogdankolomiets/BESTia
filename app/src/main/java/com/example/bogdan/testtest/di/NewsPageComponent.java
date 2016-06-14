package com.example.bogdan.testtest.di;

import com.example.bogdan.testtest.view.NewsActivity;

import dagger.Component;
import dagger.Subcomponent;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 10.06.16
 */
@ActivityScope
@Subcomponent(modules = NewsPageModule.class)
public interface NewsPageComponent {
    void inject(NewsActivity newsActivity);
}
