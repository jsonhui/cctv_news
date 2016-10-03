package com.cctvnews.www.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cctvnews.www.R;
import com.cctvnews.www.presenter.frgdao.TimeChainDao;
import com.cctvnews.www.presenter.frgimple.TimeChainImple;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Author ：Created by Jason on 2016/7/12 13:16
 * @Email : 320175912@qq.com
 * @desc : 时间链
 */
public class TimeChainFragment extends Fragment {
    @BindView(R.id.time_chain_pull)
    PullToRefreshListView pullToRefreshListView;
    @BindView(R.id.time_chain_rl_iv)
    ImageView searchIV;
    private TimeChainImple dao = new TimeChainDao();

    public TimeChainFragment() {
    }

    public static TimeChainFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable("path", path);
        TimeChainFragment fragment = new TimeChainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_chain, container, false);
        ButterKnife.bind(this, view);
        final String path = (String) getArguments().getSerializable("path");
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.load_layout, container, false);
        /**操作ui**/
        dao.oprateTimeChain(getContext(), pullToRefreshListView, searchIV, inflate, path);
        return view;
    }
}