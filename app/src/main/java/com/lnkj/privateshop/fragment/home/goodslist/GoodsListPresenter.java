package com.lnkj.privateshop.fragment.home.goodslist;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.AdvertisingBean;
import com.lnkj.privateshop.entity.HomeGoodsListbean;
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

public class GoodsListPresenter implements GoodsListContract.Presenter {

    GoodsListContract.View mView;
    private String token;
    public GoodsListPresenter(GoodsListContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull GoodsListContract.View view) {

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
    public void getDataFromServer(int p,String cat_id) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("p",p);
        map.put("cat_id",cat_id);
//        System.out.println("p"+p);
//        System.out.println("map_id"+map_id);
//        System.out.println("token"+token);
        meApi.getHomeGoodsList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status==1){
                                HomeGoodsListbean beass = JSON.parseObject(data,HomeGoodsListbean.class);
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
