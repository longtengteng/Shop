package com.lnkj.privateshop.api.login;


import com.lnkj.privateshop.entity.BaseEntity;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by WRJ on 2016/8/30.
 */
public interface LoginService {

    /**
     * 登陆
     */
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/login")
    Observable<String> login(@FieldMap Map<String,Object> map);
    /**
     * 三方登陆
     */
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/otherLogin")
    Observable<String> loginThree(@FieldMap Map<String,Object> map);

    //获取验证码
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/smsCode")
    Observable<String> getRePWRandNumber(@FieldMap Map<String,String> map);


    //验证  验证码
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/checkSmsCode")
    Observable<String> checkCode(@FieldMap Map<String,String> map);

    //注册接口
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/register")
    Observable<String> modifyLoginPassword(@FieldMap Map<String,Object> map);
    //修改密码
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/findpwdSave")
    Observable<String> findpwdSave(@FieldMap Map<String,Object> map);

    //启动页广告
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/startAppAd")
    Observable<String> startAppAd(@FieldMap Map<String,Object> map);
    /**
     * 初始化
     */
    @FormUrlEncoded
    @POST("main/init")
    Observable<BaseEntity> init(@FieldMap Map<String,Object> map);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFromNet(@Url String fileUrl);

    //省市区
    @FormUrlEncoded
    @POST("index.php/Core/RegionApi/getRegionList")
    Observable<BaseEntity> getRegionList(@Field("code") String param);

    //更換手機號
    @FormUrlEncoded
    @POST("Member/PublicApi/changeLoginInfo")
    Observable<BaseEntity> changeLoginInfo(@Field("mobile") String mobile,
                                           @Field("newMobile") String newMobile,
                                           @Field("machine_code") String machine_code,
                                           @Field("code") String code,
                                           @Field("type") int type);

    //更換手機號获取验证码
    @FormUrlEncoded
    @POST("Member/PublicApi/getVerify")
    Observable<BaseEntity> getVerify(@Field("mobile") String param);

    //用户打开app的启动页图片和登录背景图
    @FormUrlEncoded
    @POST("Member/PublicApi/startPicture")
    Observable<BaseEntity> startPicture(@Field("code") String param);


}
