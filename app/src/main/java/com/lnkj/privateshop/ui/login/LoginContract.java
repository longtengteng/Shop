package com.lnkj.privateshop.ui.login;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by WRJ on 2016/8/30.
 */
public class LoginContract {

    interface View extends BaseView {

        void initView();

        void showLoading();//显示加载框

        void hideLoading();//隐藏加载框

        void toMain();//登陆成功前往主界面

        void setTitle(String s);

        void login_three();
    }


    interface Presenter extends BasePresenter<View> {
        void initView();

        void login(String phone, String pwd);

        void login_three(String login_type, String parent_id, String open_id, String nickname, String head_pic);
    }
}
