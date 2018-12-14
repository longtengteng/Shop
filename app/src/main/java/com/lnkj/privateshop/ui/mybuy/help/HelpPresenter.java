package com.lnkj.privateshop.ui.mybuy.help;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.entity.HelpBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.PreferencesUtils;
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

public class HelpPresenter implements HelpContract.Presenter{
    HelpContract.View view;
    Context mContext;
    private String token;
    public HelpPresenter(HelpContract.View view, Context mContext) {
        this.view=view;
        this.mContext=mContext;

    }
    @Override
    public void getPutFromServer() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        final int state=  PreferencesUtils.getInt(mContext,"state", Constants.STATE_BUY);
        if (state==Constants.STATE_BUY){
        map.put("article_cat_id",6);
        }else {
        map.put("article_cat_id",7);
        }
        meApi.help(map)
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
                                HelpBean beass = JSON.parseObject(data,HelpBean.class);
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
    public void attachView(@NonNull HelpContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }





}
