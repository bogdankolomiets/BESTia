package com.example.bogdan.testtest.di;

import com.example.bogdan.testtest.App;
import com.example.bogdan.testtest.api.BestiaApi;
import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.model.BestiaModelImpl;

import javax.inject.Singleton;

import dagger.Component;
import rx.Observable;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    Observable.Transformer schedulersTransformer();
    BestiaModel bestiaModel();

}
