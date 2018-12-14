package com.lnkj.privateshop.ui.login.register;

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
public class RegisterPresenter implements RegisterContract.Presenter {

    public String flag;
    private RegisterContract.View mView;
    private Context mContext;
    private LoginApi loginApi;
    private Subscription subscriptSpan;
    public String phone ;

    @Inject
    public RegisterPresenter(Context context, LoginApi loginApi) {
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
    public void next(String username ,String code,String nick) {
        Map<String,Object> map = new HashMap<>();

        if (TextUtils.isEmpty(username)){
            ToastUtil.showToast("请输入密码");
            return;
        }else if (TextUtils.isEmpty(username)){
            ToastUtil.showToast("请再次输入密码");
            return;
        }else if (!username.equals(code)){
            ToastUtil.showToast("两次密码不一致");
            return;
        }
        if (!flag.equals(Constants.FINDPWD)){
            if (TextUtils.isEmpty(nick)){
                ToastUtil.showToast("请输入昵称");
                return;
            }else {
                map.put("nickname", nick);
            }
        }
            map.put("mobile", phone);
            map.put("password", code);
        mView.showLoading();
        if (flag.equals(Constants.FINDPWD)){
            subscriptSpan = loginApi.findpwdSave(map)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String userData) {
                            mView.hideLoading();
                            try {
                                LLog.d("123",userData);
                                JSONObject object = new JSONObject(userData);
                                int status = object.getInt("status");
                                String info = object.getString("info");
                                if (status==1){
                                    //成功
                                    mView.toLogIn();
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
        }else {
        subscriptSpan = loginApi.modifyLoginPassword(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String userData) {
                        mView.hideLoading();
                        try {
                            LLog.d("123",userData);
                            JSONObject object = new JSONObject(userData);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){
                                //成功
                                mView.toLogIn();
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


    }


    @Override
    public void attachView(@NonNull RegisterContract.View view) {
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
