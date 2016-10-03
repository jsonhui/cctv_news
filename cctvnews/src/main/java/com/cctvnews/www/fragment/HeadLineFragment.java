package com.cctvnews.www.fragment;

import android.content.Context;
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

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.cctvnews.www.R;
import com.cctvnews.www.adapter.NewsAdapter;
import com.cctvnews.www.config.URL;
import com.cctvnews.www.model.mmodel.NewsItem;
import com.cctvnews.www.model.mmodel.NewsRefreshDatas;
import com.cctvnews.www.tool.commontool.HttpUtils;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.cctvnews.www.ui.LivingActivity;
import com.cctvnews.www.ui.NewsSpecialTopicActivity;
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
 * Created by Administrator on 2016/7/14 0014.
 */
public class HeadLineFragment extends Fragment {
    @BindView(R.id.headlinefragment_pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;
    private String path;
    private List<NewsItem.DataBean.BigImgBean> bigImgBeen = new ArrayList<>();
    private List<NewsRefreshDatas.ItemListBean> itemListBeen = new ArrayList<>();
    private NewsItem newsItem;
    private NewsRefreshDatas newsRefreshDatas;
    private HeaderViewHolder headerViewHolder;
    /**
     * 前半段的URL
     */
    private String urlDatas;
    private int page = 1;
    private NewsAdapter newsAdapter;
    TextView convenientbanner_tv;


    public HeadLineFragment() {
    }

    public static HeadLineFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable("path", path);
        HeadLineFragment fragment = new HeadLineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        /**开始自动滚动**/
        headerViewHolder.convenientBanner.startTurning(4000);
    }

    @Override
    public void onStop() {
        super.onStop();
        /**停止滚动**/
        headerViewHolder.convenientBanner.stopTurning();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_heaflin, container, false);
        path = (String) getArguments().getSerializable("path");
        ButterKnife.bind(this, view);
        urlDatas = URL.FRONT_DATA + HttpUtils.getTAdA(path) + HttpUtils.getToutuNum(path) + URL.CENTRE_DATA;
        /**初始化数据*/
        initDatas();
        /**初始化适配器*/
        newsAdapter = new NewsAdapter(itemListBeen, getContext());
        /**绑定适配器*/
        pullToRefreshListView.setAdapter(newsAdapter);
        /**添加头部视图*/
        addHeaderView();
        /**使用上拉下滑刷新*/
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
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

        /**设置监听*/
        initListener();
        return view;
    }

    private void initListener() {
        /**设置下拉上滑刷新*/
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                OkHttpTool.newInstance().start(urlDatas + 1 + URL.END_DATA).callback(new IOKCallBack() {
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
            public Intent intent;

            //参数postion：表示行数
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //定义一个标记位，来指定List<?>的那一条具体数据
                int flag = 0;
                ListView listView = pullToRefreshListView.getRefreshableView();
                /**
                 * listView.getHeaderViewsCount()得到头的数量，
                 * 大于1的时候,表示加了头(可能不止一个头)，在这里只加一个头
                 * 等于1的时候，表示没有加头
                 */

                if (listView.getHeaderViewsCount() == 1) {
                    flag = position - 1;
                } else {
                    flag = position - 2;
                }
                String articleType = itemListBeen.get(flag).getItemType();
                if (articleType.equals("classtopic_flag")) {
                    intent = new Intent(getContext(), NewsSpecialTopicActivity.class);
                    intent.putExtra("url", itemListBeen.get(flag).getDetailUrl());
                }else if (articleType.equals("it_flag")){
                    intent = new Intent(getContext(), LivingActivity.class);
                    intent.putExtra("url",itemListBeen.get(flag).getDetailUrl());
                }
                else {
                    intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", itemListBeen.get(flag).getDetailUrl());
                }
                startActivity(intent);
            }
        });

    }

    /**
     * 添加头部视图
     */
    private void addHeaderView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.news_pull_list_item_convenientbanner, null);
        headerViewHolder = new HeaderViewHolder(headerView);
        /**动态加载数据*/
        loadBannerDatas();
        pullToRefreshListView.getRefreshableView().addHeaderView(headerView);
    }

    /**
     * 下载广告数据
     */
    private void loadBannerDatas() {
        if (bigImgBeen != null && !bigImgBeen.isEmpty()) {
            //初始化头部视图的广告
            setupBanner(headerViewHolder);
            return;
        }
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                newsItem = gson.fromJson(result, NewsItem.class);
                bigImgBeen.addAll(newsItem.getData().getBigImg());
                setupBanner(headerViewHolder);
            }
        });

    }

    private void setupBanner(HeaderViewHolder headerViewHolder) {
        headerViewHolder.convenientBanner.setPages(new CBViewHolderCreator<HeadBannerViewHolder>() {
            @Override
            public HeadBannerViewHolder createHolder() {
                return new HeadBannerViewHolder();
            }
        }, bigImgBeen).setPageIndicator(new int[]{R.drawable.second_head_point, R.drawable.second_head_point_select})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    }

    private void initDatas() {
        OkHttpTool.newInstance().start(urlDatas + 1 + URL.END_DATA).callback(new IOKCallBack() {
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

    /**
     * 创建一个头部的内部类
     */
    class HeaderViewHolder {
        ConvenientBanner convenientBanner;

        public HeaderViewHolder(View headerView) {
            convenientBanner = (ConvenientBanner) headerView.findViewById(R.id.new_item_convenientbanner);
            convenientbanner_tv = (TextView) headerView.findViewById(R.id.new_item_convenientbanner_tv);
        }
    }

    /***/
    class HeadBannerViewHolder implements Holder<NewsItem.DataBean.BigImgBean> {
        ImageView imageViev;

        @Override
        public View createView(Context context) {
            imageViev = new ImageView(context);
            imageViev.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageViev;
        }

        @Override
        public void UpdateUI(Context context, final int position, NewsItem.DataBean.BigImgBean data) {
            Picasso.with(context).load(data.getItemImage()).into(imageViev);
            switch (position) {
                case 0:
                    convenientbanner_tv.setText(bigImgBeen.get(2).getItemTitle());
                    break;
                case 1:
                    convenientbanner_tv.setText(bigImgBeen.get(0).getItemTitle());
                    break;
                case 2:
                    convenientbanner_tv.setText(bigImgBeen.get(1).getItemTitle());
                    break;
            }
            imageViev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(getContext(), WebViewActivity.class);
//                    intent.putExtra("url", bigImgBeen.get(position).getDetailUrl());
//                    startActivity(intent);
                    Intent intent;
                    String articleType = bigImgBeen.get(position).getItemType();
                    if (articleType.equals("classtopic_flag")) {
                        intent = new Intent(getContext(), NewsSpecialTopicActivity.class);
                        intent.putExtra("url", bigImgBeen.get(position).getDetailUrl());
                    }else if (articleType.equals("it_flag")){
                        intent = new Intent(getContext(), LivingActivity.class);
                        intent.putExtra("url",bigImgBeen.get(position).getDetailUrl());
                    }
                    else {
                        intent = new Intent(getContext(), WebViewActivity.class);
                        intent.putExtra("url", bigImgBeen.get(position).getDetailUrl());
                    }
                    startActivity(intent);
                }
            });

        }
    }
}
