package com.cctvnews.www.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cctvnews.www.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class JokerFragment extends Fragment {

    @BindView(R.id.shishi_xinwenlianbo_iv)
    ImageView shishi_xinwenlianbo_iv;
    @BindView(R.id.shishi_wanjiannews_iv)
    ImageView shishi_wanjiannews_iv;
    @BindView(R.id.shishi_newszhibojian_iv)
    ImageView shishi_newszhibojian_iv;
    @BindView(R.id.yansong_news1_iv)
    ImageView yansong_news1_iv;
    @BindView(R.id.yansong_newszhoukan_iv)
    ImageView yansong_newszhoukan_iv;
    @BindView(R.id.fengmian_mainduimian_iv)
    ImageView fengmian_mainduimian_iv;
    @BindView(R.id.zhitong_jiaodian_iv)
    ImageView zhitong_jiaodian_iv;
    @BindView(R.id.zhitong_newsdiaocha_iv)
    ImageView zhitong_newsdiaocha_iv;
    @BindView(R.id.zhitong_report_iv)
    ImageView zhitong_report_iv;
    @BindView(R.id.lianan_haixia_iv)
    ImageView lianan_haixia_iv;
    @BindView(R.id.shiye_shijiezhoukan_iv)
    ImageView shiye_shijiezhoukan_iv;
    @BindView(R.id.shiye_huanqiu_iv)
    ImageView shiye_huanqiu_iv;
    @BindView(R.id.shiye_guoji_iv)
    ImageView shiye_guoji_iv;
    @BindView(R.id.huicui_zhaowen_iv)
    ImageView huicui_zhaowen_iv;
    @BindView(R.id.huicui_news30_iv)
    ImageView huicui_news30_iv;
    @BindView(R.id.huicui_gongtong_iv)
    ImageView huicui_gongtong_iv;
    @BindView(R.id.huicui_dongfan_iv)
    ImageView huicui_dongfan_iv;
    @BindView(R.id.huicui_24hours_iv)
    ImageView huicui_24hours_iv;
    @BindView(R.id.huicui_wuyenews_iv)
    ImageView huicui_wuyenews_iv;
    @BindView(R.id.huicui_fazhi_iv)
    ImageView huicui_fazhi_iv;


    public JokerFragment() {
    }

    public static JokerFragment newInstance(String path) {
        Bundle args = new Bundle();

        JokerFragment fragment = new JokerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joker, container, false);
        ButterKnife.bind(view);


        return view;
    }

}
