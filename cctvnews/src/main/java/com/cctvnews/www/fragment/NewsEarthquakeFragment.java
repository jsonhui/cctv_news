package com.cctvnews.www.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.News;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsEarthquakeFragment extends Fragment {
    private List<News.DataBean> dataBeen = new ArrayList<>();
    private String path;
    @BindView(R.id.earthqueake_web)
    WebView earthqueake_web;


    public NewsEarthquakeFragment() {
    }

    public static NewsEarthquakeFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable("path", path);
        NewsEarthquakeFragment fragment = new NewsEarthquakeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_earthquake, container, false);
        path = (String) getArguments().getSerializable("path");
        ButterKnife.bind(this, view);
        WebSettings settings = earthqueake_web.getSettings();
        settings.setJavaScriptEnabled(true);
        earthqueake_web.loadUrl(path);
        return view;
    }


}
