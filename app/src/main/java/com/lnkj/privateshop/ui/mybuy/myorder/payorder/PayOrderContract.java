package com.lnkj.privateshop.ui.mybuy.myorder.payorder;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

import org.json.JSONObject;

/**
 * Created by WRJ on 2016/8/30.
 */
public class PayOrderContract {

    interface View extends BaseView {

        void initView();
        void payWxpaySuccreed(JSONObject data);
        void payMentSuccreed(String data);
        void payYueSuccreed(String data);
    }


    interface Presenter extends BasePresenter<View> {
        void initView();

        void setToken(String token);
        void payWxpay(String order_sn);
        void payMent(String order_sin);
        void payYue(String price);
    }
}
