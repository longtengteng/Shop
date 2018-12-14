package com.lnkj.privateshop.ui.mybuy.sell.order.seach;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.SeachOrderBean;
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
 * Created by Administrator on 2017/8/7 0007.
 */

public class SeachOrderPresenter implements SeachOrderContract.Presenter {
    SeachOrderContract.View mView;
    private String token;

    public SeachOrderPresenter(SeachOrderContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull SeachOrderContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void clearAttention(int type) {

    }

    @Override
    public void getDataFromService(String keyword) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("keyword", keyword);
        meApi.searchOrder(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){
                            SeachOrderBean beass = JSON.parseObject(data, SeachOrderBean.class);
                            mView.getOrderData(beass);
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
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void onRemindPay(String order_sn) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("order_sn", order_sn);
        meApi.onRemindPay(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            ToastUtil.showToast(info);
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
    public void onDeleteOrder(String order_sn, int type) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_status", type);
        changeOrder(map, order_sn, type);
    }


    @Override
    public void onRemindDelivery(String order_sn, int position) {
//        //前往发货
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("order_status",101);
//        changeOrder(map,orderlist,position,101);
    }


    public void changeOrder(Map<String, Object> map, String order_sn, final int flag) {
        map.put("token", token);
        map.put("order_sn", order_sn);
        meApi.chanageOrderStatus(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                if (flag == 100) {
                                    mView.remoOrder();
                                } else if (flag == 0) {
                                    mView.okGoods();
                                } else if (flag == 101) {//发货成功
                                    mView.remindDelivery(info);
                                }
                            }
                                ToastUtil.showToast(info);

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
