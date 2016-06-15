package com.example.bogdan.testtest.model;

import android.graphics.Bitmap;
import android.util.Base64;

import com.example.bogdan.testtest.utils.ImageUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 07.06.16
 */
public class BitmapMapper implements Func1<List<String>, List<Bitmap>> {

    @Inject
    public BitmapMapper() {

    }

    @Override
    public List<Bitmap> call(List<String> strings) {
        if (strings == null)
            return null;
        List<Bitmap> bitmapList = Observable.from(strings)
                .map(s -> {
                    return ImageUtils.decodeBitmap(Base64.decode(s.getBytes(), Base64.DEFAULT));
                })
                .toList()
                .toBlocking()
                .first();
        return bitmapList;
    }
}
