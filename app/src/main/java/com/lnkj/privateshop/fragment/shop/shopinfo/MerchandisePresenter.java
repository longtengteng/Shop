package com.lnkj.privateshop.fragment.shop.shopinfo;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.MenGoodsListBean;
import com.lnkj.privateshop.entity.NewShopHomeBean;
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
 * Created by Administrator on 2017/8/7 0007.
 */

public class MerchandisePresenter implements MerchandiseContract.Presenter {

    MerchandiseContract.View mView;
    private String token;

    public MerchandisePresenter(MerchandiseContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull MerchandiseContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void getDataFromServer(int p, String sort, String shop_id) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cat_id", shop_id);
        map.put("p", p);
        map.put("token", token);
        // map.put("sort",sort);
        meApi.getShopmerchandiseList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                NewShopHomeBean beass = JSON.parseObject(data, NewShopHomeBean.class);
                                mView.getGoodsListSucceed(beass);
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
    public void getDataFromServer(int p, String shop_id) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shop_id", shop_id);
        map.put("p", p);
        map.put("token", token);
        System.out.println("shop_id:" + shop_id);
        System.out.println("p:" + p);
        System.out.println("token:" + token);
        meApi.getShopMenberList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据会员", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                MenGoodsListBean beass = JSON.parseObject(data, MenGoodsListBean.class);
                                mView.getMenGoodsSuccreed(beass);
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
