package com.cctvnews.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cctvnews.www.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public class HudongFragment extends Fragment {
    @BindView(R.id.hudong_pullToRefreshListView)
    PullToRefreshListView hudong_pullToRefreshListView;

    public HudongFragment() {
    }

    public static HudongFragment newInstance(String path) {

        Bundle args = new Bundle();
        args.putSerializable("path", path);
        HudongFragment fragment = new HudongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hudong, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
