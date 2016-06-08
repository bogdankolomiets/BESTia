package com.example.bogdan.testtest.model;

import android.util.Base64;

import java.util.List;

import rx.Observable;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
public interface BestiaModel {

    Observable<List<String>> getMainImageList();

    Observable<List<String>> getNewsImageList();
}
