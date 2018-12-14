package com.lnkj.privateshop.ui.addshoppingcart.altercart;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.AlterGoodsCountBean;
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
 * Created by Administrator on 2017/7/31 0031.
 */

public class AlterGoodsCarPresenter implements AlterGoodsCarContract.Presenter {
   private  AlterGoodsCarContract.View mView ;

 private String token;
    public AlterGoodsCarPresenter(AlterGoodsCarContract.View mView) {
        this.mView = mView;

    }

    @Override
    public void attachView(@NonNull AlterGoodsCarContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

    }



 @Override
 public void setToken(String token) {
  this.token = token;
 }

    @Override
    public void getGoods(String goods_id) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("goods_id",goods_id);
        map.put("token",token);
        meApi.alterGoodsCount(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据商品规格", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status==1){
                                AlterGoodsCountBean beass = JSON.parseObject(data,AlterGoodsCountBean.class);
                                mView.getGoodsSucceed(beass);
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
    public void AddShopPing(String key,String goods_id,String  buy_number) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("goods_id",goods_id);
        map.put("goods_spec_key",key);
        map.put("buy_number",buy_number);
        map.put("token",token);
        meApi.alterGoods(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据加入购物车", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){
                                mView.AddshopIngSucceed();
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


}
