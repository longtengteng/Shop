package com.lnkj.privateshop.ui;

import android.net.http.SslError;
import android.os.Build;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;

public class WebActivity extends BaseActivity {
private WebView mWebView;
private ImageView img_back;
    private TextView tv_title;
    @Override
    public int initContentView() {
        return R.layout.activity_web;
    }

    @Override
    public void initInjector() {

        mWebView = (WebView) findViewById(R.id.mWebView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        img_back= (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);

        tv_title.setText(getIntent().getStringExtra("title"));

    }

    @Override
    public void initUiAndListener() {

        final ProgressBar bar = (ProgressBar)findViewById(R.id.myProgressBar);
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        try {

        mWebView.loadUrl(getIntent().getStringExtra("url"));
        }catch (Exception e){}
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWebView.canGoBack()){
                    mWebView.goBack();
                }else {
                    finish();
                }
            }
        });
    }
}
