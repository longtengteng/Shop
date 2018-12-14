package com.lnkj.privateshop.ui.mybuy.myorder.orderdetailed;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.OrderDetailderBean;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class OrderDetaildeContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getOrderSucceed(OrderDetailderBean beass);
        void okGoods();
        void remoOrder();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void initView();
        void getToken(String token);
        void getOrderFromServer(String orderId);
        /**
         * 确认收货
         * @param
         */
        void onOkGoods(String orderId);
        /**
         * 提醒发货
         * @param
         */
        void onRemindDelivery(String orderId);

        /**
         * 删除订单
         * @param
         */
        void onDeleteOrder(String orderId);

        /**
         * 取消订单
         * @param orderId
         */
        void onCancelorder(String orderId);
    }
}
