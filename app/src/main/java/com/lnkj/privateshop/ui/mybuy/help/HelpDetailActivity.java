package com.lnkj.privateshop.ui.mybuy.help;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HelpDetailActivity extends BaseActivity implements HelpDetailContract.View {
    HelpDetailPresenter mPresenter = new HelpDetailPresenter(this);
    String id;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.wb_content)
    WebView wb_content;

    @Override
    public int initContentView() {
        return R.layout.activity_help_detail;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        id = getIntent().getStringExtra("id");

    }

    @Override
    public void initUiAndListener() {
        WebSettings webSettings = wb_content.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSettings.setUseWideViewPort(true);//关键点

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        wb_content.setInitialScale(5);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(false); // 允许访问文件
        webSettings.setSupportZoom(true); // 支持缩放
        wb_content.setWebViewClient(new MyWebViewClient());
        mPresenter.getPutFromServer(id);
    }
    private class MyWebViewClient extends WebViewClient {
        @Override public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url); // html加载完成之后，调用js的方法
            imgReset();
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    private void imgReset() {
        wb_content.loadUrl("javascript:(function(){" + "var objs = document.getElementsByTagName('img'); " + "for(var i=0;i<objs.length;i++) " + "{" + "var img = objs[i]; " + " img.style.width = '100%'; " + " img.style.height = 'auto'; " + "}" + "})()");
    }
    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void succree(String title, String content, String addtime) {
        wb_content.loadDataWithBaseURL(null, content + "", "text/html", "utf-8", null);
        tvTitle.setText(title);
    }
    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
