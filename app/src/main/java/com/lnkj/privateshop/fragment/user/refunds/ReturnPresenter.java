package com.lnkj.privateshop.fragment.user.refunds;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ReutrnBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class ReturnPresenter implements ReturnContract.Presenter{
    ReturnContract.View view;
    private String token;
    public ReturnPresenter(ReturnContract.View view) {
        this.view=view;

    }

    @Override
    public void attachView(@NonNull ReturnContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void getReturnOrder(String shop_state) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("shop_state",shop_state);
//        String shop_id = PreferencesUtils.getString(MyApplication.mContext,"shop_id");
//        map.put("shop_id",shop_id);
        meApi.ReturnDetails(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){
                                ReutrnBean beass = JSON.parseObject(data,ReutrnBean.class);
                                view.succree(beass);
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
                        view.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });



    }

    @Override
    public void onDeleteOrder(String Order_sn, final int position) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",Order_sn);
        meApi.onMaiDeleteOrder(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){
                                view.DeleteSuccreed();
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
    public void onRevocation(String order_sn, final int position) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        meApi.goBackRefundOrder(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){
                               view.RevocationSucreed(position);
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
