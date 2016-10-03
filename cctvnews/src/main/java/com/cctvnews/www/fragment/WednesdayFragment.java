package com.cctvnews.www.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.ProgramAdapter;
import com.cctvnews.www.customview.MyJCVideoPlayerStandard;
import com.cctvnews.www.model.mmodel.ProgramDatas;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Json on 2016/7/15 20:11
 * 邮箱：320175912@qq.com
 */
public class WednesdayFragment extends BaseFragment {
    private List<ProgramDatas.Cctv13Bean.ProgramBean> programBeen = new ArrayList<>();
    private ProgramAdapter programAdapter;
    @BindView(R.id.wednesday_list)
    ListView wednesdayList;

    public WednesdayFragment() {
    }

    public static WednesdayFragment newInstance(String path, MyJCVideoPlayerStandard jiecaoView) {

        Bundle args = new Bundle();
        args.putString("path", path);
        args.putSerializable("jiecaoView", jiecaoView);
        WednesdayFragment fragment = new WednesdayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_wednesday, container, false);
        ButterKnife.bind(this, view);
        String path = getArguments().getString("path");
        final MyJCVideoPlayerStandard jiecaoView = (MyJCVideoPlayerStandard) getArguments().getSerializable("jiecaoView");
        /**适配器**/
        programAdapter = new ProgramAdapter(getContext(), programBeen, getClass().getSimpleName(), sharedPreferences, sharedPreferences1, jiecaoView);
        /**绑定适配器**/
        wednesdayList.setAdapter(programAdapter);
        /**数据**/
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                ProgramDatas programDatas = gson.fromJson(result, ProgramDatas.class);
                List<ProgramDatas.Cctv13Bean.ProgramBean> Been = programDatas.getCctv13().getProgram();
                programBeen.clear();
                programBeen.addAll(Been);
                programAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}
