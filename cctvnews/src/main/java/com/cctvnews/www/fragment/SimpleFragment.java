package com.cctvnews.www.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.NewsAdapter;
import com.cctvnews.www.config.URL;
import com.cctvnews.www.model.mmodel.NewsItem;
import com.cctvnews.www.model.mmodel.NewsRefreshDatas;
import com.cctvnews.www.tool.commontool.HttpUtils;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.cctvnews.www.ui.WebViewActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Author ：Created by Jason on 2016/7/12 12:04
 * @Email : 320175912@qq.com
 * @desc : 随着传进来的参数不同显示不一样
 */
public class SimpleFragment extends Fragment {

    private NewsItem newsItem;
    private NewsRefreshDatas newsRefreshDatas;
    private List<NewsItem.DataBean.BigImgBean> bigImgBeen = new ArrayList<>();
    private List<NewsRefreshDatas.ItemListBean> itemListBeen = new ArrayList<>();

    @BindView(R.id.simplefragment_pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;
    private String path;
    private ImageView image_iv;
    private TextView image_tv;
    private View view;
    private String urlDatas;
    private int page = 1;
    private NewsAdapter newsAdapter;

    public SimpleFragment() {
    }

    public static SimpleFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable("path", path);
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        /**获取参数**/
        path = (String) getArguments().getSerializable("path");
        /**TODO 解析数据，初始化视图，创建适配器并且绑定**/
        ButterKnife.bind(this, view);
        urlDatas = URL.FRONT_DATA + HttpUtils.getTAdA(path) + HttpUtils.getToutuNum(path) + URL.CENTRE_DATA;
        /**初始化数据*/
        initDatas();
        /**设置下拉上拉样式**/
        ILoadingLayout startProxy = pullToRefreshListView.getLoadingLayoutProxy(true, false);
        startProxy.setPullLabel("下拉刷新");
        startProxy.setRefreshingLabel("努力加载");
        startProxy.setReleaseLabel("释放刷新");
        startProxy.setLoadingDrawable(this.getResources().getDrawable(R.drawable.icon_loading_earth));
        ILoadingLayout endProxy = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        endProxy.setPullLabel("");
        endProxy.setRefreshingLabel("");
        endProxy.setReleaseLabel("");
        endProxy.setLoadingDrawable(this.getResources().getDrawable(R.drawable.default_ptr_rotate));
        /**设置下拉上拉样式**/
        /**初始化适配器*/
        newsAdapter = new NewsAdapter(itemListBeen, getContext());
        /**绑定适配器*/
        pullToRefreshListView.setAdapter(newsAdapter);
        /**添加头部视图*/
        addHeaderView();
        /**使用上拉下滑刷新*/
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        /**设置监听*/
        initListener();
        return view;
    }

    private void initListener() {
        /**设置下拉上滑刷新*/
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                OkHttpTool.newInstance().start(urlDatas + page + URL.END_DATA).callback(new IOKCallBack() {
                    @Override
                    public void success(String result) {
                        /**清空适配器*/
                        itemListBeen.clear();
                        Gson gson = new Gson();
                        newsRefreshDatas = gson.fromJson(result, NewsRefreshDatas.class);
                        itemListBeen.addAll(newsRefreshDatas.getItemList());
                        /**刷新适配器*/
                        newsAdapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();
                    }
                });

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                OkHttpTool.newInstance().start(urlDatas + page + URL.END_DATA)
                        .callback(new IOKCallBack() {
                            @Override
                            public void success(String result) {
                                Gson gson = new Gson();
                                newsRefreshDatas = gson.fromJson(result, NewsRefreshDatas.class);
                                itemListBeen.addAll(newsRefreshDatas.getItemList());
                                /**刷新适配器*/
                                newsAdapter.notifyDataSetChanged();
                                pullToRefreshListView.onRefreshComplete();
                            }
                        });
            }
        });
        /**给item设置监听*/
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position >= 1) {
                    int flag = 0;
                    ListView listView = pullToRefreshListView.getRefreshableView();
                    if (listView.getHeaderViewsCount() == 1) {
                        flag = position - 1;
                    } else {
                        flag = position - 2;
                    }
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", itemListBeen.get(flag).getDetailUrl());
                    startActivity(intent);
                }
            }

        });

    }

    /**
     * 添加头部视图
     */
    private void addHeaderView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.news_pull_list_item_image, null);
        image_iv = (ImageView) view.findViewById(R.id.new_item_image_iv);
        image_tv = (TextView) view.findViewById(R.id.new_item_image_tv);
        /**加载数据*/
        initHeaderViewDatas();
        ListView listView = pullToRefreshListView.getRefreshableView();
        //判断有没有头
        int headerViewsCount = listView.getHeaderViewsCount();
        if (headerViewsCount <= 1) {
            listView.addHeaderView(view);
        }
           image_iv.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(getContext(),WebViewActivity.class);
                   intent.putExtra("url",bigImgBeen.get(0).getDetailUrl());
                   startActivity(intent);
               }
           });


    }

    private void initHeaderViewDatas() {
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                newsItem = gson.fromJson(result, NewsItem.class);
                bigImgBeen = newsItem.getData().getBigImg();
                for (int i = 0; i < bigImgBeen.size(); i++) {
                    image_tv.setText(bigImgBeen.get(i).getItemTitle());
                    Picasso.with(getContext()).load(bigImgBeen.get(i).getItemImage()).into(image_iv);
                }
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        OkHttpTool.newInstance().start(urlDatas + page + URL.END_DATA).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                newsRefreshDatas = gson.fromJson(result, NewsRefreshDatas.class);
                itemListBeen.addAll(newsRefreshDatas.getItemList());
                /**刷新适配器*/
                newsAdapter.notifyDataSetChanged();
            }
        });
    }
}
