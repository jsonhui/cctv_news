package com.cctvnews.www.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cctvnews.www.R;
import com.cctvnews.www.presenter.actdao.StatementDao;
import com.cctvnews.www.presenter.actimple.StatementImple;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatementActivity extends UMActivity {
    private Context context = this;
    @BindView(R.id.statement_viewpager)
    ViewPager viewPager;
    @BindView(R.id.statement_title_rl)
    RelativeLayout titleRL;
    @BindView(R.id.statement_reject_btn)
    Button rejectBTN;
    @BindView(R.id.statement_agree_btn)
    Button agreeBTN;
    private StatementImple dao = new StatementDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        ButterKnife.bind(this);
        /**做引导(内部判断，不引导就不再执行下面的代码)**/
        dao.doStatement(context, viewPager, titleRL, rejectBTN, agreeBTN);
    }
}
