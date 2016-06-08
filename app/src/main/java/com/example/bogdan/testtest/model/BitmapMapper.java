package com.example.bogdan.testtest.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 07.06.16
 */
public class BitmapMapper implements Func1<List<String>, List<Bitmap>> {

    @Override
    public List<Bitmap> call(List<String> strings) {
        if (strings == null)
            return null;
        List<Bitmap> bitmapList = Observable.from(strings)
                .map(s -> {
                    byte[] decode = Base64.decode(s.getBytes(), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                    System.out.println("Bitmap height " + bitmap.getHeight() + "   " + bitmap.getWidth());
                    return bitmap;
                })
                .toList()
                .toBlocking()
                .first();
        return bitmapList;
    }
}
