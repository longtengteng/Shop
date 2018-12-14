package com.lnkj.privateshop.ui.mybuy.sell.order.seach;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.entity.OrderAllBean;
import com.lnkj.privateshop.entity.SeachOrderBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class SeachOrderContract {
    interface View extends BaseView {
        void initView();
        void updateView(int id);
        void upateFragmentData(int type);
        void liftData(GoodsCategoryBean beans);
        void remoOrder();
        void okGoods();
        void remindDelivery(String s);
        void getOrderData(SeachOrderBean beass);
        void onRemindPaySucreed();
    }

    interface Presenter extends BasePresenter<View> {
        void clearAttention(int type);

        void getDataFromService(String keyword);

        void getToken(String token);

        void onRemindPay(String order_sn);

        /**
         * 删除订单
         *
         * @param position
         */
        void onDeleteOrder(String order_sn, int type);


        /**
         * 提醒发货
         *
         * @param position
         */
        void onRemindDelivery(String order_sn, int position);


    }
}
