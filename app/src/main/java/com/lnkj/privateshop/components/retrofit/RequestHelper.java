package com.lnkj.privateshop.components.retrofit;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.utils.LLog;

import java.util.Map;

/**
 * Created by gzsll on 2014/9/23 0023.
 */
public class RequestHelper {

    private Context mContext;

    public RequestHelper(Context mContext) {
        this.mContext = mContext;
    }

    public String getAndroidId() {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public String getDeviceId() {
        String deviceId;
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getDeviceId() == null) {
            deviceId = getAndroidId();
        } else {
            deviceId = tm.getDeviceId();
        }
        return deviceId;
    }

    public String getJson(Map<String, Object> map) {
//        if (SettingPrefUtil.getisLogin(mContext)) {
//            User user = SettingPrefUtil.getUserBeen(mContext);
//            map.put("token", "994654793761705283");
//        }
        String json = JSON.toJSONString(map);
        return json;
    }

    public String getParams(Map<String, Object> map) {
        String params = getJson(map);
        LLog.d("加密前参数", params);
        try {
            //params=DecriptUtil.byte2hex(DecriptUtil.Encrypt(params,"5efd3f6060e20330"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    public Map<String,Object> getMap(Map<String,Object> map){
//        map.put("token",SettingPrefUtil.getUserToken(mContext));
//        map.put("sig",SettingPrefUtil.getSign(mContext));
        return map;
    }

    public Map<String,Object> getTokenMap(Map<String,Object> map){
//        map.put("token",SettingPrefUtil.getUserToken(mContext));
        return map;
    }
}
