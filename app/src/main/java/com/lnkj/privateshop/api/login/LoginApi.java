package com.lnkj.privateshop.api.login;


import com.lnkj.privateshop.api.ApiUtils;
import com.lnkj.privateshop.components.retrofit.RequestHelper;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by WRJ on 2016/8/30.
 */
public class LoginApi {

    private LoginService loginService;
    private RequestHelper requestHelper;


    public LoginApi(RequestHelper requestHelper, OkHttpClient okHttpClient) {

        this.requestHelper = requestHelper;
        Retrofit retrofit = ApiUtils.initRetrofit(okHttpClient);
        loginService = retrofit.create(LoginService.class);
    }
    //登录
    public Observable<String> login(Map<String,Object> map) {
        return loginService.login(map).subscribeOn(Schedulers.io());
    }
    //三方登录
    public Observable<String> loginThree(Map<String,Object> map) {
        return loginService.loginThree(map).subscribeOn(Schedulers.io());
    }
    //获取验证码
    public Observable<String> getRePWRandNumber(Map<String,String> map) {
        return loginService.getRePWRandNumber(map).subscribeOn(Schedulers.io());

    }
    //启动页广告
    public Observable<String> startAppAd(Map<String,Object> map) {
        return loginService.startAppAd(map).subscribeOn(Schedulers.io());
    }
    /**
     * 注册
     */
    public Observable<String> modifyLoginPassword(Map<String, Object> params) {
        return loginService.modifyLoginPassword(params).subscribeOn(Schedulers.io());
    }
    /**
     * 修改密码
     */
    public Observable<String> findpwdSave(Map<String, Object> params) {
        return loginService.findpwdSave(params).subscribeOn(Schedulers.io());
    }
    //验证  验证码
    public Observable<String> checkCode(Map<String,String> map) {
        return loginService.checkCode(map).subscribeOn(Schedulers.io());

    }

}
