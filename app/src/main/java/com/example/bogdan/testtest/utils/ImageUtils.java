package com.example.bogdan.testtest.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 10.06.16
 */
public class ImageUtils {

    private ImageUtils() {};

    public static Bitmap decodeBitmap(Context context, int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId, getOptions());
    }

    public static Bitmap decodeBitmap(Context context, int resId, int width, int height) {
        Bitmap bitmap = decodeBitmap(context, resId);
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    public static Bitmap decodeBitmap(byte[] decodeArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodeArray, 0, decodeArray.length, getOptions());
        return bitmap;
    }

    public static Bitmap decodeStreamBitmap(InputStream in) {
        Bitmap bitmap = BitmapFactory.decodeStream(in, null, getOptions());
        return bitmap;
    }

    private static BitmapFactory.Options getOptions() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        return options;
    }



}
