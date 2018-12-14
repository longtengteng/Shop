package com.lnkj.privateshop.ui.login.findpwd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.api.login.LoginApi;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by WRJ on 2016/8/30.
 */
public class FindPwdPresenter implements FindPwdContract.Presenter {

    public String flag;
    private FindPwdContract.View mView;
    private Context mContext;
    private LoginApi loginApi;
    private Subscription subscriptSpan;


    @Inject
    public FindPwdPresenter(Context context, LoginApi loginApi) {
        this.mContext = context;
        this.loginApi = loginApi;

    }
    @Override
    public void initView() {
        mView.initView();
        if (flag.equals(Constants.FINDPWD)){
          mView.setTitel("找回密码");
        }else {
          mView.setTitel("注册");
        }
        LLog.d("flag",flag);
    }
    @Override
    public void getCode(String username) {
        if (TextUtils.isEmpty(username)){
            ToastUtil.showToast("请输入手机号");
            return;
        }
            Map<String,String> map = new HashMap<>();
            map.put("mobile", username);
        if (flag.equals(Constants.FINDPWD)){
            map.put("type", 2+"");
        LLog.d(TAG,2+"");
        }else {
            map.put("type", 1+"");
            LLog.d(TAG,1+"");
        }
        mView.showLoading();
            subscriptSpan = loginApi.getRePWRandNumber(map)
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
                                    mView.countDown();
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
    public void next(String username ,String code) {
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
        subscriptSpan = loginApi.checkCode(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String userData) {
                        mView.hideLoading();
                        try {
                            JSONObject object = new JSONObject(userData);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            LLog.d(TAG,userData);
                            if (status==1){
                               mView.toRegister();
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

    private static final String TAG = "FindPwdPresenter";

    @Override
    public void attachView(@NonNull FindPwdContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (subscriptSpan != null && !subscriptSpan.isUnsubscribed()) {
            subscriptSpan.unsubscribe();
        }
        mView = null;

    }
}
