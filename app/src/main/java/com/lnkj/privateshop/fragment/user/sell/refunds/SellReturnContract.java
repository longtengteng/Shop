package com.lnkj.privateshop.fragment.user.sell.refunds;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.SellReutrnBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface SellReturnContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void finisht();
        void succree( SellReutrnBean beass );
        void OkRoNoGOodssuccree();
        void OnDeleteOrderSucreed();

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getReturnOrder(String shop_state,int p);
         void OkRoNoGoods(String order_sn, String refund_type,String order_goods_id);
        void ReceiveGoods(String order_sn,String pwd);
        void onUrged(String order_sn);
        void onDeleteOrder(String order_sn);
    }
}
