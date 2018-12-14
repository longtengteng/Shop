package com.lnkj.privateshop.ui.login.splash;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by WRJ on 2016/8/30.
 */
public class SplashContract {

    interface View extends BaseView {
        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void toLogIn();//登陆成功前往主界面
        void toMain();
        void setTitel(String s);
        void countDown();
        void get6StartAppAdSucreed(String url);
    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void getStartAppAd();
        void next();
    }
}
