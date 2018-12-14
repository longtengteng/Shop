package com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed.modify;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.EditOrderPriceBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ModifyPriceContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getOrderSucceed(EditOrderPriceBean beass);
        void setData(String consume, String income, String settlement, String user_money);
        void modificationPriceSuccreed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getOrderFromServer(String orderId);
        void modificationPrice(String order_sn,String express,String goods_id,String type,String money);
    }
}
