package com.lnkj.privateshop.ui.goodscar.gopay;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

import org.json.JSONObject;

/**
 * Created by WRJ on 2016/8/30.
 */
public class GoPayOrderContract {

    interface View extends BaseView {

        void initView();
        void payWxpaySuccreed(JSONObject data);
        void payMentSuccreed(String data);
        void payYe(String data);
    }


    interface Presenter extends BasePresenter<View> {
        void initView();

        void setToken(String token);
        void payWxpay(String order_sn);
        void payMent(String order_sin);
        void payYe(String order_sin,String pwd);
    }
}
