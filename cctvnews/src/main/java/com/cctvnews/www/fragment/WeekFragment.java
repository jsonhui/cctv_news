package com.cctvnews.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.cctvnews.www.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Json on 2016/7/15 15:28
 * 邮箱：320175912@qq.com
 */
public class WeekFragment extends Fragment {
    @BindView(R.id.week_text)
    TextView weekTV;
    @BindView(R.id.week_listview)
    ListView weekListView;

    public WeekFragment() {
    }

    public static WeekFragment newInstance(int flag) {
        Bundle args = new Bundle();
        args.putInt("flag", flag);
        WeekFragment fragment = new WeekFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        ButterKnife.bind(this, view);
        int flag = getArguments().getInt("flag");
        weekTV.setText("第" + flag + "页");
        return view;
    }
}
