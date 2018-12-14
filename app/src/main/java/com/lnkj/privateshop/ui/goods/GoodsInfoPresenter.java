package com.lnkj.privateshop.ui.goods;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.GoodsBean;
import com.lnkj.privateshop.entity.ShopEmchatBean;
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

public class GoodsInfoPresenter implements GoodsInfoContract.Presenter {
    private GoodsInfoContract.View mView;
    private Context mContext;
    private String token;

    public GoodsInfoPresenter(GoodsInfoContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void attachView(@NonNull GoodsInfoContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void login(String phone, String pwd) {

    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void getGoodsInfo(String goodsid) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("goods_id", goodsid);
//            map.put("goods_id",259+"");
        meApi.getGoodsInfo(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据111", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                GoodsBean beass = JSON.parseObject(data, GoodsBean.class);
                                mView.getGoodsInfoSucceed(beass);
                            }else {
                            ToastUtil.showToast(info);
                                mView.finsh();
                            }
                        } catch (JSONException e) {
                            ToastUtil.showToast("数据异常");
                            mView.finsh();
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mView.hideLoading();
                        ToastUtil.showToast("数据异常");
                        mView.finsh();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void CollectGoods(String goods_id) {
//        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("goods_id", goods_id);
        meApi.collecgGoods(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
//                        mView.hideLoading();
                        LLog.d("数据111", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                mView.CollectGoodsSucceed();
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
//                        mView.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void getShopEmchat(String shop_id) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("shop_id", shop_id);
        meApi.getShopEmchat(map).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String data) {
                try {
                    mView.hideLoading();
                    ShopEmchatBean bean = JSON.parseObject(data, ShopEmchatBean.class);
                    mView.getShopEmchat(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast("客服不在线");
                }
            }
        });
    }
}
