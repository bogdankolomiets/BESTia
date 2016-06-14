package com.example.bogdan.testtest.view;

import android.graphics.Bitmap;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 14.06.16
 */
public class NewsImage {
    private Bitmap mPoster;
    private Bitmap mFrame;
    private Bitmap mHeader;

    public NewsImage(Bitmap poster, Bitmap frame, Bitmap header) {
        mPoster = poster;
        mHeader = frame;
        mFrame = frame;
    }

    public Bitmap getmPoster() {
        return mPoster;
    }

    public Bitmap getmFrame() {
        return mFrame;
    }

    public Bitmap getmHeader() {
        return mHeader;
    }
}
