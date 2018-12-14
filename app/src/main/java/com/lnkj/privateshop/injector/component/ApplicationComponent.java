package com.lnkj.privateshop.injector.component;

import android.app.NotificationManager;
import android.content.Context;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.MyApplication;
import com.lnkj.privateshop.api.home.HomeApi;
import com.lnkj.privateshop.api.login.LoginApi;
import com.lnkj.privateshop.api.me.MeApi;
import com.lnkj.privateshop.components.okhttp.OkHttpHelper;
import com.lnkj.privateshop.injector.mudules.ApiModule;
import com.lnkj.privateshop.injector.mudules.ApplicationModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by sll on 2016/3/8.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class, ApiModule.class
})
public interface ApplicationComponent {
    Context getContext();

    Bus getBus();

    LoginApi getLoginApi();

    MeApi getMeApi();

    HomeApi getHomeApi();

    OkHttpHelper getOkHttpHelper();

    NotificationManager getNotificationManager();

    void inject(MyApplication mApplication);

    void inject(BaseActivity mBaseActivity);

}
