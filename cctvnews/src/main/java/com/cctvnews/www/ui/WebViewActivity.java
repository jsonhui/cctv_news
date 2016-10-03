package com.cctvnews.www.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.cctvnews.www.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends UMActivity {

    /**
     * 声明一个WebView视图
     */
    private WebView webView;
    @BindView(R.id.web_back_iv)
    ImageView web_back_iv;

    /**
     * 声明一个数据源（String的Url地址）
     */
    String url;

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        //webView停止加载
        webView.stopLoading();
        //销毁webview
        webView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        /**
         * 初始化视图
         */
        initView();
        /**
         * 初始化数据（对于WebView来说，他的数据就是一个访问的URL地址）
         */
        initData();
        /**
         * 将这个url直接给这个视图就ok了，
         * 这就是WebView的作用所在。
         */
        this.webView.loadUrl(url);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        //webView.setInitialScale(100);
        WebSettings webSettings = webView.getSettings();
        //支持script
        webSettings.setJavaScriptEnabled(true);
        //扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        //放大缩小
        webSettings.setSupportZoom(true);
        //设置可以出现缩放工具
        webSettings.setBuiltInZoomControls(true);
        /**设置监听*/
        initListener();
    }

    private void initListener() {
        web_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化数据（对于WebView来说，他的数据就是一个访问的URL地址）
     */
    private void initData() {
        this.url = getIntent().getStringExtra("url");
    }

    /**
     * 初始化视图
     */
    private void initView() {
        this.webView = (WebView) findViewById(R.id.web_web);
    }
}
