package com.lnkj.privateshop.ui.mybuy.footprint;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.FootPrintBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface FootPrintContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();

        void showFragment(int position);//fragment切换

        void showLoginUi();//登录

        void updatePraiseView(int dif, int position);
        void getOrderData(FootPrintBean beass);
        void PullLoadMoreComplete();
        void delUserBrowseSucceed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void exist();//退出
        void getDataFromServer(int p);
        void  delUserBrowse(String goods_id);
//        boolean isLogin();//是否登录
    }
}
