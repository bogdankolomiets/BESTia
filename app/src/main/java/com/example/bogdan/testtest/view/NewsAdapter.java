package com.example.bogdan.testtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.bogdan.testtest.ImageUtils;
import com.example.bogdan.testtest.R;
import com.squareup.picasso.LruCache;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 10.06.16
 */
public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bitmap> mNewsList;
    private android.support.v4.util.LruCache<Integer, Bitmap> mMemoryCache;

    public NewsAdapter(Context context) {
        mContext = context;
        mNewsList = new ArrayList<>();

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        mMemoryCache = new android.support.v4.util.LruCache<Integer, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(Integer key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
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
            holder = new Holder();
            holder.newsPoster = (ResizebleImageView) convertView.findViewById(R.id.newsPoster);
            holder.newsPoster.configureView(562, 794, 30, 68);
            holder.newsFrame = (ResizebleImageView) convertView.findViewById(R.id.newsFrame);
            holder.newsFrame.configureView(610, 850, 6, 40);
            holder.newsHeader = (ResizebleImageView) convertView.findViewById(R.id.newsHeader);
            holder.newsHeader.configureView(624, 950, 0, 0);
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

    private void addBitmapToMemoryCache(Integer key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromMemoryCache(Integer key) {
        return mMemoryCache.get(key);
    }

    private void loadImage(Context mContext, int resId, ResizebleImageView imageView) {
        final Integer key = resId;
        Bitmap bitmap = getBitmapFromMemoryCache(key);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else  {
            bitmap = ImageUtils.decodeBitmap(mContext, resId);
            addBitmapToMemoryCache(key, bitmap);
        }
    }

    private void loadImage(int position, ResizebleImageView imageView) {
        final Integer key = position;
        Bitmap bitmap = getBitmapFromMemoryCache(key);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            bitmap = getItem(position);
            addBitmapToMemoryCache(key, bitmap);
        }
    }

    private static class Holder {
        ResizebleImageView newsPoster;
        ResizebleImageView newsFrame;
        ResizebleImageView newsHeader;
    }
}
