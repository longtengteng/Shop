package com.lnkj.privateshop.ui.goods;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsBean;
import com.lnkj.privateshop.entity.OrderConBean;
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

        void getGoodsInfoSucceed(GoodsBean beass);

        void CollectGoodsSucceed();

        void getShopEmchat(ShopEmchatBean bean);

        void addCart();//加入购物车

        void getGoodsDetailFromLimitSucceed(GoodsBean beass);

        void getGoodsInfoSucceed(OrderConBean orderConBean);
        void btnClickable(boolean clickable);
    }


    interface Presenter extends BasePresenter<View> {
        void initView();

        void login(String phone, String pwd);

        void setToken(String token);

        void getGoodsInfo(String goodsid,String from_shop_id);

        void getGoodsDetailFromLimit(String goods_id, String act_id);

        void CollectGoods(String goods_id);

        void getShopEmchat(String shop_id);

        void addCart(String goods_spec_key, String goods_id, String buy_number, String act_id, String act_type, String from_shop_id);//加入购物车

        void cartConfirm(String goods_id,String buy_number,String goods_spec_key,String from_shop_id);

    }
}
