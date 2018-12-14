package com.lnkj.privateshop.ui.mybuy.sttting.chagephone;

import android.support.annotation.NonNull;
import android.text.TextUtils;

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
 * Created by Administrator on 2017/8/9 0009.
 */

public class ChagePhonePresenter implements ChagePhoneContract.Presenter {
        private ChagePhoneContract.View mView;
    private String token;
    public ChagePhonePresenter(ChagePhoneContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull ChagePhoneContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    @Override
    public void upladData(String Phone,String number ) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("mobile", Phone);
        map.put("sms_code", number);
        mView.showLoading();
        meApi.saveNewMobile(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {

                        LLog.d("数据222", data);
                        mView.hideLoading();
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {

                                mView.upladSuccreed();

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

                        LLog.d("数据错误222", throwable.toString() + "");
                    }
                });


    }

    @Override
    public void setWithdrawPassword(String setWithdrawPassword) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("password", setWithdrawPassword);
        mView.showLoading();
        meApi.checkPassword(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {

                        LLog.d("数据222", data);
                        mView.hideLoading();
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {

                                mView.setWithdrawPasswordSuccreed();

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

                        LLog.d("数据错误222", throwable.toString() + "");
                    }
                });

    }

    @Override
    public void sendCode(String username) {
        if (TextUtils.isEmpty(username)){
            ToastUtil.showToast("请输入手机号");
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("mobile", username);
        map.put("type", "4");

        mView.showLoading();
        meApi.getRePWRandNumber(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String userData) {
                        mView.hideLoading();
                        try {
                            JSONObject object = new JSONObject(userData);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){
                                mView.sendCodeSuccreed();
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
                        // mView.hideLoading();
                        mView.hideLoading();
                    }
                });
    }
}
