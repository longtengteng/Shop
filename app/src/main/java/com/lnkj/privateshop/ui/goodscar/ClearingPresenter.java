package com.lnkj.privateshop.ui.goodscar;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.OrderConBean;
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

public class ClearingPresenter implements ClearingContract.Presenter {
    private ClearingContract.View mView;

    private String token;

    public ClearingPresenter(ClearingContract.View mView) {
        this.mView = mView;

    }

    @Override
    public void attachView(@NonNull ClearingContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void login(String phone, String pwd) {

    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void getGoodsInfo(String goodsid) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("goods_id", goodsid);
        meApi.orderConfirm(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据111", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                OrderConBean beass = JSON.parseObject(data, OrderConBean.class);
                                mView.getGoodsInfoSucceed(beass);
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
    public void addPayOrder(int is_from_cart, String address_id, String shop_id, String remark, String goods_id, String buy_number, String act_id, String goods_spec_key) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("is_from_cart", is_from_cart);
        if (is_from_cart == 0) {
            map.put("goods_id", goods_id);
            map.put("buy_number", buy_number);
            map.put("goods_spec_key", goods_spec_key);
            if (!TextUtils.isEmpty(act_id)) {
                map.put("act_id", act_id);
            }
        } else {
            map.put("shop_id", shop_id + "");
            map.put("remark", remark + "");
        }
        map.put("address_id", address_id + "");
        meApi.createOrder(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据111", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                String objectdata = object.getString("data");
                                mView.addPayOredeSuccreed(objectdata);
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
    public void getAddressPrice(String goods_id, String address_id) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("address_id", address_id);
        map.put("goods_id", goods_id);

//        System.out.println("token:"+token);
//        System.out.println("address_id:"+address_id);
//        System.out.println("goods_id:"+goods_id);

        meApi.cartConfirm(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据111", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                OrderConBean beass = JSON.parseObject(data, OrderConBean.class);
                                mView.getGoodsInfoSucceed(beass);
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
