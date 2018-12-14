package com.lnkj.privateshop.ui.mybuy.sell.order.carriage;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ExpressTemplateBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class CarriagePresenter implements CarriageContract.Presenter {

    CarriageContract.View mView;
    private String token;
    public CarriagePresenter(CarriageContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull CarriageContract.View view) {

    }

    @Override
    public void detachView() {

    }


    @Override
    public void getExpressTemplate() {
        mView.showLoading();
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        meApi.getExpressTemplate(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){
                                ExpressTemplateBean beass = JSON.parseObject(data,ExpressTemplateBean.class);
                        mView.getExpressTemplateSuccree(beass);
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

    @Override
    public void delete(String express_template_id) {
        mView.showLoading();
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("express_template_id",express_template_id);
        meApi.delExpressTemplate(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){

                                mView.deletesrccreed();
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
                        mView.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    @Override
    public void addExpressTempLate(String default_first_money, String default_add_money, String first_money,
                                   String add_money, String region_id, String area_name,String express_template_name) {
        mView.showLoading();
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("default_first_money",default_first_money+"");
        map.put("default_add_money",default_add_money+"");

        map.put("first_money",first_money+"");
        map.put("add_money",add_money+"");
        map.put("express_template_name",area_name+"");
        map.put("area_name",area_name+"");
        map.put("region_id",region_id+"");


        System.out.println("token"+token);
        System.out.println("default_first_money"+default_first_money+"");
        System.out.println("default_add_money"+default_add_money+"");
        System.out.println("first_money:"+first_money+"");
        System.out.println("add_money:"+add_money+"");
        System.out.println("express_template_name:"+area_name+"");
        System.out.println("area_name:"+area_name+"");
        System.out.println("region_id:"+region_id+"");

        meApi.addExpressTemplate(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){
                              mView.addSuccreed();
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
                        mView.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });

    }


}
