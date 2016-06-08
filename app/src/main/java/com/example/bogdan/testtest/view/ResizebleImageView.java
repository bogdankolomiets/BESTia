package com.example.bogdan.testtest.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 06.06.16
 */
public class ResizebleImageView extends ImageView {
    private Activity mActivity;
    private float mCoeficient;

    public ResizebleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mActivity = (Activity) context;
        mCoeficient = getCoeficient();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Drawable drawable = getDrawable();
        if (drawable != null) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height;

            if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
                height = MeasureSpec.getSize(heightMeasureSpec);
            } else {
                height = (int) Math.ceil((float) width * (float) drawable.getIntrinsicHeight() / (float) drawable.getIntrinsicWidth());
            }
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private float getCoeficient() {
        DisplayMetrics ds = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(ds);
        int screenWidth = ds.widthPixels;

        return screenWidth / 750f;
    }

    public void configureView(int width, int height, int leftMargin, int topMargin) {
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams((int) (width * mCoeficient), (int) (height * mCoeficient));
        params.leftMargin = (int)(leftMargin * mCoeficient);
        params.topMargin = (int)(topMargin * mCoeficient);

        this.setLayoutParams(params);
    }
}
