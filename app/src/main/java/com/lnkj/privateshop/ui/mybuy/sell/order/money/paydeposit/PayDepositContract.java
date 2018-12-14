package com.lnkj.privateshop.ui.mybuy.sell.order.money.paydeposit;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface PayDepositContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void showFragment(int position);//fragment切换
        void showLoginUi();//登录
        void updatePraiseView(int dif, int position);
        void getOrderData(String frozen_bond,String shop_bond,String shop_type,int is_open);
        void defrostSurcreed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void exist();//退出
        void getDataFromServer();
        void defrost(String money);
//        boolean isLogin();//是否登录
    }
}
