package com.lnkj.privateshop.ui.mybuy.refunds.shipping;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ShipPingBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class ShipPingReturnPresenter implements ShipPingReturnContract.Presenter {
    ShipPingReturnContract.View mView;
    String token;

    public ShipPingReturnPresenter(ShipPingReturnContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull ShipPingReturnContract.View view) {

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
    public void getShipPingDataFromServer() {
        mView.showLoading();
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        meApi.getShipPing(map)
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
                            ShipPingBean beass = JSON.parseObject(data,ShipPingBean.class);
                                mView.getShipPingData(beass);
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
    public void ShipPingGoods(String order_sn, String express_id, String express_code,String express_name) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        map.put("express_id",express_id);
        map.put("express_name",express_name);
        map.put("express_code",express_code);

        System.out.println("token:"+token);
        System.out.println("order_sn:"+order_sn);
        System.out.println("express_id:"+express_id);
        System.out.println("express_name:"+express_name);
        System.out.println("express_code:"+express_code);

        if (TextUtils.isEmpty(express_code)){
            ToastUtil.showToast("请填写物流单号");
            return;
        }else if (TextUtils.isEmpty(express_id)){
            ToastUtil.showToast("请选择物流公司");
            return;
        }else if (TextUtils.isEmpty(order_sn)){
            ToastUtil.showToast("单号不能为空");
            return;
        }
        mView.showLoading();
        meApi.ReturnShipping(map)
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
                                mView.ShopPingGoodsSuccree();
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
