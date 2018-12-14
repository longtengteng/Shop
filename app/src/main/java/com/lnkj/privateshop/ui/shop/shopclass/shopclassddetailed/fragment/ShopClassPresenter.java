package com.lnkj.privateshop.ui.shop.shopclass.shopclassddetailed.fragment;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ShopClassgoodsBean;
import com.lnkj.privateshop.utils.LLog;

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

public class ShopClassPresenter implements ShopClassContract.Presenter {
   private  ShopClassContract.View mView ;
    private Context mContext;
 private String token;
    public ShopClassPresenter(ShopClassContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void attachView(@NonNull ShopClassContract.View view) {

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
    public void getShopClass(String shop_id,String sort,String  catid,int p) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("shop_id",shop_id);
        map.put("sort",sort);
        map.put("cat_id",catid);
        map.put("p",p);
        meApi.getShopGoodsList(map)
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
                                ShopClassgoodsBean beass = JSON.parseObject(data,ShopClassgoodsBean.class);
                                mView.getShopClassSucceed(beass);
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
