package com.lnkj.privateshop.fragment.group;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ChatBean;
import com.lnkj.privateshop.entity.DynamicragBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class GroupPresenter implements GroupContract.Presenter {
    private String token;
    GroupContract.View view;
    @Inject
    public GroupPresenter(GroupContract.View view) {
       this.view = view;
    }

    @Override
    public void attachView(@NonNull GroupContract.View view) {

    }

    @Override
    public void detachView() {

    }


    @Override
    public void getDynamicragFromService(int page) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("p",page);
        map.put("token",token);
        meApi.getDynamicList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据圈子", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status==1){
                                DynamicragBean beass = JSON.parseObject(data, DynamicragBean.class);
                                view.getDynamicragSuceed(beass);
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
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void getChatFromService(int page) {
        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("p",page);
//        map.put("token",token);
        meApi.getChatRoomList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("数据_________", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status==1){
                                ChatBean beass = JSON.parseObject(data, ChatBean.class);
                                view.getChatFromSuceed(beass);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });

    }

    @Override
    public void setToken(String token) {
        this.token=token;
    }

    @Override
    public void setCollectShop(String shopid) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("shop_id",shopid);
        map.put("token",token);
        meApi.setCollectShop(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){
                             view.SetColloectShopSuccreed();
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
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }
}
