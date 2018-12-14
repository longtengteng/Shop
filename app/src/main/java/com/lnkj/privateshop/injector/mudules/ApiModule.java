package com.lnkj.privateshop.injector.mudules;


import com.lnkj.privateshop.api.home.HomeApi;
import com.lnkj.privateshop.api.login.LoginApi;
import com.lnkj.privateshop.api.me.MeApi;
import com.lnkj.privateshop.components.retrofit.RequestHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by sll on 2015/3/7.
 */
@Module
public class ApiModule {

    /**
     * 登录
     *
     * @param requestHelper
     * @param okHttpClient
     * @return
     */
    @Provides
    @Singleton

    public LoginApi provideLoginApi(RequestHelper requestHelper, @Named("api") OkHttpClient okHttpClient) {

        return new LoginApi(requestHelper, okHttpClient);

    }

    /**
     * 首页
     *
     * @param requestHelper
     * @param okHttpClient
     * @return
     */
    @Provides
    @Singleton

    public HomeApi provideHomeApi(RequestHelper requestHelper, @Named("api") OkHttpClient okHttpClient) {

        return new HomeApi(requestHelper, okHttpClient);

    }

    /**
     * 个人中心
     */

    @Provides
    @Singleton

    public MeApi provideMeApi(RequestHelper requestHelper, @Named("api") OkHttpClient okHttpClient) {

        return new MeApi();

    }
}
