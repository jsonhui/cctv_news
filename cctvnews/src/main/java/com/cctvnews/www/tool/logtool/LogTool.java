package com.cctvnews.www.tool.logtool;

import android.util.Log;

/**
 * Created by jason on 2016/6/29.
 */
public class LogTool {
    private static final String TAG = "jason";
    private static boolean debug = true;

    public static void log(Class clazz, String log) {
        if (debug) {
            Log.d(TAG, clazz.toString() + "-->" + log);
        }
    }
}
