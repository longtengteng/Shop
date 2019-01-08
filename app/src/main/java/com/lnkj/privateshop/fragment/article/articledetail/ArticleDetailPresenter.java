package com.lnkj.privateshop.fragment.article.articledetail;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ArticleContentBean;
import com.lnkj.privateshop.entity.LimitSaleListBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

public class ArticleDetailPresenter implements ArticleDetailContract.Presenter {
    private String token;
    private ArticleDetailContract.View mView;

    public ArticleDetailPresenter(ArticleDetailContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getToken(String token) {

    }

    @Override
    public void getDataFromServer(String article_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("article_id", article_id);
        mView.showLoading();
        meApi.articleContent(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        mView.PullLoadMoreComplete();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.optString("info");
                            if (status == 1) {
                                ArticleContentBean beass = JSON.parseObject(data, ArticleContentBean.class);
                                mView.succeed(beass);
                            } else {
                                ToastUtil.showToast(info);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mView.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void getSize() {

    }

    @Override
    public void attachView(@NonNull ArticleDetailContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
