package com.lnkj.privateshop.ui.shop.shopInfo;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ShopArchivesBean;
import com.lnkj.privateshop.entity.ShopBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class ShopInfoContract {

    interface View extends BaseView {

        void initView();
        void finsh();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void setTitle(String s);
        void getShomInfoSucceed(ShopBean beans);
        void SetColloectShopSuccreed();
        void getShopArchivesSucceed(ShopArchivesBean beans);

    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void setToken(String token);
        void  getShomInfo(String shopid);
        void setCollectShop(String shopid);
        void  getShopArchives(String shopid);
    }
}
