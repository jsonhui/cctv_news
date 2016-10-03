package com.cctvnews.www.presenter.frgdao;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.TimeChainAdapter;
import com.cctvnews.www.config.URL;
import com.cctvnews.www.handle.ICallBack;
import com.cctvnews.www.model.imodel.ITimeChain;
import com.cctvnews.www.model.mmodel.TimeChain;
import com.cctvnews.www.presenter.frgimple.TimeChainImple;
import com.cctvnews.www.tool.commontool.HttpUtils;
import com.cctvnews.www.tool.logtool.LogTool;
import com.cctvnews.www.ui.SearchActivity;
import com.cctvnews.www.ui.WebActivity;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Json on 2016/7/21 19:57
 * 邮箱：320175912@qq.com
 */
public class TimeChainDao implements TimeChainImple {
    private List<Object> listData = new ArrayList<>();
    private TimeChainAdapter timeChainAdapter;
    private int pager = 1;

    @Override
    public void oprateTimeChain(final Context context,
                                final PullToRefreshListView pullToRefreshListView,
                                ImageView searchIV,
                                View inflate,
                                final String path) {
        final ITimeChain dao = new TimeChain();
        /****添加为空的进度***/
        ImageView loadIV = (ImageView) inflate.findViewById(R.id.load_iv);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
        loadIV.startAnimation(animation);
        pullToRefreshListView.setEmptyView(inflate);
        /**监听(下拉刷新和上拉刷新都可)**/
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        /**设置下拉上拉样式**/
        ILoadingLayout startProxy = pullToRefreshListView.getLoadingLayoutProxy(true, false);
        startProxy.setPullLabel("下拉刷新");
        startProxy.setRefreshingLabel("努力加载");
        startProxy.setReleaseLabel("释放刷新");
        startProxy.setLoadingDrawable(context.getResources().getDrawable(R.drawable.icon_loading_earth));
        ILoadingLayout endProxy = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        endProxy.setPullLabel("");
        endProxy.setRefreshingLabel("");
        endProxy.setReleaseLabel("");
        endProxy.setLoadingDrawable(context.getResources().getDrawable(R.drawable.default_ptr_rotate));
        /**设置下拉上拉样式**/
        /**适配器**/
        timeChainAdapter = new TimeChainAdapter(context, listData);
        /**绑定适配器**/
        pullToRefreshListView.setAdapter(timeChainAdapter);
        /**数据**/
        dao.getTimeChainData(path + 1, new ICallBack() {
            @Override
            public void callData(List<Object> info) {
                listData.clear();
                listData.addAll(info);
                timeChainAdapter.notifyDataSetChanged();
            }
        });
        /**搜索监听**/
        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("searchData", URL.SEARCH_DATA);
                intent.setClass(context, SearchActivity.class);
                context.startActivity(intent);
            }
        });
        /**刷新监听**/
        pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                /**上拉刷新 **/
                pager++;
                dao.getTimeChainMoreData(path + pager, listData, new ICallBack() {
                    @Override
                    public void callData(List<Object> info) {
                        listData = info;
                        timeChainAdapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();
                    }
                });
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                /**下拉刷新 **/
                dao.getTimeChainData(path + 1, new ICallBack() {
                    @Override
                    public void callData(List<Object> info) {
                        listData.clear();
                        listData.addAll(info);
                        timeChainAdapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();
                    }
                });
                pager = 1;
            }
        });
        /**item监听**/
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**定义一个标记位，来只是List<?>的哪一条具体数据**/
                int temp;
                /**得到内置的ListView**/
                ListView listView = pullToRefreshListView.getRefreshableView();
                /** listView.getHeaderViewsCount()得到头的数量，
                 * 大于1的时候,表示加了头(可能不止一个头)，在这里只加一个头
                 * 等于1的时候，表示没有加头*/
                Log.i("TAG", listView.getHeaderViewsCount() + "---------------------------------");
                if (listView.getHeaderViewsCount() == 1) {
                    temp = position - 1;
                } else {
                    temp = position - 2;
                }
                LogTool.log(getClass(), "into item");
                Intent intent = new Intent(context, WebActivity.class);
                TimeChain.ItemListBean tempInfo = (TimeChain.ItemListBean) listData.get(temp);
                long longTime = Long.parseLong(tempInfo.getPubDate());
                String stringTime = HttpUtils.toDataTwo(longTime);
                String path = "http://m.news.cctv.com/" + stringTime + tempInfo.getItemID() + ".shtml";
                intent.putExtra("url", path);
                context.startActivity(intent);
            }
        });
    }
}