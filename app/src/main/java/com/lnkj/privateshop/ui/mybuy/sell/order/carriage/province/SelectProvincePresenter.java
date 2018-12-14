package com.lnkj.privateshop.ui.mybuy.sell.order.carriage.province;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.AddressBean;
import com.lnkj.privateshop.utils.LLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class SelectProvincePresenter implements SelectProvinceContract.Presenter {

    SelectProvinceContract.View mView;
    private String token;
    public SelectProvincePresenter(SelectProvinceContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull SelectProvinceContract.View view) {

    }

    @Override
    public void detachView() {

    }



    @Override
    public void getAddress() {
        mView.showLoading();
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        meApi.getAddress(map)
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
                                AddressBean beass = JSON.parseObject(data, AddressBean.class);
                                mView.getAddressSuccree(beass);
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
    public void getToken(String token) {
        this.token=token;
    }


}
