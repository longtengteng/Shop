package com.lnkj.privateshop.ui.goods.spec;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsBean;
import com.lnkj.privateshop.entity.OrderConBean;
import com.lnkj.privateshop.entity.ShopEmchatBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoContract;

public class SpecContract {
    interface View extends BaseView {

        void initView();

        void showLoading();//显示加载框

        void hideLoading();//隐藏加载框

        void finsh();

        void getPriceAndStoreBySpceSucceed(SpecBean specBean);

        void addCart();

        void cartConfirm(BugNowBean bugNowBean);
        void getGoodsInfoSucceed(OrderConBean orderConBean);
        void btnClickable(boolean clickable);

    }


    interface Presenter extends BasePresenter<SpecContract.View> {
        void initView();

        void setToken(String token);

        void getPriceAndStoreBySpce(String goods_spec_key, String goods_id,String act_id);

        void addCart(String goods_spec_key, String goods_id, String buy_number, String act_id, String act_type);

        void cartConfirm(String goods_id,String buy_number,String goods_spec_key);
    }
}
