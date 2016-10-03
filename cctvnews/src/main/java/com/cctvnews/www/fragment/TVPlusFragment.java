package com.cctvnews.www.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.TVPlusPagerAdapter;
import com.cctvnews.www.config.URL;
import com.cctvnews.www.customview.MyJCVideoPlayerStandard;
import com.cctvnews.www.model.mmodel.LiveDatas;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.logtool.LogTool;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author ：Created by Jason on 2016/7/12 13:18
 * @Email : 320175912@qq.com
 * @desc : 电视+
 */
public class TVPlusFragment extends Fragment {
    private List<Fragment> fragments = new ArrayList<>();
    private LiveDatas.HlsUrlBean hlsUrlBeen;
    @BindView(R.id.tvplus_jiecao_videoview)
    MyJCVideoPlayerStandard jiecaoView;
    @BindView(R.id.tvplus_rg)
    RadioGroup radioGroup;
    @BindView(R.id.tvplus_view_page)
    ViewPager viewPager;
    @BindView(R.id.tvplus_listen_tv)
    TextView listenTV;
    /**
     * 测试数据
     */
    private TVPlusPagerAdapter tVPlusPagerAdapter;
    private boolean flag = false;

    public TVPlusFragment() {
    }

    public static TVPlusFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable("path", path);
        TVPlusFragment fragment = new TVPlusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        jiecaoView.release();
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tvplus, container, false);
        ButterKnife.bind(this, view);
        /**数据**/
        String path = (String) getArguments().getSerializable("path");
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                LiveDatas liveDatas = gson.fromJson(result, LiveDatas.class);
                hlsUrlBeen = liveDatas.getHls_url();
                /**节操vidioView**/
                LogTool.log(getClass(), jiecaoView.toString());
                jiecaoView.setUp(hlsUrlBeen.getHls4(), "");
                jiecaoView.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                jiecaoView.thumbImageView.setImageResource(R.drawable.tv_back);
                /**节操vidioView**/
            }
        });
        fragments.add(ProgramFragment.newInstance(jiecaoView));
        fragments.add(JokerFragment.newInstance(""));
        fragments.add(DialogFragment.newInstance(URL.DIALOG_DATA));
        /**适配器**/
        tVPlusPagerAdapter = new TVPlusPagerAdapter(getFragmentManager(), fragments);
        /**绑定适配器**/
        viewPager.setAdapter(tVPlusPagerAdapter);
        /**监听(begin)：给RadioGroup 设置点击事件*/
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tvplus_rb_one:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.tvplus_rb_two:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tvplus_rb_three:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        /**监听(end)：给RadioGroup 设置点击事件*/
        listenTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    LogTool.log(getClass(), jiecaoView.toString());
                    listenTV.setTextColor(getResources().getColor(R.color.blue));
                    jiecaoView.setUp(hlsUrlBeen.getHls4(), "");
                    jiecaoView.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    jiecaoView.thumbImageView.setImageResource(R.drawable.tv_back);
                    flag = false;
                } else {
                    LogTool.log(getClass(), jiecaoView.toString());
                    listenTV.setTextColor(getResources().getColor(R.color.gray));
                    jiecaoView.setUp(hlsUrlBeen.getHls6(), "");
                    jiecaoView.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    jiecaoView.thumbImageView.setImageResource(R.drawable.ic_ting_dian_shi);
                    jiecaoView.startButton.performClick();
                    flag = true;
                }
            }
        });
        radioGroup.check(R.id.tvplus_rb_one);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.tvplus_rb_one);
                        break;
                    case 1:
                        radioGroup.check(R.id.tvplus_rb_two);
                        break;
                    case 2:
                        radioGroup.check(R.id.tvplus_rb_three);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }

}
