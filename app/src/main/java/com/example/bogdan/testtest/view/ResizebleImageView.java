package com.example.bogdan.testtest.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
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
        if (getDrawable() != null) {
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
        if (leftMargin != 0)
            params.leftMargin = (int)(leftMargin * mCoeficient);
        if (topMargin != 0)
            params.topMargin = (int)(topMargin * mCoeficient);

        setLayoutParams(params);
    }

    public void setImage(int resId, int width, int height) {
        System.out.println("Width Width Width = " + width);
        Bitmap bitmap = decodeBitmap(getResources(), resId, width, height);
        System.out.println("This bitmap size = " + bitmap.getByteCount());
//        System.out.println("Resized bitmap size = " + getResizedBitmap(bitmap, (int) (width * mCoeficient),(int) (height * mCoeficient)).getByteCount());
        setImageBitmap(bitmap);
    }

    public void setTopPadding(int padding) {
        setPadding(0, (int)(mCoeficient * padding), 0, 0);
    }

    public void setBottomPadding(int padding) {

    }

    private void getImageSize(Bitmap bitmap) {
        System.out.println("Bitmap widht = " + bitmap.getWidth()
                + ", height = " + bitmap.getHeight() + ", Size = " + (bitmap.getByteCount() / 1024f / 1024) + " mb");
    }

    public void setImage(int resId) {
        setImageBitmap(decodeBitmap(resId));
    }

    private Bitmap decodeBitmap(int redId) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        return BitmapFactory.decodeResource(getResources(), redId, options);
    }

    private Bitmap decodeBitmap(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeResource(res, resId, options);

    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        System.out.println("Height " + height);
        System.out.println("reqHeight" + reqHeight);
        final int width = options.outWidth;
        int inSampleSize = 2;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


}
