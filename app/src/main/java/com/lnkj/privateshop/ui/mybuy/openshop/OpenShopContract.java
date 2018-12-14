package com.lnkj.privateshop.ui.mybuy.openshop;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AddGoodsBean;
import com.lnkj.privateshop.entity.EditShopBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface OpenShopContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void openSuccree(String shop_id);
        void openFactorySuccerr(String shop_id);
        void openRealitySuccerr(String shop_id);
        void getClassSucceed(AddGoodsBean beass);
        void getShopInfoSucceed(EditShopBean.DataBean beans);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void exist();//退出
        void getDataFromServer();
        void getShopInfo();
        void openShop(String path_head,String path,String shop_name,String people,String phone,String province,String city,String address,String  Cat_id,String mount,String pack_mount,boolean is_chane);
        void openFactoryShop(String path_head,String path,String shop_name,String people,String phone,String province,String city,String address,String  Cat_id,String mount,String pack_mount,boolean is_chane);
        void openRealityShop(String path_head,String path,String shop_name,String people,String phone,String province,String city,String address,String  Cat_id,String mount,String pack_mount,boolean is_chane,String floor,String munnber);
    }
}
