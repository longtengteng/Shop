package com.lnkj.privateshop.ui.mybuy.sell.order.refunds.details;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.RetrunDetailsBean;
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

public class SellReturnDetailsPresenter implements SellReturnDetailsContract.Presenter {
    SellReturnDetailsContract.View mView;
    String token;

    public SellReturnDetailsPresenter(SellReturnDetailsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull SellReturnDetailsContract.View view) {

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

//

    @Override
    public void ShipPingGoods(String order_sn, String order_id) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        map.put("order_goods_id",order_id);
        mView.showLoading();
        meApi.sellReturnDetails2(map)
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
                                RetrunDetailsBean beass = JSON.parseObject(data,RetrunDetailsBean.class);
                                mView.getShipPingData(beass);
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
    public void OkRoNoGoods(String order_sn, String refund_type,String order_goods_id) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        map.put("refund_type",refund_type);
      //  map.put("order_goods_id",order_goods_id);
        mView.showLoading();
        meApi.sellReturnOkOrNO(map)
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
                                mView.OkRoNoGOodssuccree();
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

    @Override
    public void ReceiveGoods(String order_sn,String pwd) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        map.put("password",pwd);

        mView.showLoading();
        meApi.ReceiveGoods(map)
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
                                mView.OnDeleteOrderSucreed();
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

    @Override
    public void onUrged(String order_sn) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("refund_id",order_sn);
        mView.showLoading();
        meApi.onUrged(map)
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

    @Override
    public void onDeleteOrder(String order_sn) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        mView.showLoading();
        meApi.onDeleteOrder(map)
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
                                mView.OnDeleteOrderSucreed();
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
