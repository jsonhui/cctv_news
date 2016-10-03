package com.cctvnews.www.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.NewsTopicThreeAdapter;
import com.cctvnews.www.model.mmodel.NewsTopicThreeDatas;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class NewsTopicThreeActivity extends UMActivity {

    private List<NewsTopicThreeDatas.DataBean.ListBean> listBeen = new ArrayList<>();
    private NewsTopicThreeDatas newsTopicThreeDatas;
    private int page = 1;


    @BindView(R.id.topic_three_iv)
    ImageView back_iv;
    @BindView(R.id.topic_three_pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;
    @BindView(R.id.topic_three_tv)
    TextView mtextView;

    String url;
    String url02 = "&n=20&p=";
    private NewsTopicThreeAdapter newsTopicThreeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_special_topic_three);
        ButterKnife.bind(this);
        intiData();
        newsTopicThreeAdapter = new NewsTopicThreeAdapter(listBeen, this);
        pullToRefreshListView.setAdapter(newsTopicThreeAdapter);
        initListener();
    }

    private void initListener() {
        pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                page++;
                if (page == 2) {
                    OkHttpTool.newInstance().start(url + url02 + page).callback(new IOKCallBack() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            newsTopicThreeDatas = gson.fromJson(result, NewsTopicThreeDatas.class);
                            listBeen.addAll(newsTopicThreeDatas.getData().getList());
                            newsTopicThreeAdapter.notifyDataSetChanged();
                            pullToRefreshListView.onRefreshComplete();
                        }
                    });
                } else {
                    pullToRefreshListView.onRefreshComplete();
                }
            }
        });
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplication(), WebViewActivity.class);
                intent.putExtra("url", listBeen.get(position - 1).getDetailUrl());
                startActivity(intent);
            }
        });
    }

    private void intiData() {
        this.url = getIntent().getStringExtra("url");
        OkHttpTool.newInstance().start(url + url02 + 1).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                newsTopicThreeDatas = gson.fromJson(result, NewsTopicThreeDatas.class);
                listBeen.addAll(newsTopicThreeDatas.getData().getList());
                newsTopicThreeAdapter.notifyDataSetChanged();
            }
        });

    }
}
