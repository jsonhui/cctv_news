package com.cctvnews.www.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cctvnews.www.tool.logtool.LogTool;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * 作者：Json on 2016/7/18 21:25
 * 邮箱：320175912@qq.com
 */

public class SweepActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        LogTool.log(getClass(), "-rawResult" + rawResult.getText());// Prints scan results
        LogTool.log(getClass(), rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        /**获得数据跳转**/
        if (!"".equals(rawResult.getText().toString()) && rawResult.getText() != null) {
            Intent intent = new Intent();
            intent.putExtra("url", rawResult.getText().toString());
            intent.setClass(getApplicationContext(), WebActivity.class);
            startActivity(intent);
        }
        mScannerView.resumeCameraPreview(this);
    }
}