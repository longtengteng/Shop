package com.lnkj.privateshop.ui.mybuy.refunds.shipping.details;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.RetrunDetailsBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ReturnDetailsContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getShipPingData(RetrunDetailsBean beass);
        void ShopPingGoodsSuccree();
        void RevocationSucreed( );
        void DeleteSuccreed();
        void getServiceInfo(ServiceEmchatBean.DataBean serviceEmchatBean);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void getShipPingDataFromServer();
        void ShipPingGoods(String order_sn, String Order_id);
        void onRevocation(String order_sn);
        void onDeleteOrder(String Order_sn);
        void getServiceInfo();

    }
}
