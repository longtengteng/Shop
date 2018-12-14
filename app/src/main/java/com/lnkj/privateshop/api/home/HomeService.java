package com.lnkj.privateshop.api.home;


import com.lnkj.privateshop.entity.BaseEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by WRJ on 2016/8/31.
 */
public interface HomeService {


    //查询邮件
    @FormUrlEncoded
    @POST("mail/query")
    Observable<BaseEntity> shopGoodsCategory(@Field("code") String param);

}
