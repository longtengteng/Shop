package com.lnkj.privateshop.fragment.user.sell.lookredund;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.LookBedundBean;
import com.lnkj.privateshop.utils.LLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class DetailPresenter implements DetailContract.Presenter {
    DetailContract.View mView;
    String token ;
    public DetailPresenter(DetailContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull DetailContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token =token;
    }

    @Override
    public void initView() {

    }


    @Override
    public void getDataFromServer(String tyep,int p) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("type",tyep);
        map.put("p",p);
        meApi.getSellbalancedetail(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            LookBedundBean beass = JSON.parseObject(data,LookBedundBean.class);

                            mView.setData(beass);
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
