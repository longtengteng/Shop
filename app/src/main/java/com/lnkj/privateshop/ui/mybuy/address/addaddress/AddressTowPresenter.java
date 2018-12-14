package com.lnkj.privateshop.ui.mybuy.address.addaddress;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.smatrAddressBean;
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

public class AddressTowPresenter implements AddressTowContract.Presenter {
    AddressTowContract.View view;
    private String token;

    public AddressTowPresenter(AddressTowContract.View view) {
        this.view = view;

    }

    @Override
    public void getDataFromServer(String addressid) {
        view.setClickable();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("address_id",addressid);
        LLog.d("token", token);
        view.showLoading();
        meApi.getAddressDetail(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        view.setClickalbeTrue();
                        view.PullLoadMoreComplete();
                        LLog.d("数据2", data);
                        JSONObject object = null;
                        try {
                            object = new JSONObject(data);
                            int status = object.getInt("status");
                    if (status==1){
                        JSONObject datae =  object.getJSONObject("data");
                        String name =datae.getString("consignee");
                        String address =datae.getString("address");
                        String province =datae.getString("province");
                        String city =datae.getString("city");
                        String district =datae.getString("district");
                        String mobile =datae.getString("mobile");
                        String is_default =datae.getString("is_default");
                        view.setView(name,address,province,city,district,mobile,is_default);
                    }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            view.setClickalbeTrue();
                        }


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.hideLoading();
                        view.setClickalbeTrue();
                        view.PullLoadMoreComplete();
                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });

    }

    @Override
    public void checkData(String addressid,String name, String phone, String address, String street, String detail, Boolean approve) {
        if (TextUtils.isEmpty(name)){
            ToastUtil.showToast("请输入收货人");
        }else if (TextUtils.isEmpty(phone)){
            ToastUtil.showToast("请输入收货人手机号");
        }else if (phone.length()!=11){
            ToastUtil.showToast("请输入正确的手机号");
        }else if (TextUtils.isEmpty(address)){
            ToastUtil.showToast("请输入坐在地区");
        }else if (TextUtils.isEmpty(detail)){
            ToastUtil.showToast("请输入详细地址");
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token", token);
            map.put("consignee",name);
            map.put("address",detail);
            map.put("mobile",phone);
            if (!TextUtils.isEmpty(addressid)){
                map.put("address_id",addressid);
            }
            if (approve){
                map.put("is_default",1);
            }
             String split[] =  address.split("-");
            for (int i = 0; i < split.length; i++) {
                if (i==0){
                    map.put("province",split[i]);
                }else if (i==1){
                    map.put("city",split[i]);
                }else if (i==2){
                    map.put("district",split[i]);
                }
                LLog.d("数据", split[i]);
            }
            meApi.saveAddress(map)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String data) {
                            view.hideLoading();
                            view.PullLoadMoreComplete();
                            LLog.d("数据", data);
                            JSONObject object = null;
                            try {
                                object = new JSONObject(data);
                                int status = object.getInt("status");
                                String info =object.optString("info");
                                if (status==1){
                                    view.saveSuccess();
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
                            view.PullLoadMoreComplete();
                            LLog.d("数据错误11", throwable.toString() + "");
                        }
                    });




        }

    }

    @Override
    public void identifyAddress(String asddress) {
        view.setClickable();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("address",asddress);
        LLog.d("数据2", token);
        view.showLoading();
        meApi.smartAddress(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        view.setClickalbeTrue();
                        view.PullLoadMoreComplete();
                        LLog.d("数据2", data);
                        JSONObject object = null;
                        try {
                            object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){
                                smatrAddressBean adressBean = JSON.parseObject(data,smatrAddressBean.class);
                                view.identifyAddressSuccess(adressBean);
                            }else {
                                ToastUtil.showToast(info);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            view.setClickalbeTrue();
                        }


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.hideLoading();
                        view.setClickalbeTrue();
                        view.PullLoadMoreComplete();
                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });
    }


    @Override
    public void attachView(@NonNull AddressTowContract.View view) {

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
