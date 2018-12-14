package com.lnkj.privateshop.api.home;

import com.lnkj.privateshop.api.ApiUtils;
import com.lnkj.privateshop.components.retrofit.RequestHelper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by WRJ on 2016/8/31.
 */
public class HomeApi {

    private HomeService homeService;
    private RequestHelper requestHelper;

    public HomeApi(RequestHelper requestHelper, OkHttpClient okHttpClient) {

        this.requestHelper = requestHelper;
        Retrofit retrofit = ApiUtils.initRetrofit(okHttpClient);
        homeService = retrofit.create(HomeService.class);
    }



}