package com.lnkj.privateshop.ui.mybuy.sell.order.money.bankcard;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.BankCardBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface BanKcardContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();

        void deleteBankCardSuccreed();
        void setData( BankCardBean beans);

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void getDataFromServer();
//        boolean isLogin();//是否登录
        void delteBankCard(String id);
    }
}
