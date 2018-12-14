package com.lnkj.privateshop.fragment.home.goodslist;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.AdvertisingBean;
import com.lnkj.privateshop.entity.FollowBean;
import com.lnkj.privateshop.utils.LLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class FollowPresenter implements FollowContract.Presenter {

    FollowContract.View mView;
    private String token;
    public FollowPresenter(FollowContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull FollowContract.View view) {

    }

    @Override
    public void detachView() {

    }
    @Override
    public void initView() {

    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    @Override
    public void getDataFromServer(int p) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("p",p);
        meApi.getshopFocusGoods(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据关注", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status==1){
                                FollowBean beass = JSON.parseObject(data,FollowBean.class);
                                mView.getGoodsListSucceed(beass);
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
    public void getAdvertising() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        meApi.getadvertising(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据广告____2", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status==1){
                                AdvertisingBean beass = JSON.parseObject(data,AdvertisingBean.class);
                                mView.getAdvertisingSuccreed(beass);
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


}
