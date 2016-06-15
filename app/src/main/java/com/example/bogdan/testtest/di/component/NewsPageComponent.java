package com.example.bogdan.testtest.di.component;

import com.example.bogdan.testtest.di.ActivityScope;
import com.example.bogdan.testtest.di.module.NewsPageModule;
import com.example.bogdan.testtest.view.NewsActivity;

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
