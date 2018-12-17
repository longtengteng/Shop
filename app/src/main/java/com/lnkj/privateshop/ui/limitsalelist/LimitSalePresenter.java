package com.lnkj.privateshop.ui.limitsalelist;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.LimitSaleListBean;
import com.lnkj.privateshop.entity.SeachGoodsBean;
import com.lnkj.privateshop.ui.seachgoods.SeachGoodsContract;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

public class LimitSalePresenter implements LimitSaleContract.Presenter {
    private LimitSaleContract.View mView;
    private String token;

    public LimitSalePresenter(LimitSaleContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void getDataFromServer( int page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("p", page);
        mView.showLoading();
        meApi.getLimitSaleList(map)
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
                                LimitSaleListBean beass = JSON.parseObject(data, LimitSaleListBean.class);
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

    @Override
    public void setToken(String token) {
        this.token=token;
    }

    @Override
    public void getSize() {

    }

    @Override
    public void attachView(@NonNull LimitSaleContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
