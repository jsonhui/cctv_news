package com.cctvnews.www.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.NewsTopicAdapter;
import com.cctvnews.www.model.mmodel.NewsSpecialTopicDatas;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class NewsSpecialTopicActivity extends UMActivity {
    @BindView(R.id.topic_iv)
    ImageView imageView_back;
    @BindView(R.id.topic_stickyListHeadersListView)
    ExpandableListView expandableListView;
    @BindView(R.id.topic_big_image_iv)
    ImageView big_image;
    String url;
    private NewsSpecialTopicDatas newsSpecialTopicDatas;
    /**
     * 准备数据源
     */
    private List<String> groupdata = new ArrayList<>();
    private List<NewsSpecialTopicDatas.DataBean.TopicInfoBean> infoBeen = new ArrayList<>();
    private List<NewsSpecialTopicDatas.DataBean.TopicListBean> topicListBeen = new ArrayList<>();
    private Map<String, List<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean>> itemMap = new HashMap<>();
    private NewsTopicAdapter newsTopicAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_special_topic);
        ButterKnife.bind(this);
        /**初始化数据*/
        initData();
        /**初始化适配器*/
        newsTopicAdapter = new NewsTopicAdapter(this, groupdata, itemMap);
        /**绑定适配器*/
        expandableListView.setAdapter(newsTopicAdapter);
        /**初始化监听*/
        intiListener();

    }

    private void intiListener() {
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("url", itemMap.get(groupdata.get(groupPosition)).get(childPosition).getDetailUrl());
                startActivity(intent);
                return true;
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Intent intent = new Intent();
                intent.putExtra("url", topicListBeen.get(groupPosition).getForumUrl());
                intent.setClass(getApplicationContext(), NewsTopicThreeActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }

    private void initData() {
        this.url = getIntent().getStringExtra("url");
        OkHttpTool.newInstance().start(url).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (topicListBeen != null && !topicListBeen.isEmpty()) {
                    return;
                }
                Gson gson = new Gson();
                newsSpecialTopicDatas = gson.fromJson(result, NewsSpecialTopicDatas.class);
                topicListBeen.addAll(newsSpecialTopicDatas.getData().getTopicList());
                upData(topicListBeen);
//                expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//                    @Override
//                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                        return true;
//                    }
//                });

                newsTopicAdapter.notifyDataSetChanged();
                for (int i = 0; i < groupdata.size(); i++) {
                    expandableListView.expandGroup(i);
                }
                /**初始化顶部大图*/
                initImageDatas();
            }
        });
    }

    private void initImageDatas() {
        infoBeen.add(newsSpecialTopicDatas.getData().getTopicInfo());
        Picasso.with(this).load(infoBeen.get(0).getTopicImg()).into(big_image);
    }

    private void upData(List<NewsSpecialTopicDatas.DataBean.TopicListBean> topicListBeen) {
        if (topicListBeen.size() != 0) {
            String name = topicListBeen.get(0).getForumName();
            groupdata.add(name);
            itemMap.put(name, new ArrayList<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean>());
            for (int j = 0; j < 4; j++) {
                itemMap.get(name).add(topicListBeen.get(0).getList().get(j));
            }
        }
        boolean same = false;
        for (int i = 1; i < topicListBeen.size(); i++) {
            String name2 = topicListBeen.get(i).getForumName();
            for (String n : groupdata) {
                if (name2.equals(n)) {
                    for (int j = 0; j < 4; j++) {
                        itemMap.get(name2).add(topicListBeen.get(i).getList().get(j));
                    }
                    same = true;
                }
            }
            if (!same) {
                groupdata.add(name2);
                itemMap.put(name2, new ArrayList<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean>());
                for (int j = 0; j < 4; j++) {
                    itemMap.get(name2).add(topicListBeen.get(i).getList().get(j));
                }
            }
            same = false;
        }

    }
}
