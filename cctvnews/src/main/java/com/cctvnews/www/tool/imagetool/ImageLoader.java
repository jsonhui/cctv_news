package com.cctvnews.www.tool.imagetool;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.cctvnews.www.R;
import com.cctvnews.www.tool.commontool.HttpUtils;


/**
 * 图片加载工具类
 *
 * @author arvin
 */
public class ImageLoader {

    public static void loaderImage(String path, ImageView iv) {

        // 先去内存里面找
        Bitmap mp = LruCacheTools.get(path);
        if (mp == null) {// 说明内存里面没有
            // 去磁盘找
            Log.i("TAG", "-----内存里面没，到磁盘找");
            new DiskAsyncTask(iv).execute(path);

        } else {
            Log.i("TAG", "-----内存里面已找到   显示");
            iv.setImageBitmap(mp);
        }
    }

    /**
     * 到磁盘找图片
     *
     * @author arvin
     */
    private static class DiskAsyncTask extends AsyncTask<String, Void, Bitmap> {
        String path;
        ImageView iv;

        public DiskAsyncTask(ImageView iv) {
            this.iv = iv;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            path = params[0];
            // 去磁盘里面找
            Bitmap mp = FileUtils.readFromDisk(path);
            return mp;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result == null) {// 说明磁盘里面没有
                // 网上下载
                Log.i("TAG", "-----磁盘里面没，从网上下载找");
                new DownLoadImageAsyncTask(iv).execute(path);
            } else// 磁盘里面有
            {
                Log.i("TAG", "-----磁盘里面找到，放到内存-显示");
                // 放入内存
                LruCacheTools.put(path, result);
                // 显示
                iv.setImageBitmap(result);

            }
        }
    }

    /**
     * 到网络找图片
     *
     * @author arvin
     */
    private static class DownLoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        String path;
        ImageView iv;

        public DownLoadImageAsyncTask(ImageView iv) {
            this.iv = iv;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            path = params[0];
            // 去磁盘里面找
            Bitmap mp = HttpUtils.getImageWithPath(path);
            return mp;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result == null) {// 说明网上也没有
                Log.i("TAG", "----网络上无图片，显示默认图片");
                // 显示默认图片
                iv.setImageResource(R.mipmap.ic_launcher);
            } else// 下载成功
            {
                Log.i("TAG", "----网络图片下载成功，放到磁盘--放入内存，显示");
                //放到磁盘
                FileUtils.saveToDisk(path, result);
                // 放入内存
                LruCacheTools.put(path, result);
                // 显示
                iv.setImageBitmap(result);
            }
        }
    }

}
