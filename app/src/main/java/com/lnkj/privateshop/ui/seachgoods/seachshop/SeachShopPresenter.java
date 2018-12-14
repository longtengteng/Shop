package com.lnkj.privateshop.ui.seachgoods.seachshop;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.SeachShopBean;
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

public class SeachShopPresenter implements SeachShopContract.Presenter {
    SeachShopContract.View view;
    private String token;
    public SeachShopPresenter(SeachShopContract.View view) {
        this.view=view;

    }
    @Override
    public void getDataFromServer(String keywords, int p) {
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("token",token);
        map.put("keyword",keywords);
        map.put("p",p);
        meApi.seachshop(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据店铺", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.optString("info");
                            if (status==1){
                                SeachShopBean beass = JSON.parseObject(data, SeachShopBean.class);
                                view.getOrderData(beass);
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
                        LLog.d("数据错误店铺", throwable.toString() + "");
                    }
                });
    }


    @Override
    public void attachView(@NonNull SeachShopContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }




}
