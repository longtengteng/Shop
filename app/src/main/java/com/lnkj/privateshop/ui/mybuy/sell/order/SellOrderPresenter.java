package com.lnkj.privateshop.ui.mybuy.sell.order;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.OrderAllBean;
import com.lnkj.privateshop.utils.LLog;

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

public class SellOrderPresenter implements SellOrderContract.Presenter {
    SellOrderContract.View view;
    private String token;

    public SellOrderPresenter(SellOrderContract.View view) {
        this.view = view;

    }

    @Override
    public void getDataFromServer(final int index) {
        view.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("order_status", "");
        System.out.println("token:" + token);
        meApi.getSellOrder(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            OrderAllBean beass = JSON.parseObject(data, OrderAllBean.class);
                            if (index == 1) {
                                view.getOrderData(beass);

                            } else {
                                view.addTabData(beass);
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
    public void attachView(@NonNull SellOrderContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void initView() {

    }

    @Override
    public void exist() {

    }


}
