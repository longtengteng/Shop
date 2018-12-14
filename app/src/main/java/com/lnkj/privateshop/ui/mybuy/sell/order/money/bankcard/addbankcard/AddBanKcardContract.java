package com.lnkj.privateshop.ui.mybuy.sell.order.money.bankcard.addbankcard;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface AddBanKcardContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();


        void setData();

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void getDataFromServer(String people_name,String number,String bank_name,String kaihu);
//        boolean isLogin();//是否登录
    }
}
