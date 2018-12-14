package com.lnkj.privateshop.ui.mybuy.sttting.editpwd;

import android.support.annotation.NonNull;
import android.text.TextUtils;

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

public class EditCashPwdPresenter implements EditCashPwdContract.Presenter {
        private EditCashPwdContract.View mView;
    private String token;
    public EditCashPwdPresenter(EditCashPwdContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull EditCashPwdContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    @Override
    public void upladData(String username, String code) {
        if (TextUtils.isEmpty(username)){
            ToastUtil.showToast("请输入手机号");
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("mobile", username);
//        if (flag.equals(Constants.FINDPWD)){
        map.put("sms_code", code);
//            LLog.d("sms",code);
//        }else {
//            LLog.d("sms",1+"");
//            map.put("type", 1+"");
//        }
        mView.showLoading();
        meApi.checkCode(map)
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
                        // mView.hideLoading();
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void setWithdrawPassword(String yuanpwd, String newpwd,String newpwdto) {
        if (TextUtils.isEmpty(yuanpwd)){
            ToastUtil.showToast("请输入原密码");
            return;
        }else if (TextUtils.isEmpty(newpwd)){
            ToastUtil.showToast("请输入新密码");
        }else if (TextUtils.isEmpty(newpwdto)){
            ToastUtil.showToast("请在此输入密码");
        }
        Map<String,String> map = new HashMap<>();
        map.put("token", token);
        map.put("newPassword", newpwd);
        map.put("confirmPassword", newpwdto);
        mView.showLoading();
        meApi.editWithdrawPassword(map)
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
                               mView.toLoging();
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
                        // mView.hideLoading();
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void getCode(String username) {
        if (TextUtils.isEmpty(username)){
            ToastUtil.showToast("请输入手机号");
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("mobile", username);
        map.put("type", 5+"");
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
                        // mView.hideLoading();
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void next(String phone, String code) {

    }
}
