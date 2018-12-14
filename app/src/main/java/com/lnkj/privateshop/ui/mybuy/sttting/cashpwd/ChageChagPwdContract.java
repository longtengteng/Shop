package com.lnkj.privateshop.ui.mybuy.sttting.cashpwd;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ChageChagPwdContract {

    //界面处理逻辑
    interface View extends BaseView {

        void toLoging();
        void upladSuccreed();
        void setWithdrawPasswordSuccreed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void upladData(String number, String name, String pic);
        void setWithdrawPassword(String setWithdrawPassword,String confirm_withdraw_password);

    }
}
