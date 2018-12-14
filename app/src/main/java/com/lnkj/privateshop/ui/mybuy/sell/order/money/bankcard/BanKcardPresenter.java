package com.lnkj.privateshop.ui.mybuy.sell.order.money.bankcard;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.BankCardBean;
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

public class BanKcardPresenter implements BanKcardContract.Presenter{
    BanKcardContract.View view;
    private String token;
    public BanKcardPresenter(BanKcardContract.View view) {
        this.view=view;

    }
    @Override
    public void getDataFromServer() {
        view.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
       meApi.getBankCardList(map)
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
                         BankCardBean beans = JSON.parseObject(data,BankCardBean.class);
                         view.setData(beans);
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
    public void delteBankCard(String id) {
        view.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("id",id);
        meApi.deleteBankCard(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.optString("info");
                            if (status==1){
                               view.deleteBankCardSuccreed();
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

    @Override
    public void attachView(@NonNull BanKcardContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }




}
