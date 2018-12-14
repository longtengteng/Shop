package com.lnkj.privateshop.ui.mybuy.myorder.looktransit;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ExpressBean;
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
 * Created by Administrator on 2017/8/3 0003.
 */

public class LookTransitPresenter implements LookTransitContract.Presenter{
    LookTransitContract.View view;
    Context mContext;
    private String token;
    public LookTransitPresenter(LookTransitContract.View view, Context mContext) {
        this.view=view;
        this.mContext=mContext;

    }
    @Override
    public void getPutFromServer(String id) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("order_sn",id);
//        map.put("token","543513580446812742");
//        map.put("order_sn","S201710110843012519");
        meApi.getExpressTraces(map)
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
                            String info = object.optString("info");
                            if (status==1){
                                ExpressBean beass = JSON.parseObject(data,ExpressBean.class);
                                view.succree(beass);
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
                        view.hideLoading();
                    }
                });

    }
    @Override
    public void attachView(@NonNull LookTransitContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }





}
