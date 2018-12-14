package com.lnkj.privateshop.ui.mybuy.mredund.recharge;

import android.support.annotation.NonNull;

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

public class RechargePresenter implements RechargeContract.Presenter{
    RechargeContract.View mView;
    private String token;
    public RechargePresenter(RechargeContract.View mView) {
        this.mView=mView;

    }

    @Override
    public void attachView(@NonNull RechargeContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void payWxpay(String order_sn) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("recharge_money",order_sn);
        map.put("pay_code","2");
        meApi.recharge(map)
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
                            JSONObject datas = object.getJSONObject("data");
                            if (status==1){
                                mView.payWxpaySuccreed(datas);
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
    public void payMent(String order_sin) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("recharge_money",order_sin);
        map.put("pay_code","1");
        meApi.recharge(map)
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
                            if (status==1){
                                mView.payMentSuccreed(datas);
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
