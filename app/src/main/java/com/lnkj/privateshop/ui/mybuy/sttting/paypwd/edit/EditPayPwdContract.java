package com.lnkj.privateshop.ui.mybuy.sttting.paypwd.edit;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface EditPayPwdContract {

    //界面处理逻辑
    interface View extends BaseView {

        void toLoging();
        void upladSuccreed();
        void setWithdrawPasswordSuccreed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void upladData(String number, String name);
        void setWithdrawPassword(String yuanpwd, String newpwd, String newpwdto);

        void getCode(String phone);
        void next(String phone, String code);
    }
}
