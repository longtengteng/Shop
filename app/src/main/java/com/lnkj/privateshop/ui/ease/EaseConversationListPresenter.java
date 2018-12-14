package com.lnkj.privateshop.ui.ease;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.HelpBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;
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

public class EaseConversationListPresenter implements EaseConversationListContract.Presenter {
    EaseConversationListContract.View view;
    private String token;
    public EaseConversationListPresenter(EaseConversationListContract.View view) {
        this.view=view;

    }
    @Override
    public void getDataFromServer() {
        Map<String,Object> map = new HashMap<String, Object>();
       map.put("article_cat_id",8);
        view.showLoading();
       meApi.help(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据系统公告", data);
                        JSONObject object = null;
                        try {
                            object = new JSONObject(data);
                             int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){
                                HelpBean bean = JSON.parseObject(data,HelpBean.class);
                                  view.getOrderData(bean);
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

                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });

    }

    @Override
    public void getServiceInfo() {
        view.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        meApi.getServiceInfo(map).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String data) {
                try {
                    view.hideLoading();
                    JSONObject object = new JSONObject(data);
                    ServiceEmchatBean bean = JSON.parseObject(data, ServiceEmchatBean.class);
                    view.getServiceInfo(bean.getData());
                } catch (Exception e) {
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
    public void attachView(@NonNull EaseConversationListContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }




}
