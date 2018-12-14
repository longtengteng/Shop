package com.lnkj.privateshop.ui.ease.systemnews.noticedetails;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.utils.LLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

public class NoticeDetailsActivity extends BaseActivity {


    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_title)
    TextView tvTitle;
//    @Bind(R.id.tv_content)
//    TextView tvContent;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title_e)
    TextView tvTitle_e;
    private String Article_id;
    @Bind(R.id.wb_content)
    WebView wb_content;
    @Override
    public int initContentView() {
        return R.layout.activity_notice_details;
    }


    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("公告详情");
        Article_id = getIntent().getStringExtra("Article_id");
//        tvTime.setText(getIntent().getStringExtra("time"));
//        tvTitle_e.setText(getIntent().getStringExtra("titel"));
//        tvContent.setText(getIntent().getStringExtra("content"));
//        meRlParent.setOnTouchListener(this);
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

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("article_id", Article_id);
        meApi.helpDetail(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("数据111", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                JSONObject objectdata = object.getJSONObject("data");
                                tvTime.setText(objectdata.getString("addtime"));
                                tvTitle_e.setText(objectdata.getString("title"));
                                String content = objectdata.getString("content");
//                                tvContent.setText(Html.fromHtml(content));
                                wb_content.setVisibility(View.VISIBLE);
                                wb_content.loadDataWithBaseURL(null, content + "", "text/html", "utf-8", null);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });


    }


    @Override
    public void initUiAndListener() {


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
    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

}
