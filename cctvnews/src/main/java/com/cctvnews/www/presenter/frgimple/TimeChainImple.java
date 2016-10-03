package com.cctvnews.www.presenter.frgimple;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 作者：Json on 2016/7/21 19:54
 * 邮箱：320175912@qq.com
 */
public interface TimeChainImple {
    /**
     * 操作ui
     **/
    void oprateTimeChain(Context context,
                         PullToRefreshListView pullToRefreshListView,
                         ImageView searchIV,
                         View inflate,
                         String path);
}
