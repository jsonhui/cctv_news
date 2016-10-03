package com.cctvnews.www.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cctvnews.www.R;
import com.cctvnews.www.config.URL;
import com.cctvnews.www.customview.MyJCVideoPlayerStandard;
import com.cctvnews.www.tool.commontool.HttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramFragment extends Fragment {
    private MondayFragment mondayFragment;
    private TuesdayFragment tuesdayFragment;
    private WednesdayFragment wednesdayFragment;
    private ThursdayFragment thursdayFragment;
    private FridayFragment fridayFragment;
    private SaturdayFragment saturdayFragment;
    private SundayFragment sundayFragment;
    @BindView(R.id.program_rg)
    RadioGroup rg;
    private FragmentManager fragmentManager;
    private MyJCVideoPlayerStandard jiecaoView;

    public ProgramFragment() {
    }

    public static ProgramFragment newInstance(MyJCVideoPlayerStandard jiecaoView) {
        Bundle args = new Bundle();
        args.putSerializable("jiecaoView", jiecaoView);
        ProgramFragment fragment = new ProgramFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_program, container, false);
        ButterKnife.bind(this, view);
        try {
            jiecaoView = (MyJCVideoPlayerStandard) getArguments().getSerializable("jiecaoView");
        } catch (Exception e) {
            Toast.makeText(getContext(), "网络出现异常", Toast.LENGTH_SHORT).show();
        }
        fragmentManager = getChildFragmentManager();
        /** 给RadioGroup 设置点击事件*/
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                /** 隐藏所有的fragment*/
                hideAllFragment(fragmentTransaction);
                switch (checkedId) {
                    case R.id.program_rb_tuesday:
                        if (tuesdayFragment == null) {
                            String path = URL.TV_PLUS_ITEM + HttpUtils.getNowTime(3);
                            tuesdayFragment = TuesdayFragment.newInstance(path, jiecaoView);
                            fragmentTransaction.add(R.id.program_frame, tuesdayFragment);
                        } else {
                            fragmentTransaction.show(tuesdayFragment);
                        }
                        break;
                    case R.id.program_rb_wednesday:
                        if (wednesdayFragment == null) {
                            String path = URL.TV_PLUS_ITEM + HttpUtils.getNowTime(2);
                            wednesdayFragment = WednesdayFragment.newInstance(path, jiecaoView);
                            fragmentTransaction.add(R.id.program_frame, wednesdayFragment);
                        } else {
                            fragmentTransaction.show(wednesdayFragment);
                        }
                        break;
                    case R.id.program_rb_thursday:
                        if (thursdayFragment == null) {
                            String path = URL.TV_PLUS_ITEM + HttpUtils.getNowTime(1);
                            thursdayFragment = ThursdayFragment.newInstance(path, jiecaoView);
                            fragmentTransaction.add(R.id.program_frame, thursdayFragment);
                        } else {
                            fragmentTransaction.show(thursdayFragment);
                        }
                        break;
                    case R.id.program_today:
                        if (fridayFragment == null) {
                            String path = URL.TV_PLUS_ITEM + HttpUtils.getNowTime(0);
                            fridayFragment = FridayFragment.newInstance(path, jiecaoView);
                            fragmentTransaction.add(R.id.program_frame, fridayFragment);
                        } else {
                            fragmentTransaction.show(fridayFragment);
                        }
                        break;
                    case R.id.program_rb_satuday:
                        if (saturdayFragment == null) {
                            String path = URL.TV_PLUS_ITEM + HttpUtils.getNowTime(-1);
                            saturdayFragment = SaturdayFragment.newInstance(path, jiecaoView);
                            fragmentTransaction.add(R.id.program_frame, saturdayFragment);
                        } else {
                            fragmentTransaction.show(saturdayFragment);
                        }
                        break;
                    case R.id.program_rb_sunday:
                        if (sundayFragment == null) {
                            String path = URL.TV_PLUS_ITEM + HttpUtils.getNowTime(-2);
                            sundayFragment = SundayFragment.newInstance(path, jiecaoView);
                            fragmentTransaction.add(R.id.program_frame, sundayFragment);
                        } else {
                            fragmentTransaction.show(sundayFragment);
                        }
                        break;
                    case R.id.program_rb_monday:
                        if (mondayFragment == null) {
                            String path = URL.TV_PLUS_ITEM + HttpUtils.getNowTime(-3);
                            mondayFragment = MondayFragment.newInstance(path, jiecaoView);
                            fragmentTransaction.add(R.id.program_frame, mondayFragment);
                        } else {
                            fragmentTransaction.show(mondayFragment);
                        }
                        break;

                }
                fragmentTransaction.commit();
            }
        });
        /**设置第一个按钮为选中状态**/
        rg.check(R.id.program_today);
        return view;
    }

    /**
     * 隐藏所有的fragment
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (mondayFragment != null) fragmentTransaction.hide(mondayFragment);
        if (tuesdayFragment != null) fragmentTransaction.hide(tuesdayFragment);
        if (wednesdayFragment != null) fragmentTransaction.hide(wednesdayFragment);
        if (thursdayFragment != null) fragmentTransaction.hide(thursdayFragment);
        if (fridayFragment != null) fragmentTransaction.hide(fridayFragment);
        if (saturdayFragment != null) fragmentTransaction.hide(saturdayFragment);
        if (sundayFragment != null) fragmentTransaction.hide(sundayFragment);
    }

}
