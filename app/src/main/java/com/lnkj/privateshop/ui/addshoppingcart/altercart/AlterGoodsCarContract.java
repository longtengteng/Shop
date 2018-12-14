package com.lnkj.privateshop.ui.addshoppingcart.altercart;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AlterGoodsCountBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class AlterGoodsCarContract {

    interface View extends BaseView {
        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void setTitle(String s);
        void getGoodsSucceed(AlterGoodsCountBean beans);
        void AddshopIngSucceed();
    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void setToken(String token);
        void  getGoods(String Goods_id);
        void AddShopPing(String key, String goods_id, String buy_number);
    }
}
