package com.lnkj.privateshop.ui.mybuy.sttting.paypwd;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ChagePayPwdContract {

    //界面处理逻辑
    interface View extends BaseView {

        void toLoging();
        void upladSuccreed();
//        void setWithdrawPasswordSuccreed();
        void sendCodeSuccreed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void upladData(String Phone, String number,String et_pay_pwd,String et_pay_pwd_to);
//        void setWithdrawPassword(String setWithdrawPassword);
        void  sendCode(String username);
    }
}
