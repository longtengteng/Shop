package com.lnkj.privateshop.ui.mybuy.myorder.looktransit;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ExpressBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface LookTransitContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void finisht();
        void succree(ExpressBean beass);

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void getPutFromServer(String id);

    }
}
