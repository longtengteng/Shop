package com.lnkj.privateshop.ui.seachgoods.seachshop;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.SeachShopBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface SeachShopContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getOrderData(SeachShopBean beans);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer(String keywords, int p);
    }
}
