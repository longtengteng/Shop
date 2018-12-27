package com.lnkj.privateshop.fragment.user;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.api.me.MeApi;
import com.lnkj.privateshop.entity.StartShopBean;
import com.lnkj.privateshop.entity.UserBean;
import com.lnkj.privateshop.entity.shopcommentBean;
import com.lnkj.privateshop.utils.LLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

public class UserPresenter implements UserContract.Presenter {

    private String token;
    private MeApi meApi;
    UserContract.View mView;

    public UserPresenter(UserContract.View view) {
        this.mView = view;
        meApi = new MeApi();
    }


    @Override
    public void setToken(String token) {
        this.token=token;
    }

    @Override
    public void apply_start_shop() {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        meApi.apply_start_shop(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status==1){
                                StartShopBean beass = JSON.parseObject(data,StartShopBean.class);
                                mView.apply_start_shop(beass);
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
    public void attachView(@NonNull UserContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
