package com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed.modify;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.EditOrderPriceBean;
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
 * Created by Administrator on 2017/8/3 0003.
 */

public class ModifyPricePresenter implements ModifyPriceContract.Presenter{
    ModifyPriceContract.View view;
    private String token;
    public ModifyPricePresenter(ModifyPriceContract.View view) {
        this.view=view;

    }

    @Override
    public void attachView(@NonNull ModifyPriceContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void getOrderFromServer(String orderId) {
        view.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("order_sn",orderId);
        meApi.getEditOrders(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status==1){
                                EditOrderPriceBean beass = JSON.parseObject(data,EditOrderPriceBean.class);
                                view.getOrderSucceed(beass);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void modificationPrice(String order_sn, String express, String goods_id, String type, String money) {
        view.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        map.put("express_amount",express);
       String goods_ids[] =  goods_id.split(",");
        for (int i = 0; i < goods_ids.length; i++) {
        map.put("order_goods_id["+i+"]", goods_ids[i]);
            System.out.println("order_goods_id["+i+"]:"+ goods_ids[i]);
        }

        String types[] =  type.split(",");
        for (int i = 0; i < goods_ids.length; i++) {
            map.put("discount_type["+i+"]", types[i]);
            System.out.println("discount_type["+i+"]:"+ types[i]);
        }

        String moneys[] =  money.split(",");

        for (int i = 0; i < goods_ids.length; i++) {
            map.put("discount_money["+i+"]", moneys[i]);
            System.out.println("discount_money["+i+"]:"+ moneys[i]);


        }

        meApi.getSaveEditOrders(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){
                                view.modificationPriceSuccreed();
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
                        view.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }


}
