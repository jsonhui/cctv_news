package com.cctvnews.www.presenter.actimple;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * 作者：Json on 2016/7/21 17:27
 * 邮箱：320175912@qq.com
 */
public interface StatementImple {
    /**
     * 是否需要引导
     **/
    void doStatement(Context context, ViewPager viewPager, RelativeLayout titleRL, Button rejectBTN, Button agreeBTN);
}
