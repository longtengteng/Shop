package com.lnkj.privateshop.ui.shop.shopclass.shopclassddetailed;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ShopClassDerBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class ShopClassDetContract {

    interface View extends BaseView {

        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void setTitle(String s);
        void getShopClassSucceed(ShopClassDerBean beans);
    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void setToken(String token);
        void  getShopClass(String shop_id,String sort,String  catid,int p);
    }
}
