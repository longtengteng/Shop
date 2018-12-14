package com.lnkj.privateshop.fragment.shop;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.shopcommentBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class ShopCommentContract {

    interface View extends BaseView {

        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void setTitle(String s);
        void getShopCommentSucceed(shopcommentBean beans);
    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void setToken(String token);
        void  getShomComment(String shopid,int p,String type);
    }
}
