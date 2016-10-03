package com.cctvnews.www.tool.imagetool;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 内存缓存工具
 *
 * @author arvin
 */
public class LruCacheTools {

    private static LruCache<String, Bitmap> lruCache;
    private static int maxSize = 4 * 1024 * 1024;

    /**
     * 初始化lruCache
     */
    static {
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            /**
             * 注意要重写该方法，如果不重写，设置的最大缓存区无效
             */
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    /**
     * 放图片
     */
    public static void put(String key, Bitmap bm) {
        if (key != null && bm != null) {
            lruCache.put(key, bm);
        }
    }

    /**
     * 取图片
     */
    public static Bitmap get(String key) {
        if (!key.equals("")) {
            return lruCache.get(key);
        }
        return null;

    }
}
