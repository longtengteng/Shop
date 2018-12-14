package com.lnkj.privateshop.ui.goodscar;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.OrderConBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class ClearingContract {

    interface View extends BaseView {

        void initView();
        void getGoodsInfoSucceed(OrderConBean beass);
        void CollectGoodsSucceed();
        void addPayOredeSuccreed(String order_sn);
        void getAddressPriceSucceed();
    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void login(String phone, String pwd);
        void setToken(String token);
        void getGoodsInfo(String goodsid);
        void addPayOrder(String addressid,String shopid,String remark);
        void getAddressPrice(String goods_id,String address_id);

    }
}
