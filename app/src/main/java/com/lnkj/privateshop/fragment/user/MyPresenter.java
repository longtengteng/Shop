package com.lnkj.privateshop.fragment.user;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.api.me.MeApi;
import com.lnkj.privateshop.entity.ServiceEmchatBean;
import com.lnkj.privateshop.entity.UseInfoBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MyPresenter implements MyContract.Presenter {
    private String token;
    private MeApi meApi;
    MyContract.View view;

    public MyPresenter(MyContract.View view) {
       this.view = view;
        meApi = new MeApi();
    }
    @Override
    public void getDataFromService() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        meApi.getUserInfo(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据321", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){
                                UseInfoBean beass = JSON.parseObject(data,UseInfoBean.class);
                                view.getUserInfoSuccreed(beass);
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
    public void setToken(String token) {
        this.token=token;


}

    @Override
    public void getOrderData() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        meApi.getOredrData(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){

                            JSONObject bean = object.getJSONObject("data");
                            int status_1 = bean.getInt("status_1");
                            int status_2 = bean.getInt("status_2");
                            int status_3 = bean.getInt("status_3");
                            String status_4 = bean.getString("status_4");
                            String status_5 = bean.getString("status_5");
                                view.getOrderDataSuccreed(status_1,status_2,status_3,status_4,status_5);

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
    public void getServiceInfo() {
        view.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        meApi.getServiceInfo(map).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String data) {
                LLog.d("数据", data);
                try {
                    view.hideLoading();
                    JSONObject object = new JSONObject(data);
                    ServiceEmchatBean bean = JSON.parseObject(data, ServiceEmchatBean.class);
                    view.getServiceInfo(bean.getData());
                } catch (Exception e) {
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
    public void attachView(@NonNull MyContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void clearAttention(int type) {

    }

}
