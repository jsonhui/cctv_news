package com.cctvnews.www.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class NewsViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;
    List<String> mTitleDatas;

    public NewsViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitleDatas) {
        super(fm);
        this.fragmentList = fragmentList;
        this.mTitleDatas = mTitleDatas;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();

    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleDatas.get(position);
    }
}
