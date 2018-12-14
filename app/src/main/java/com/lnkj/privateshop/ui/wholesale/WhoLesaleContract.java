package com.lnkj.privateshop.ui.wholesale;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.WhoLesaleBean;

import java.util.List;

/**
 * Created by zjh on 2016/5/11.
 */
public interface WhoLesaleContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getOrderData(List<WhoLesaleBean.DataBean> beans);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer(int p);
//        boolean isLogin();//是否登录
    }
}
