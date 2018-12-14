package com.lnkj.privateshop.ui.mybuy.sell.order.months;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface MonthsContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();

        void showFragment(int position);//fragment切换

        void showLoginUi();//登录

        void updatePraiseView(int dif, int position);
        void setData(String mones, String mainmones, String income,String time);
        void PullLoadMoreComplete();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void exist();//退出
        void getDataFromServer();
//        boolean isLogin();//是否登录
    }
}
