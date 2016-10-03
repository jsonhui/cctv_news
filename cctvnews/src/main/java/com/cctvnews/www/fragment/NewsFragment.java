package com.cctvnews.www.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.NewsViewPagerAdapter;
import com.cctvnews.www.model.mmodel.News;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author ：Created by Jason on 2016/7/12 13:15
 * @Email : 320175912@qq.com
 * @desc : 新闻页
 */
public class NewsFragment extends Fragment {
    /**
     * 装fragment的集合
     */
    List<Fragment> fragmentList = new ArrayList<>();
    /**
     * 装TabLayout的集合
     */
    List<String> mTitleDatas = new ArrayList<>();

    @BindView(R.id.newsfragment_viewpager)
    ViewPager viewPager;
    @BindView(R.id.newsfragment_tablayout)
    TabLayout tabLayout;

    private List<News.DataBean> dataBeen = new ArrayList<>();
    private NewsViewPagerAdapter newsViewPagerAdapter;
    private String path;


    public NewsFragment() {
    }

    public static NewsFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable("path", path);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        path = (String) getArguments().getSerializable("path");
        ButterKnife.bind(this, view);
        /** TODO 在这写**/
        //初始化数据
        initDatas();
        //初始化适配器
        newsViewPagerAdapter = new NewsViewPagerAdapter(getFragmentManager(), fragmentList, mTitleDatas);
        //绑定适配器
        viewPager.setAdapter(newsViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                News news = gson.fromJson(result, News.class);
                dataBeen = news.getData();
                if (fragmentList.size() != 0 && mTitleDatas.size() != 0) {
                    return;
                }
                for (int i = 0; i < dataBeen.size(); i++) {
                    if (i == 0) {
                        fragmentList.add(HeadLineFragment.newInstance(dataBeen.get(i).getUrl()));
                    } else if (i <= dataBeen.size() - 2 && i != 0) {
                        fragmentList.add(SimpleFragment.newInstance(dataBeen.get(i).getUrl()));
                    } else {
                        fragmentList.add(NewsEarthquakeFragment.newInstance(dataBeen.get(i).getUrl()));
                    }

                    mTitleDatas.add(dataBeen.get(i).getTitle());
                }
                newsViewPagerAdapter.notifyDataSetChanged();
            }

        });
    }


}
