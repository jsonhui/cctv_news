package com.cctvnews.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.LivingAdapter;
import com.cctvnews.www.model.mmodel.LivingDatas;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public class LivingFragment extends Fragment {

    @BindView(R.id.living_pullToRefreshListView)
    PullToRefreshListView living_pullToRefreshListView;
    private String path;
    private LivingDatas livingDatas = null;
    private List<LivingDatas.MsgListBean> msgListBeen  = new ArrayList<>();
    private LivingAdapter livingAdapter;

    public LivingFragment() {
    }

    public static LivingFragment newInstance(String path) {

        Bundle args = new Bundle();
        args.putSerializable("path", path);
        LivingFragment fragment = new LivingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_living,null);
        path = (String) getArguments().getSerializable("path");
        ButterKnife.bind(this,view);
        /**初始化适配器**/
        livingAdapter  = new LivingAdapter(msgListBeen,getContext());
        /**绑定适配器**/
        living_pullToRefreshListView.setAdapter(livingAdapter);
        /**初始话数据**/
        initDatas();
        return view;
    }

    private void initDatas() {
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                livingDatas = gson.fromJson(result,LivingDatas.class);
                msgListBeen.addAll(livingDatas.getMsgList());
                livingAdapter.notifyDataSetChanged();
            }
        });
    }
}
