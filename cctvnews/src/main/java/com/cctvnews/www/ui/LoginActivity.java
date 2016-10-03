package com.cctvnews.www.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cctvnews.www.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class LoginActivity extends UMActivity {
    @BindView(R.id.login_back)
    ImageView login_back;
    @BindView(R.id.login_register)
    TextView login_register;
    @BindView(R.id.login_wangjimima_tv)
    TextView wangjimima;
    @BindView(R.id.login_login_rb)
    RadioButton login_rb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //监听
        initListener();
    }

    private void initListener() {
        /**返回键 back的监听**/
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
