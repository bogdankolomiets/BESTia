package com.example.bogdan.testtest.view.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.bogdan.testtest.Constants;
import com.example.bogdan.testtest.utils.ImageUtils;
import com.example.bogdan.testtest.R;
import com.example.bogdan.testtest.utils.Resizer;
import com.example.bogdan.testtest.utils.MemImageCache;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.news_row, parent, false);
            holder = new Holder(convertView);

            Resizer.configureView(holder.newsPoster,
                    Constants.NEWS_ADAPTER.WIDTH.NEWS_POSTER,
                    Constants.NEWS_ADAPTER.HEIGHT.NEWS_POSTER);
            Resizer.setPosition(holder.newsPoster,
                    Constants.NEWS_ADAPTER.L_MARGIN.NEWS_POSTER,
                    Constants.NEWS_ADAPTER.T_MARGIN.NEWS_POSTER);

            Resizer.configureView(holder.newsFrame,
                    Constants.NEWS_ADAPTER.WIDTH.NEWS_FRAME,
                    Constants.NEWS_ADAPTER.HEIGHT.NEWS_FRAME);
            Resizer.setPosition(holder.newsFrame,
                    Constants.NEWS_ADAPTER.L_MARGIN.NEWS_FRAME,
                    Constants.NEWS_ADAPTER.T_MARGIN.NEWS_FRAME);

            Resizer.configureView(holder.newsHeader,
                    Constants.NEWS_ADAPTER.WIDTH.NEWS_HEADER,
                    Constants.NEWS_ADAPTER.HEIGHT.NEWS_HEADER);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        loadImage(position, holder.newsPoster);

        loadImage(mContext,
                R.drawable.news_poster_frame,
                holder.newsFrame,
                Constants.NEWS_ADAPTER.WIDTH.NEWS_FRAME,
                Constants.NEWS_ADAPTER.HEIGHT.NEWS_FRAME);

        if (position % 2 == 0) {
            loadImage(mContext,
                    R.drawable.news_poster_sprt_0,
                    holder.newsHeader,
                    Constants.NEWS_ADAPTER.WIDTH.NEWS_HEADER,
                    Constants.NEWS_ADAPTER.HEIGHT.NEWS_HEADER);
            holder.newsPoster.setRotation(Constants.NEWS_ADAPTER.ROTATION.POSTER_SPRT_0);
            holder.newsFrame.setRotation(Constants.NEWS_ADAPTER.ROTATION.POSTER_SPRT_0);
        } else {
            loadImage(mContext,
                    R.drawable.news_poster_sprt_1,
                    holder.newsHeader,
                    Constants.NEWS_ADAPTER.WIDTH.NEWS_HEADER,
                    Constants.NEWS_ADAPTER.HEIGHT.NEWS_HEADER);
            holder.newsPoster.setRotation(-Constants.NEWS_ADAPTER.ROTATION.POSTER_SPRT_1);
            holder.newsFrame.setRotation(-Constants.NEWS_ADAPTER.ROTATION.POSTER_SPRT_1);
        }

        return convertView;
    }

    private void loadImage(Context mContext, int resId, ImageView imageView, int width, int height) {
        final Integer key = resId;
        Bitmap bitmap = mMemoryCache.getBitmapFromMemoryCache(key);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else  {
            bitmap = ImageUtils.decodeBitmap(mContext, resId, width, height);
            mMemoryCache.addBitmapToMemoryCache(key, bitmap);
        }
    }

    private void loadImage(int position, ImageView imageView) {
        final Integer key = position;
        Bitmap bitmap = mMemoryCache.getBitmapFromMemoryCache(key);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageBitmap(ImageUtils.decodeBitmap(mContext,
                    R.drawable.placeholder,
                    Constants.NEWS_ADAPTER.WIDTH.NEWS_POSTER,
                    Constants.NEWS_ADAPTER.HEIGHT.NEWS_POSTER));
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
