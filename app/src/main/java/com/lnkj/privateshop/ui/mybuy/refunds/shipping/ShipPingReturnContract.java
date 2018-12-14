package com.lnkj.privateshop.ui.mybuy.refunds.shipping;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ShipPingBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ShipPingReturnContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getShipPingData(ShipPingBean beass);
        void ShopPingGoodsSuccree();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void getShipPingDataFromServer();
        void ShipPingGoods(String order_sn, String express_id, String express_code,String express_name);
    }
}
