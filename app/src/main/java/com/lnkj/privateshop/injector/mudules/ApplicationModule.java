package com.lnkj.privateshop.injector.mudules;

import android.app.NotificationManager;
import android.content.Context;
import android.view.LayoutInflater;

import com.lnkj.privateshop.components.okhttp.CookieInterceptor;
import com.lnkj.privateshop.components.okhttp.HttpLoggingInterceptor;
import com.lnkj.privateshop.components.okhttp.OkHttpHelper;
import com.lnkj.privateshop.components.retrofit.RequestHelper;
import com.squareup.otto.Bus;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @author gzsll
 */
@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    public Bus provideBusEvent() {
        return new Bus();
    }

    @Provides
    @Singleton
//    @Named("api")
    OkHttpClient provideApiOkHttpClient(
            CookieInterceptor mCookieInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        builder.addInterceptor(mCookieInterceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    @Named("api")
    OkHttpClient provideOkHttpClient( OkHttpClient mOkHttpClient) {
        OkHttpClient.Builder builder = mOkHttpClient.newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.addInterceptor(logging);

        builder.interceptors().clear();
        return builder.build();
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    NotificationManager provideNotificationManager(Context mContext) {
        return (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    @Singleton
    CookieInterceptor provideCookieInterceptor() {
        return new CookieInterceptor();
    }

    @Provides
    @Singleton
    RequestHelper provideRequestHelper(Context mContext) {
        return new RequestHelper(mContext);
    }

    @Provides
    @Singleton
    OkHttpHelper provideOkHttpHelper(OkHttpClient mOkHttpClient) {
        return new OkHttpHelper(mOkHttpClient);
    }
}
