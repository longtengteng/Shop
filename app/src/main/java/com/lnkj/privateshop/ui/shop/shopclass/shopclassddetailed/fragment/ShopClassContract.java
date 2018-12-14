package com.lnkj.privateshop.ui.shop.shopclass.shopclassddetailed.fragment;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ShopClassgoodsBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class ShopClassContract {

    interface View extends BaseView {

        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void setTitle(String s);
        void getShopClassSucceed(ShopClassgoodsBean beans);
    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void setToken(String token);
        void  getShopClass(String shop_id, String sort, String catid, int p);
    }
}
