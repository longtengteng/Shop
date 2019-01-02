package com.lnkj.privateshop.ui.mybuy.sell.order.refunds.details;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.RetrunDetailsBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface SellReturnDetailsContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getShipPingData(RetrunDetailsBean beass);
        void ShopPingGoodsSuccree();
        void OkRoNoGOodssuccree();
        void OnDeleteOrderSucreed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void ShipPingGoods(String order_sn, String Order_id);
        void OkRoNoGoods(String order_sn, String refund_type,String order_goods_id);
        void ReceiveGoods(String order_sn,String pwd);
        void onUrged(String order_sn);
        void onDeleteOrder(String order_sn);
    }
}
