package com.lnkj.privateshop.ui.seachgoods;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.AddriGoodsBean;
import com.lnkj.privateshop.entity.ClassGoodsBean;
import com.lnkj.privateshop.entity.SeachGoodsBean;
import com.lnkj.privateshop.entity.SizeBean;
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
 * Created by Administrator on 2017/8/9 0009.
 */

public class SeachGoodsPresenter implements SeachGoodsContract.Presenter {
        private SeachGoodsContract.View mView;
    private String token;
    public SeachGoodsPresenter(SeachGoodsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull SeachGoodsContract.View view) {

    }

    @Override
    public void detachView() {


    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    @Override
    public void getDataFromServer( String cat_id,String attr,String sorts,String min_price,String max_price,String pack_num,int page,String shop_type,String goods_size,String keywords) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("token", token);
        map.put("cat_id", cat_id);
        map.put("attr", attr);
        map.put("sort", sorts);
        map.put("min_price", min_price);
        map.put("max_price", max_price);
        map.put("pack_num", pack_num);
        map.put("p", page);
        map.put("goods_size", goods_size);
        map.put("shop_type", shop_type);
        map.put("keywords", keywords);

    mView.showLoading();
        meApi.seachGoods(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        mView.PullLoadMoreComplete();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.optString("info");
                            if (status==1){
                                SeachGoodsBean beass = JSON.parseObject(data, SeachGoodsBean.class);

                                mView.succeed(beass);
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
/*
获取分类
 */
    @Override
    public void getClassGoods() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token",token);
        meApi.classGoods(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        mView.PullLoadMoreComplete();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.optString("info");
                            if (status==1){
                                ClassGoodsBean beass = JSON.parseObject(data, ClassGoodsBean.class);

                                mView.classSucceed(beass);
                            }else {
//                                ToastUtil.showToast(info);
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
    public void setToken(String token) {
        this.token=token;
    }

    /**
     * 获取属性
     */
    @Override
    public void getDataAttri(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("cat_id",id);
        meApi.addriGoods(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        mView.PullLoadMoreComplete();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.optString("info");
                            if (status==1){
                                AddriGoodsBean beass = JSON.parseObject(data, AddriGoodsBean.class);
                                mView.AttriSucceed(beass);
                            }else {
//                                ToastUtil.showToast(info);
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
    public void getSize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token",token);
        meApi.getsize(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        mView.PullLoadMoreComplete();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.optString("info");
                            if (status==1){
                                SizeBean beass = JSON.parseObject(data, SizeBean.class);
                                mView.sizeSucceed(beass);
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
