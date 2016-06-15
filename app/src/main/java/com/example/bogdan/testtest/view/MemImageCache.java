package com.example.bogdan.testtest.view;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 15.06.16
 */
public class MemImageCache extends LruCache<Integer, Bitmap> {

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public MemImageCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(Integer key, Bitmap value) {
        return value.getByteCount() / 1024;
    }

    @Override
    protected void entryRemoved(boolean evicted, Integer key, Bitmap oldValue, Bitmap newValue) {
        oldValue.recycle();
    }

    public void addBitmapToMemoryCache(Integer key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemoryCache(Integer key) {
        return get(key);
    }
}
