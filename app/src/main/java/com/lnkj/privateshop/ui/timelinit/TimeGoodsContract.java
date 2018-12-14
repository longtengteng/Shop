package com.lnkj.privateshop.ui.timelinit;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.TimeGoodsBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface TimeGoodsContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getOrderData(TimeGoodsBean.DataBean beans);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer();
//        boolean isLogin();//是否登录
    }
}
