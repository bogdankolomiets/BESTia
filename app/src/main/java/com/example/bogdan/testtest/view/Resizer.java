package com.example.bogdan.testtest.view;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 13.06.16
 */
public class Resizer {
    private static float sCoeficient;

    private Resizer() {

    }

    private static float getCoeficient(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        return screenWidth / 750f;
    }

    public static void into(Activity activity) {
        sCoeficient = getCoeficient(activity);
    }

    public static <V extends View> void configureView(V view, int width, int height) {
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams((int)(sCoeficient * width), (int)(sCoeficient * height));

        view.setLayoutParams(params);
    }

    public static <V extends View> void setBelow(V view, int belowId) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.addRule(RelativeLayout.BELOW, belowId);
    }

    public static <V extends View> void setPosition(V view, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (leftMargin != 0)
            params.leftMargin = (int)(leftMargin * sCoeficient);
        if (topMargin != 0)
            params.topMargin = (int)(topMargin * sCoeficient);
        if (rightMargin != 0)
            params.rightMargin = (int)(rightMargin * sCoeficient);
        if (bottomMargin != 0)
            params.bottomMargin = (int)(bottomMargin * sCoeficient);

        view.setLayoutParams(params);
    }

}
