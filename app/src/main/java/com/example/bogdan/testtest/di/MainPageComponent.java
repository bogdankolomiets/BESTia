package com.example.bogdan.testtest.di;

import com.example.bogdan.testtest.view.MainActivity;

import dagger.Subcomponent;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 07.06.16
 */
@ActivityScope
@Subcomponent(modules = MainPageModule.class)
public interface MainPageComponent {
    void inject(MainActivity mainActivity);
}
