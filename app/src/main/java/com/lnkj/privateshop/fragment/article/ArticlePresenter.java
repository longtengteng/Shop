package com.lnkj.privateshop.fragment.article;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ArticleBean;
import com.lnkj.privateshop.entity.ArticleCateBean;
import com.lnkj.privateshop.entity.LimitSaleListBean;
import com.lnkj.privateshop.ui.limitsalelist.LimitSaleContract;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

public class ArticlePresenter implements ArticleContract.Presenter {

    private ArticleContract.View mView;
    private String token;

    public ArticlePresenter(ArticleContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void getDataFromServer(int page, String article_category_id) {
        Map<String, Object> map = new HashMap<String, Object>();
   //     map.put("token", token);
        map.put("p", page);
        map.put("article_category_id",article_category_id);
        mView.showLoading();
        meApi.article(map)
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
                            if (status==1){
                                ArticleBean beass = JSON.parseObject(data, ArticleBean.class);
                                mView.succeed(beass);
                            }else {
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
    public void getArticleCategory() {
        Map<String, Object> map = new HashMap<String, Object>();
        //     map.put("token", token);
        mView.showLoading();
        meApi.articleCategory(map)
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
                            if (status==1){
                                ArticleCateBean beass = JSON.parseObject(data, ArticleCateBean.class);
                                mView.getArticleCategory(beass);
                            }else {
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

    }

    @Override
    public void getSize() {

    }

    @Override
    public void attachView(@NonNull ArticleContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
