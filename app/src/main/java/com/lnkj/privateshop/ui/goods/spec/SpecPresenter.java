package com.lnkj.privateshop.ui.goods.spec;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.GoodsBean;
import com.lnkj.privateshop.entity.OrderConBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoContract;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

public class SpecPresenter implements SpecContract.Presenter {
    private SpecContract.View mView;
    private Context mContext;
    private String token;

    public SpecPresenter(SpecContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;

    }

    @Override
    public void initView() {

    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void getPriceAndStoreBySpce(String goods_spec_key, String goods_id, String act_id) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("goods_spec_key", goods_spec_key);
        map.put("goods_id", goods_id);
        map.put("act_id", act_id);
//            map.put("goods_id",259+"");
        meApi.getPriceAndStoreBySpce(map)
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
                                SpecBean beass = JSON.parseObject(data, SpecBean.class);
                                mView.getPriceAndStoreBySpceSucceed(beass);
                            } else {
                                //               ToastUtil.showToast(info);
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
    public void addCart(String goods_spec_key, String goods_id, String buy_number, String act_id, String act_type, String from_shop_id) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("goods_spec_key", goods_spec_key);
        map.put("goods_id", goods_id);
        map.put("buy_number", buy_number);
        map.put("act_id", act_id);
        map.put("from_shop_id", from_shop_id);
        map.put("act_type", act_type);
//            map.put("goods_id",259+"");
        meApi.getaddCart(map)
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
                                //     SpecBean beass = JSON.parseObject(data, SpecBean.class);
                                ToastUtil.showToast(info);
                                mView.addCart();
                            } else {
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
    public void cartConfirm(String goods_id, String buy_number, String goods_spec_key, String from_shop_id) {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("is_from_cart", "0");
        map.put("goods_id", goods_id);
        map.put("buy_number", buy_number);
        map.put("from_shop_id", from_shop_id);
        map.put("goods_spec_key", goods_spec_key);
        meApi.orderConfirm(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        mView.btnClickable(true);
                        LLog.d("数据111", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                OrderConBean beass = JSON.parseObject(data, OrderConBean.class);
                                mView.getGoodsInfoSucceed(beass);
                            } else {
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
                        mView.btnClickable(true);
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void attachView(@NonNull SpecContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
