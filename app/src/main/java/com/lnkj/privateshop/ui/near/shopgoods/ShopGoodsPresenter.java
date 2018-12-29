package com.lnkj.privateshop.ui.near.shopgoods;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;
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

public class ShopGoodsPresenter implements ShopGoodsContract.Presenter {
    ShopGoodsContract.View view;
    private String token;

    public ShopGoodsPresenter(ShopGoodsContract.View view) {
        this.view = view;

    }

    @Override
    public void getDataFromServer(String id) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("shop_id", id);
        meApi.getShopmerchandiseList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();

                        LLog.d("数据2", data);

                        JSONObject object = null;
                        try {
                            object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                ShopMerchandiseListBean beass = JSON.parseObject(data, ShopMerchandiseListBean.class);
                                view.getOrderData(beass);
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

                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });

    }


    @Override
    public void attachView(@NonNull ShopGoodsContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }


}
