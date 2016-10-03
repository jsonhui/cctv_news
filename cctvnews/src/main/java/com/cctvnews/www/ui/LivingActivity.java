package com.cctvnews.www.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.LivingPagerAdapter;
import com.cctvnews.www.fragment.HudongFragment;
import com.cctvnews.www.fragment.LivingFragment;
import com.cctvnews.www.model.mmodel.LivingDatas;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class LivingActivity extends UMActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
  //  private LivingDatas livingDatas;
    private LivingDatas.DataBean dataBean;
    String url;
    @BindView(R.id.living_big_rl)
    RelativeLayout living_big_rl;
    @BindView(R.id.living_back_fl)
    ImageView living_back;
    @BindView(R.id.living_share_fl)
    ImageView living_share;
    @BindView(R.id.living_up)
    ImageView living_up;
    @BindView(R.id.living_living_rb)
    RadioButton living_living_rb;
    @BindView(R.id.living_hudong_rb)
    RadioButton living_hudong_rb;
    @BindView(R.id.living_tittle_tv)
    TextView living_tittle_tv;
    @BindView(R.id.living_tittle_fl)
    TextView living_tittle_fl;
    @BindView(R.id.living_fl2)
    FrameLayout living_fl2;
    @BindView(R.id.living_vp)
    ViewPager viewPager;
    @BindView(R.id.living_rg)
    RadioGroup living_rg;
    @BindView(R.id.living_big_iv)
    ImageView bigImage_iv;
    private Animation animation, animation1, animation2, animation3;
    private LivingPagerAdapter livingPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living);
        ButterKnife.bind(this);
        /**设置动画**/
        animation = AnimationUtils.loadAnimation(this, R.anim.tween);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation1 = AnimationUtils.loadAnimation(this, R.anim.tween2);
        animation1.setDuration(500);
        animation1.setFillAfter(true);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.rotate2);
        animation3 = AnimationUtils.loadAnimation(this, R.anim.rotate3);
        /**数据**/
        initData();
        setUpData();
        /**适配器**/
        livingPagerAdapter = new LivingPagerAdapter(getSupportFragmentManager(), fragmentList);
        /**绑定适配器**/
        viewPager.setAdapter(livingPagerAdapter);

        /**设置监听**/
        initListener();
    }

    private void setUpData() {
        this.url = getIntent().getStringExtra("url");
        OkHttpTool.newInstance().start(url).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                LivingDatas livingDatas = gson.fromJson(result,LivingDatas.class);
                dataBean = livingDatas.getData();
                living_tittle_tv.setText(dataBean.getLiveTitle());
                living_tittle_fl.setText(dataBean.getLiveTitle());
                Picasso.with(getApplicationContext()).load(dataBean.getLiveImage()).into(bigImage_iv);
            }
        });
    }

    private void initData() {
        this.url = getIntent().getStringExtra("url");
        fragmentList.add(LivingFragment.newInstance(url));
        fragmentList.add(HudongFragment.newInstance(""));

    }

    private void initListener() {
        living_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                living_up.startAnimation(animation2);
                living_big_rl.startAnimation(animation);
                living_big_rl.setVisibility(View.GONE);
                living_tittle_tv.setVisibility(View.INVISIBLE);
                living_tittle_fl.setVisibility(View.VISIBLE);
            }
        });
        living_fl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                living_big_rl.startAnimation(animation1);
                living_up.startAnimation(animation3);
                living_tittle_tv.setVisibility(View.VISIBLE);
                living_tittle_fl.setVisibility(View.INVISIBLE);
                living_big_rl.setVisibility(View.VISIBLE);
            }
        });
        living_rg.check(R.id.living_living_rb);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        living_rg.check(R.id.living_living_rb);
                        break;
                    case 1:
                        living_rg.check(R.id.living_hudong_rb);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        living_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.living_living_rb:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.living_hudong_rb:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
        living_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
