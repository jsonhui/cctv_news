package com.cctvnews.www.customview;

import android.content.Context;
import android.util.AttributeSet;

import java.io.Serializable;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 作者：Json on 2016/7/17 14:36
 * 邮箱：320175912@qq.com
 */
public class MyJCVideoPlayerStandard extends JCVideoPlayerStandard implements Serializable {
    public MyJCVideoPlayerStandard(Context context) {
        super(context);
    }

    public MyJCVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
