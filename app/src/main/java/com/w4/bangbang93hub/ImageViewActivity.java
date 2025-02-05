package com.w4.bangbang93hub;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.widget.Toast;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.w4.bangbang93hub.R;

public class ImageViewActivity extends android.app.Activity {
    private static final String TAG = "ImageViewActivity";
    private static final int REQUEST_PERMISSION_CODE = 1001;
    private static final int REQUEST_FILE_CHOOSER = 1002;

    private WebView mWebView;
    private ValueCallback<Uri[]> mFilePathCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 初始化腾讯X5内核
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
                @Override
                public void onCoreInitFinished() {
                    Log.d(TAG, "X5 Core initialized");
                    LogUtil.log(TAG, "X5 Core initialized");
                }

                @Override
                public void onViewInitFinished(boolean b) {
                    Log.d(TAG, "X5 WebView initialized: " + b);
                    LogUtil.log(TAG, "X5 WebView initalized: " + b);
                }
            });

        setContentView(R.layout.activity_image_view);
        
        
        
        
        mWebView = findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });

        mWebView.setWebChromeClient(new WebChromeClient() {
            /*
                @Override
                public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                    mFilePathCallback = filePathCallback;
                    Intent intent = fileChooserParams.createIntent();
                    try {
                        startActivityForResult(intent, REQUEST_FILE_CHOOSER);
                    } catch (Exception e) {
                        mFilePathCallback = null;
                        Toast.makeText(MainActivity.this, "Unable to open file chooser", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    return true;
                }
                */
            });

        mWebView.loadUrl("https://api.hesiy.cn/api/bangbang-view"); // 固定网页URL
      //LogUtil.log("Starting to load image URL" + " \n " + "https://api.hesiy.cn/api/bangbang-view");

        // 请求权限
        requestPermissions();
    }

    
    
/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                Toast.makeText(this, "Cannot exit using back key", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
*/

    private long firstBackTime;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstBackTime > 2000) {
            Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
            firstBackTime = System.currentTimeMillis();
            LogUtil.log("Exit request by back key");
            return;
        }

        super.onBackPressed();
        finish();
    }

    private void requestPermissions() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                                   Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                   Manifest.permission.READ_EXTERNAL_STORAGE
                               }, REQUEST_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FILE_CHOOSER) {
            if (mFilePathCallback == null) return;
            Uri[] results = null;
            if (resultCode == RESULT_OK && data != null) {
                String dataString = data.getDataString();
                if (dataString != null) {
                    results = new Uri[]{Uri.parse(dataString)};
                }
            }
            mFilePathCallback.onReceiveValue(results);
            mFilePathCallback = null;
        }
    }
}
