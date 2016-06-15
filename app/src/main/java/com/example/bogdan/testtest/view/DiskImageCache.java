package com.example.bogdan.testtest.view;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.bogdan.testtest.utils.DiskLruCache;
import com.example.bogdan.testtest.utils.ImageUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 15.06.16
 */
public class DiskImageCache {
    private DiskLruCache mDiskCache;
    private Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.JPEG;
    private int mCompressQuality = 70;

    public DiskImageCache(Context context, String uniqueName) {
        final File diskCacheDir = getDiskCacheDir(context, uniqueName);
    }

    private File getDiskCacheDir(Context context, String uniqueName) {
        final String cachePath = context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName);
    }

    private boolean writeBitmapToFile(Bitmap bitmap, DiskLruCache.Editor editor)
            throws IOException, FileNotFoundException {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(editor.newOutputStream(0), 8 * 1024);
            return bitmap.compress(mCompressFormat, mCompressQuality, out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void putBitmap(String key, Bitmap bitmap) {
        DiskLruCache.Editor editor = null;
        try {
            editor = mDiskCache.edit(key);
            if (editor == null) {
                return;
            }

            if (writeBitmapToFile(bitmap, editor)) {
                mDiskCache.flush();
                editor.commit();
            } else {
                editor.abort();
            }
        } catch (IOException e) {
            e.printStackTrace();

            try {
                if (editor != null) {
                    editor.abort();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public Bitmap getBitmap(String key) {
        Bitmap bitmap = null;
        DiskLruCache.Snapshot snapshot = null;

        try {
            snapshot = mDiskCache.get(key);
            if (snapshot == null) {
                return null;
            }

            final InputStream in = snapshot.getInputStream(0);
            if (in != null) {
                final BufferedInputStream buffIn = new BufferedInputStream(in, 8 * 1024);
                bitmap = ImageUtils.decodeStreamBitmap(buffIn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }

        return bitmap;
    }

}
