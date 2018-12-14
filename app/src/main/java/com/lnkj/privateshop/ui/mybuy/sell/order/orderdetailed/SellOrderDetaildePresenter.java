package com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.SellOrderDetaildeBean;
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

public class SellOrderDetaildePresenter implements SellOrderDetaildeContract.Presenter {

    SellOrderDetaildeContract.View mView;
    private String token;
    public SellOrderDetaildePresenter(SellOrderDetaildeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull SellOrderDetaildeContract.View view) {

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
    public void getOrderFromServer(String orderId) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("order_sn",orderId);
        meApi.getSellOrderDetailde(map)
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
                                SellOrderDetaildeBean beass = JSON.parseObject(data,SellOrderDetaildeBean.class);
                                mView.getOrderSucceed(beass);
                            }else {
                                ToastUtil.showToast("info");
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
    public void onOkGoods(String orderId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",101);
        changeOrder(map,orderId,101);
    }

    @Override
    public void onRemindDelivery(String orderId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",4);
        changeOrder(map,orderId,4);
    }

    @Override
    public void onDeleteOrder(String orderId, int type) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",type);
        changeOrder(map,orderId,type);
    }

    @Override
    public void onRemindPay(String order_sn) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("order_sn",order_sn);
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
                            String info  = object.getString("info");
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

    public void changeOrder(Map<String,Object> map, String orderid ,final int flag){
        map.put("token",token);
        map.put("order_sn",orderid);
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
                            String info =object.getString("info");
                            if (status==1){
                            if (flag==100){
                                mView.remoOrder();
                            }else {
                            mView.okGoods();

                            }
                            }
//                            if (flag==100){
//                                mView.remoOrder(orderlist,position,info);
//                            }else if (flag==4){
//                                mView.okGoods();
//                            }else if (flag==101){
//                                mView.remindDelivery(info);
//                            }
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
