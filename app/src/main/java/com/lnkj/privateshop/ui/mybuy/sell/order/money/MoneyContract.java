package com.lnkj.privateshop.ui.mybuy.sell.order.money;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.SellUserBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface MoneyContract {

    //界面处理逻辑
    interface View extends BaseView {
        void initView();
        void setData(String consume, String income, String settlement,String user_money);
        void getDepositSuccreed(SellUserBean bean);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer();
        void getDeposit();
//        boolean isLogin();//是否登录
    }
}
