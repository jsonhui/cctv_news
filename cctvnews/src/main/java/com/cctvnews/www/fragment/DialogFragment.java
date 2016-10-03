package com.cctvnews.www.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.DialogAdapter;
import com.cctvnews.www.model.mmodel.DialogDatas;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends Fragment {


    private List<DialogDatas.DataBean.ContentBean.InfoBean> infoBeen = new ArrayList<>();
    private int page = 1;

    @BindView(R.id.dialog_pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;
//    @BindView(R.id.dialog_chat_publish)
//    ImageView dialog_chat_publish;
    private DialogAdapter dialogAdapter;
    public DialogFragment() {
    }
    public static DialogFragment newInstance() {
        Bundle args = new Bundle();
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable("path", path);
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, view);
        String path = (String) getArguments().getSerializable("path");
        dialogAdapter = new DialogAdapter(infoBeen, getContext());
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
        pullToRefreshListView.setAdapter(dialogAdapter);
        initDatas(path);
        //pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        initListener(path);
        return view;
    }
    private void initListener(final String path) {
       pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
           @Override
           public void onLastItemVisible() {
               page++;
               OkHttpTool.newInstance().start(path+page).callback(new IOKCallBack() {
                   @Override
                   public void success(String result) {
                       Gson gson = new Gson();
                       DialogDatas dialogDatas = gson.fromJson(result, DialogDatas.class);
                       List<DialogDatas.DataBean.ContentBean> content = dialogDatas.getData().getContent();
                       for (int i = 0; i < content.size(); i++) {
                           infoBeen.addAll(content.get(i).getInfo());
                       }
                       pullToRefreshListView.onRefreshComplete();
                       dialogAdapter.notifyDataSetChanged();
                   }
               });
           }
       });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                OkHttpTool.newInstance().start(path+page).callback(new IOKCallBack() {
                    @Override
                    public void success(String result) {
                        infoBeen.clear();
                        Gson gson = new Gson();
                        DialogDatas dialogDatas = gson.fromJson(result, DialogDatas.class);
                        List<DialogDatas.DataBean.ContentBean> content = dialogDatas.getData().getContent();
                        for (int i = 0; i < content.size(); i++) {
                            infoBeen.addAll(content.get(i).getInfo());
                        }
                        pullToRefreshListView.onRefreshComplete();
                        dialogAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    private void initDatas(String path) {
        OkHttpTool.newInstance().start(path + page).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                DialogDatas dialogDatas = gson.fromJson(result, DialogDatas.class);
                List<DialogDatas.DataBean.ContentBean> content = dialogDatas.getData().getContent();
                for (int i = 0; i < content.size(); i++) {
                    infoBeen.addAll(content.get(i).getInfo());
                }
                dialogAdapter.notifyDataSetChanged();
            }
        });
    }
}
