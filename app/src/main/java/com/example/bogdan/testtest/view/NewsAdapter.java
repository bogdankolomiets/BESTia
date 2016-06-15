package com.example.bogdan.testtest.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.bogdan.testtest.utils.ImageUtils;
import com.example.bogdan.testtest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 10.06.16
 */
public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bitmap> mNewsList;
    private MemImageCache mMemoryCache;

    public NewsAdapter(Context context) {
        mContext = context;
        mNewsList = new ArrayList<>();
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        mMemoryCache = new MemImageCache(cacheSize);
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Bitmap getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addNewsItem(Bitmap newsItem) {
        mNewsList.add(newsItem);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        Bitmap image;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.news_row, parent, false);
            holder = new Holder(convertView);

            Resizer.into((Activity)mContext);

            Resizer.configureView(holder.newsPoster, 562, 794);
            Resizer.setPosition(holder.newsPoster, 30, 68, 0, 0);

            Resizer.configureView(holder.newsFrame, 610, 850);
            Resizer.setPosition(holder.newsFrame, 6, 40, 0, 0);

            Resizer.configureView(holder.newsHeader, 624, 950);
            Resizer.setPosition(holder.newsHeader, 0, 0, 0, 0);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        loadImage(position, holder.newsPoster);

        loadImage(mContext, R.drawable.news_poster_frame, holder.newsFrame);

        if (position % 2 == 0) {
            loadImage(mContext, R.drawable.news_poster_sprt_0, holder.newsHeader);
            holder.newsPoster.setRotation(1.0f);
            holder.newsFrame.setRotation(1.0f);
        } else {
            loadImage(mContext, R.drawable.news_poster_sprt_1, holder.newsHeader);
            holder.newsPoster.setRotation(-0.7f);
            holder.newsFrame.setRotation(-0.7f);
        }

        return convertView;
    }

    private void loadImage(Context mContext, int resId, ImageView imageView) {
        final Integer key = resId;
        Bitmap bitmap = mMemoryCache.getBitmapFromMemoryCache(key);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else  {
            bitmap = ImageUtils.decodeBitmap(mContext, resId);
            mMemoryCache.addBitmapToMemoryCache(key, bitmap);
        }
    }

    private void loadImage(int position, ImageView imageView) {
        final Integer key = position;
        Bitmap bitmap = mMemoryCache.getBitmapFromMemoryCache(key);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageBitmap(ImageUtils.decodeBitmap(mContext, R.drawable.placeholder));
            bitmap = getItem(position);
            imageView.setImageBitmap(bitmap);
            mMemoryCache.addBitmapToMemoryCache(key, bitmap);
        }
    }

    static class Holder {
        @BindView(R.id.newsPoster) ImageView newsPoster;
        @BindView(R.id.newsFrame) ImageView newsFrame;
        @BindView(R.id.newsHeader) ImageView newsHeader;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
