package com.lnkj.privateshop.ui.mybuy.openshop.money;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface MoneyContract {

    //界面处理逻辑
    interface View extends BaseView {
        void initView();
        void getDepositSuccreed(String frozen_bond,String shop_bond, int shop_type,String is_open);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer();

    }
}
