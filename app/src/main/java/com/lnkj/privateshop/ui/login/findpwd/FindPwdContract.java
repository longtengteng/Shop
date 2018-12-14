package com.lnkj.privateshop.ui.login.findpwd;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by WRJ on 2016/8/30.
 */
public class FindPwdContract {

    interface View extends BaseView {

        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void toRegister();//登陆成功前往主界面
        void setTitel(String s);
        void countDown();

    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void getCode(String phone);
        void next(String phone,String code);
    }
}
