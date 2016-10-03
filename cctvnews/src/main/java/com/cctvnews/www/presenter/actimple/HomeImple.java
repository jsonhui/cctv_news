package com.cctvnews.www.presenter.actimple;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * 作者：Json on 2016/7/21 19:17
 * 邮箱：320175912@qq.com
 */
public interface HomeImple {
    //打开友盟
    void openUMPush(Context context);

    //监听RadioGroup
    void listenHome(Context context,
                    FragmentManager fragmentManager,
                    RadioGroup rg_all,
                    RadioButton home_news_rb);
}
