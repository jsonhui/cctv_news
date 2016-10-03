package com.cctvnews.www.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cctvnews.www.R;
import com.cctvnews.www.presenter.actdao.HomeDao;
import com.cctvnews.www.presenter.actimple.HomeImple;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends UMActivity {
    private Context mContext = this;
    @BindView(R.id.rg_all)
    RadioGroup rg_all;
    @BindView(R.id.home_news_rb)
    RadioButton home_news_rb;
    private HomeImple dao = new HomeDao();
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        /**开启友盟推送**/
        dao.openUMPush(mContext);
        /**切换按钮**/
        dao.listenHome(mContext, fragmentManager, rg_all, home_news_rb);
    }
}
