package com.lnkj.privateshop.api;

import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.components.retrofit.FastJsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by yingfei_z
 * on 2016/8/20 0020
 * $desc
 */

public  class ApiUtils {

    public static Retrofit initRetrofit(OkHttpClient mOkHttpClient){
        Retrofit retrofit =
                new Retrofit.Builder().addConverterFactory(FastJsonConverterFactory.create())
                        .client(mOkHttpClient)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .baseUrl(Constants.Base_URL)
                        .build();
        return retrofit;
    }
}
