package com.cctvnews.www.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cctvnews.www.adapter.ProgramAdapter;
import com.cctvnews.www.tool.logtool.LogTool;

/**
 * 作者：Json on 2016/7/20 18:17
 * 邮箱：320175912@qq.com
 */
public class BaseFragment extends Fragment {
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences sharedPreferences1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("预约", getContext().MODE_PRIVATE);
        sharedPreferences1 = getContext().getSharedPreferences("直播", getContext().MODE_PRIVATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogTool.log(getClass(), "onDestroy---------");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogTool.log(getClass(), "onDestroy---------");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogTool.log(getClass(), "onDestroy---------");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogTool.log(getClass(), "onResume---------");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogTool.log(getClass(), "onPause---------");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            LogTool.log(getClass(), "onHiddenChanged-pause");
            /**TODO刷新所有的适配器**/
            for (int i = 0; i < ProgramAdapter.ALL_INSTANTS.size(); i++) {
                ProgramAdapter.ALL_INSTANTS.get(i).notifyDataSetChanged();
            }
        } else {
            LogTool.log(getClass(), "onHiddenChanged-resume");
        }
    }
}
