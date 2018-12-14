package com.lnkj.privateshop.utils;

import com.lnkj.privateshop.api.me.MeApi;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class HttpUtil  {
    public static MeApi meApi;

    public  static MeApi getApi(){
        if (meApi==null){
            meApi = new MeApi();
            return meApi;
        }
        return meApi;
    }
}
