package com.lnkj.privateshop.ui.shop.shopInfo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ShopArchivesBean;
import com.lnkj.privateshop.entity.ShopBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class ShopInfoPresenter implements ShopInfoContract.Presenter {
    private ShopInfoContract.View mView;
    private Context mContext;
    private String token;

    public ShopInfoPresenter(ShopInfoContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void attachView(@NonNull ShopInfoContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

    }


    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void getShomInfo(String shopid) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
     map.put("shop_id", shopid);
        meApi.getShopInfo(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("店铺详情数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                ShopBean beass = JSON.parseObject(data, ShopBean.class);
                                mView.getShomInfoSucceed(beass);
                            } else {
                                ToastUtil.showToast(info);
                                mView.finsh();
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
    public void setCollectShop(String shopid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shop_id", shopid);
        map.put("token", token);
        meApi.setCollectShop(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                mView.SetColloectShopSuccreed();
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
    public void getShopArchives(String shopid) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("shop_id", shopid);
        meApi.getShopArchives(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                ShopArchivesBean beass = JSON.parseObject(data, ShopArchivesBean.class);
                                mView.getShopArchivesSucceed(beass);
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
