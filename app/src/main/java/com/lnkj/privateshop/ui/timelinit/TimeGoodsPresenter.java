package com.lnkj.privateshop.ui.timelinit;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.TimeGoodsBean;
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

public class TimeGoodsPresenter implements TimeGoodsContract.Presenter {
    TimeGoodsContract.View view;
    private String token;

    public TimeGoodsPresenter(TimeGoodsContract.View view) {
        this.view = view;

    }

    @Override
    public void getDataFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        view.showLoading();
        meApi.getTimeGoods(map)
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
                            String info = object.getString("info");
                            if (status == 1) {
                                TimeGoodsBean bean = JSON.parseObject(data, TimeGoodsBean.class);

                                view.getOrderData(bean.getData());
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
                        view.hideLoading();

                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });

    }


    @Override
    public void attachView(@NonNull TimeGoodsContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }


}
