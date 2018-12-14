package com.lnkj.privateshop.ui.shop.shoparchives;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ShopArchivesBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class ShopArchivesContract {

    interface View extends BaseView {

        void initView();
        void getShomCommentSucceed(ShopArchivesBean beans);
        void SetColloectShopSuccreed();
    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void setToken(String token);
        void  getShomComment(String shopid);
        void setCollectShop(String shopid);
    }
}
