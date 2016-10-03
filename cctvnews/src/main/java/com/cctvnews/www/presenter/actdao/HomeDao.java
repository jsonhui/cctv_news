package com.cctvnews.www.presenter.actdao;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cctvnews.www.R;
import com.cctvnews.www.config.URL;
import com.cctvnews.www.fragment.DiscoverFragment;
import com.cctvnews.www.fragment.MeFragment;
import com.cctvnews.www.fragment.NewsFragment;
import com.cctvnews.www.fragment.TVPlusFragment;
import com.cctvnews.www.fragment.TimeChainFragment;
import com.cctvnews.www.presenter.actimple.HomeImple;
import com.cctvnews.www.tool.logtool.LogTool;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;

/**
 * 作者：Json on 2016/7/21 19:17
 * 邮箱：320175912@qq.com
 */
public class HomeDao implements HomeImple {
    private NewsFragment newsFragment;
    private TimeChainFragment timeChainFragment;
    private TVPlusFragment tvPlusFragment;
    private DiscoverFragment discoverFragment;
    private MeFragment meFragment;

    @Override
    public void openUMPush(Context context) {
        /**开启推送服务**/
        PushAgent mPushAgent = PushAgent.getInstance(context);
        /**sdk开启通知声音**/
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        mPushAgent.enable(new IUmengRegisterCallback() {
            @Override
            public void onRegistered(String s) {
                LogTool.log(this.getClass(), s);
            }
        });
        LogTool.log(this.getClass(), "开启:" + mPushAgent.isEnabled() + "");
        /**测试平台begin**/
        /**获得meta-data标签中的value**/
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName()
                    , PackageManager.GET_META_DATA);
            Object channel = applicationInfo.metaData.get("UMENG_CHANNEL");
            LogTool.log(this.getClass(), "onCreate: channel===" + channel);
            if ("_360".equals(channel)) {
                /**360的逻辑 TODO**/
            } else if ("xiaomi".equals(channel)) {
                /**xiaomi的逻辑 TODO**/
            } else if ("huawei".equals(channel)) {
                /**huawei的逻辑 TODO**/
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        /**测试平台end**/
    }

    @Override
    public void listenHome(Context context, final FragmentManager fragmentManager, RadioGroup rg_all, RadioButton home_news_rb) {
        /** 给RadioGroup 设置点击事件*/
        rg_all.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                /** 隐藏所有的fragment*/
                hideAllFragment(fragmentTransaction);
                switch (checkedId) {
//                    case R.id.home_news_rb:
//                        if (newsFragment == null) {
//                            newsFragment = NewsFragment.newInstance(URL.TAB_NAMES);
//                            fragmentTransaction.add(R.id.home_framrlayout, newsFragment);
//                        } else {
//                            fragmentTransaction.show(newsFragment);
//                        }
//                        break;
                    case R.id.home_timechain_rb:
                        if (timeChainFragment == null) {
                            timeChainFragment = TimeChainFragment.newInstance(URL.TIME_CHAIN);
                            fragmentTransaction.add(R.id.home_framrlayout, timeChainFragment);
                        } else {
                            fragmentTransaction.show(timeChainFragment);
                        }
                        break;
                    case R.id.home_tvplus_rb:
                        if (tvPlusFragment == null) {
                            tvPlusFragment = TVPlusFragment.newInstance(URL.LIVE_DATA);
                            fragmentTransaction.add(R.id.home_framrlayout, tvPlusFragment);
                        } else {
                            fragmentTransaction.show(tvPlusFragment);
                        }
                        break;
                    case R.id.home_discover_rb:
                        if (discoverFragment == null) {
                            discoverFragment = DiscoverFragment.newInstance(URL.DISCOVER_DATA);
                            fragmentTransaction.add(R.id.home_framrlayout, discoverFragment);
                        } else {
                            fragmentTransaction.show(discoverFragment);
                        }
                        break;
                    case R.id.home_me_rb:
                        if (meFragment == null) {
                            meFragment = MeFragment.newInstance("");
                            fragmentTransaction.add(R.id.home_framrlayout, meFragment);
                        } else {
                            fragmentTransaction.show(meFragment);
                        }
                        break;
                }
                fragmentTransaction.commit();
            }
        });
        /**设置第一个按钮为选中状态**/
        home_news_rb.setChecked(true);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (newsFragment != null) fragmentTransaction.hide(newsFragment);
        if (timeChainFragment != null) fragmentTransaction.hide(timeChainFragment);
        if (tvPlusFragment != null) fragmentTransaction.hide(tvPlusFragment);
        if (meFragment != null) fragmentTransaction.hide(meFragment);
        if (discoverFragment != null) fragmentTransaction.hide(discoverFragment);
    }
}
