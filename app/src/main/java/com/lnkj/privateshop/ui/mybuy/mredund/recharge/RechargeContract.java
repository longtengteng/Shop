package com.lnkj.privateshop.ui.mybuy.mredund.recharge;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

import org.json.JSONObject;

/**
 * Created by zjh on 2016/5/11.
 */
public interface RechargeContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();

        void payWxpaySuccreed(JSONObject data);
        void payMentSuccreed(String data);


    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void setToken(String token);
        void payWxpay(String order_sn);
        void payMent(String order_sin);

    }
}
