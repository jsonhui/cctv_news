package com.cctvnews.www.tool.httptool;

import android.os.Handler;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangjw on 2016/5/23.
 * url：androidxx.cn
 * desc：网络请求
 */
public class HttpUtil {

    private static ExecutorService executorService;

    private static Handler mHandler = new Handler();

//    private IRequestCallBack requestCallBack;

    static {
        //创建一个定长的线程池
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(4);
        }
    }

    public static void requestGet(final String address, final IRequestCallBack requestCallBack) {

        //创建线程，由线程池进行管理
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final String result = httpGet(address);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //运行在主线程之上
                        if (requestCallBack != null) {
                            requestCallBack.onSuccess(result);
                        }
                    }
                });
            }
        });
    }

    public static void requestPost(final String address, final String param, final String flag, final IRequestCallBack requestCallBack) {
        final String params = param + flag;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final String result = httpPost(address, params);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (requestCallBack != null) {
                            requestCallBack.onSuccess(result);
                        }
                    }
                });
            }
        });

    }

    /**
     * get请求方式
     *
     * @param address
     */
    private static String httpGet(String address) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(address);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                StringBuffer stringBuffer = new StringBuffer();
                while ((len = inputStream.read(buffer)) != -1) {
                    stringBuffer.append(new String(buffer, 0, len));
                }
                return stringBuffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }

        }

        return null;

    }

    private static String httpPost(String address, String params) {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(address);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.getOutputStream().write(params.getBytes());
            urlConnection.getOutputStream().flush();

            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                StringBuffer stringBuffer = new StringBuffer();
                while ((len = inputStream.read(buffer)) != -1) {
                    stringBuffer.append(new String(buffer, 0, len));
                }
                return stringBuffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;

    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
