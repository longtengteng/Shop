package com.lnkj.privateshop.ui;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface MainContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();

        void showFragment(int position);//fragment切换

        void showLoginUi();//登录

        void updatePraiseView(int dif, int position);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void initView();
        void exist();//退出

        boolean isLogin();//是否登录
    }
}
