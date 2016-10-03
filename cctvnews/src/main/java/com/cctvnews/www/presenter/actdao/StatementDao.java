package com.cctvnews.www.presenter.actdao;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.StatementPagerAdapter;
import com.cctvnews.www.presenter.actimple.StatementImple;
import com.cctvnews.www.ui.WelcomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Json on 2016/7/21 17:29
 * 邮箱：320175912@qq.com
 */
public class StatementDao implements StatementImple {
    private SharedPreferences sharedPreferences;
    private List<View> views = new ArrayList<>();
    private StatementPagerAdapter statementPagerAdapter;
    private SharedPreferences.Editor edit;

    @Override
    public void doStatement(final Context context, final ViewPager viewPager, final RelativeLayout titleRL, Button rejectBTN, Button agreeBTN) {
        sharedPreferences = context.getSharedPreferences("user", context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
        int flag = sharedPreferences.getInt("flag", -1);
        /**为2时直接finish,否则执行下面的功能*/
        if (flag == 2) {
            context.startActivity(new Intent(context, WelcomeActivity.class));
            ((Activity) context).finish();
        } else {
            /**数据**/
            views = new ArrayList<>();
            View view0 = new View(context);
            View view1 = new View(context);
            View view2 = new View(context);
            view0.setBackgroundResource(R.drawable.launch_0);
            view1.setBackgroundResource(R.drawable.launch_1);
            view2.setBackgroundResource(R.drawable.launch_2);
            views.add(view0);
            views.add(view1);
            views.add(view2);
            /**适配器**/
            statementPagerAdapter = new StatementPagerAdapter(views);
            /**绑定适配器**/
            viewPager.setAdapter(statementPagerAdapter);
            /**监听**/
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit.clear();
                    edit.putInt("flag", 2).commit();
                    context.startActivity(new Intent(context, WelcomeActivity.class));
                    ((Activity) context).finish();
                }
            });
            /**监听**/
            agreeBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleRL.setVisibility(View.GONE);
                    viewPager.setVisibility(View.VISIBLE);
                    new AlertDialog
                            .Builder(context)
                            .setCancelable(false)
                            .setTitle("提示")
                            .setMessage("是否开启要闻推送？")
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences sharedPreferencesPush = context.getSharedPreferences("推送", context.MODE_PRIVATE);
                                    sharedPreferencesPush.edit().putInt("flag", 1).commit();
                                    dialog.dismiss();
                                }
                            }).show();
                }
            });
            rejectBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**退出app,重新进来需要引导**/
                    ((Activity) context).finish();
                }
            });
        }
    }
}
