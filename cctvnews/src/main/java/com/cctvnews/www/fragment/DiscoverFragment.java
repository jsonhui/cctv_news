package com.cctvnews.www.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.cctvnews.www.R;
import com.cctvnews.www.adapter.TwoWayGridAdapter;
import com.cctvnews.www.config.URL;
import com.cctvnews.www.customview.TwoWayAdapterView;
import com.cctvnews.www.customview.TwoWayGridView;
import com.cctvnews.www.model.mmodel.DiscoverDatas;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.logtool.LogTool;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.cctvnews.www.ui.SearchActivity;
import com.cctvnews.www.ui.SweepActivity;
import com.cctvnews.www.ui.WebActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Author ：Created by Jason on 2016/7/12 13:23
 * @Email : 320175912@qq.com
 * @desc : 发现页
 */
public class DiscoverFragment extends Fragment {
    private int[] dots = new int[]{R.drawable.second_head_point, R.drawable.second_head_point_select};
    private List<DiscoverDatas.DataBean.BigImgBean> bigImgBeen = new ArrayList<>();
    private List<DiscoverDatas.DataBean.ItemListBean> itemListBeen = new ArrayList<>();
    @BindView(R.id.discover_banner)
    ConvenientBanner banner;
    @BindView(R.id.discover_twowaygridview)
    TwoWayGridView twoWayGridView;
    @BindView(R.id.discover_content_tv)
    TextView contentTV;
    @BindView(R.id.discover_search_rl)
    RelativeLayout searchLayout;
    @BindView(R.id.discover_swipe_rl)
    RelativeLayout swipeLayout;
    private TwoWayGridAdapter twoWayGridAdapter;

    public DiscoverFragment() {
    }

    public static DiscoverFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable("path", path);
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        /**开始自动滚动**/
        banner.startTurning(4000);
    }

    @Override
    public void onStop() {
        super.onStop();
        /**停止滚动**/
        banner.stopTurning();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, view);
        String path = (String) getArguments().getSerializable("path");
        /**适配器**/
        twoWayGridAdapter = new TwoWayGridAdapter(getContext(), itemListBeen);
        /**绑定适配器**/
        twoWayGridView.setAdapter(twoWayGridAdapter);
        /**数据**/
        initData(path);
        /**监听**/
        listenning();
        return view;
    }

    private void listenning() {
        /**twoWayGridView的监听**/
        twoWayGridView.setOnItemClickListener(new TwoWayAdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(TwoWayAdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getContext(), WebActivity.class);
                intent.putExtra("url", itemListBeen.get(position).getDetailUrl());
                startActivity(intent);
            }
        });
        /**搜索和扫一扫**/
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("searchData", URL.SEARCH_DATA);
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), SweepActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData(String path) {
        /**广告栏的数据h和文字的数据**/
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                DiscoverDatas discoverDatas = gson.fromJson(result, DiscoverDatas.class);
                /**获取广告栏数据**/
                bigImgBeen = discoverDatas.getData().getBigImg();
                /**获取生活服务的数据并刷新适配器**/
                List<DiscoverDatas.DataBean.ItemListBean> been = discoverDatas.getData().getItemList();
                LogTool.log(getClass(), been.size() + "--" + twoWayGridAdapter.getData().size());
                if (twoWayGridAdapter.getData().size() == 0) {
                    twoWayGridAdapter.getData().addAll(been);
                    twoWayGridAdapter.notifyDataSetChanged();
                }
                banner.setPages(new CBViewHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createHolder() {
                        return new BannerViewHolder();
                    }
                }, bigImgBeen);
                banner.setPageIndicator(dots)
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
            }
        });
    }

    private class BannerViewHolder implements Holder<DiscoverDatas.DataBean.BigImgBean> {
        ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, final DiscoverDatas.DataBean.BigImgBean data) {
            Picasso.with(getContext()).load(data.getItemImage()).into(imageView);
            LogTool.log(getClass(), position + "--");
            switch (position) {
                case 1:
                    contentTV.setText(bigImgBeen.get(0).getItemTitle());
                    break;
                case 2:
                    contentTV.setText(bigImgBeen.get(1).getItemTitle());
                    break;
                case 0:
                    contentTV.setText(bigImgBeen.get(2).getItemTitle());
                    break;
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("url", data.getDetailUrl());
                    intent.setClass(getContext(), WebActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}

