package com.lnkj.privateshop.ui.near.shopgoods;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ShopGoodsContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getOrderData(ShopMerchandiseListBean beass);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer(String id);
//        boolean isLogin();//是否登录
    }
}
