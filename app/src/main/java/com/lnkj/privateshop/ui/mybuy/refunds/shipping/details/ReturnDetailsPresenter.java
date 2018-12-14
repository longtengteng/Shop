package com.lnkj.privateshop.ui.mybuy.refunds.shipping.details;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.RetrunDetailsBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;
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
 * Created by Administrator on 2017/8/25 0025.
 */

public class ReturnDetailsPresenter implements ReturnDetailsContract.Presenter {
    ReturnDetailsContract.View mView;
    String token;

    public ReturnDetailsPresenter(ReturnDetailsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull ReturnDetailsContract.View view) {

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
                                RetrunDetailsBean beass = JSON.parseObject(data,RetrunDetailsBean.class);
//                                mView.getShipPingData(beass);
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
    public void ShipPingGoods(String order_sn, String order_id) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        map.put("order_goods_id",order_id);
        System.out.println("token:"+token);
        System.out.println("order_sn:"+order_sn);
        System.out.println("order_goods_id:"+order_id);


        mView.showLoading();
        meApi.ReturnDetails2(map)
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
    public void onRevocation(String order_sn) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        mView.showLoading();
        meApi.goBackRefundOrder(map)
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
                                mView.RevocationSucreed();
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
    public void onDeleteOrder(String Order_sn) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",Order_sn);
        meApi.onMaiDeleteOrder(map)
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
                                mView.DeleteSuccreed();
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
    public void getServiceInfo() {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        meApi.getServiceInfo(map).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String data) {
                try {
                    mView.hideLoading();
                    JSONObject object = new JSONObject(data);
                    ServiceEmchatBean bean = JSON.parseObject(data, ServiceEmchatBean.class);
                    mView.getServiceInfo(bean.getData());
                } catch (Exception e) {
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
