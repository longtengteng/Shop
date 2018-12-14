package com.lnkj.privateshop.ui.near;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;

import java.util.List;

/**
 * Created by zjh on 2016/5/11.
 */
public interface AddGoodsContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getOrderData();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer( List<ShopMerchandiseListBean.DataBean.GoodsListBean> lists,String context);
//        boolean isLogin();//是否登录
    }
}
