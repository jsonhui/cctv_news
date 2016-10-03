package com.cctvnews.www.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：Json on 2016/7/19 20:57
 * 邮箱：320175912@qq.com
 */
public class StatementPagerAdapter extends PagerAdapter {
    private List<View> viewList;

    public StatementPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return this.viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(this.viewList.get(position));
        return this.viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(this.viewList.get(position));
    }
}
