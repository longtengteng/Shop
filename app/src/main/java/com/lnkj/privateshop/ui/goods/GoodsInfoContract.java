package com.lnkj.privateshop.ui.goods;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsBean;
import com.lnkj.privateshop.entity.ShopEmchatBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class GoodsInfoContract {

    interface View extends BaseView {

        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void finsh();
        void getGoodsInfoSucceed( GoodsBean beass);
        void CollectGoodsSucceed();

        void getShopEmchat(ShopEmchatBean bean);
    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void login(String phone, String pwd);
        void setToken(String token);
        void getGoodsInfo(String goodsid);
        void CollectGoods(String goods_id);

        void getShopEmchat(String shop_id);
    }
}
