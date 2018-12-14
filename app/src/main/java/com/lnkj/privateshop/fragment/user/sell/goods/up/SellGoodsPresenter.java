package com.lnkj.privateshop.fragment.user.sell.goods.up;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.SellGoods;
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

public class SellGoodsPresenter implements SellGoodsContract.Presenter {
    SellGoodsContract.View mView;

    private String token;
    public SellGoodsPresenter(SellGoodsContract.View mView) {
        this.mView = mView;
    }
    @Override
    public void getDataFromService(final int goods_state) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("goods_state",goods_state);
        meApi.getPutGoods(map)
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
                             SellGoods beass = JSON.parseObject(data,SellGoods.class);
                             mView.liftData(beass);
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
    public void attachView(@NonNull SellGoodsContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void clearAttention(int type) {

    }


    @Override
    public void getToken(String token) {
        this.token = token;
    }












}
