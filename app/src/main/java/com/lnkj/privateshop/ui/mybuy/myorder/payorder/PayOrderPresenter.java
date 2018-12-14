package com.lnkj.privateshop.ui.mybuy.myorder.payorder;

import android.support.annotation.NonNull;

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

public class PayOrderPresenter implements PayOrderContract.Presenter {
    private PayOrderContract.View mView;

    private String token;

    public PayOrderPresenter(PayOrderContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull PayOrderContract.View view) {

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
    public void payWxpay(String order_sn) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("bond_money", order_sn);
        map.put("pay_code", "2");
        meApi.getPayshopPayBond(map)
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
                                JSONObject datas = object.getJSONObject("data");
                                mView.payWxpaySuccreed(datas);
                            } else {
                                String info = object.getString("info");
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
    public void payMent(String bond_money) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("bond_money", bond_money);
        map.put("pay_code", "1");
        meApi.getPayshopPayBond(map)
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
                                String datas = object.getString("data");
                                mView.payMentSuccreed(datas);
                            } else {
                                String info = object.getString("info");
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
    public void payYue(String price) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("bond_money", price);
        map.put("pay_code", "4");
        meApi.getPayshopPayBond(map)
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
                            String datas = object.getString("data");
                            if (status == 1) {
                                mView.payYueSuccreed(datas);
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
}
