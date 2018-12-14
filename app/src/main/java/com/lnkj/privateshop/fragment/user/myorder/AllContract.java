package com.lnkj.privateshop.fragment.user.myorder;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.entity.OrderAllBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class AllContract  {
    interface View extends BaseView {
        void initView();
        void updateView(int id);
        void upateFragmentData(int type);
        void liftData(GoodsCategoryBean beans);
        void remoOrder(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position, String s);
        void okGoods(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position, String s);
        void onCancelorderScuurdde(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position, String s);
        void remindDelivery(String s);
        void  Scrrueed( OrderAllBean beass);
    }

    interface Presenter extends BasePresenter<View> {
        void clearAttention(int type);
        void getDataFromService(int p,int status);
        void getToken(String token);
        /**
         * 删除订单
         * @param position
         */
        void onDeleteOrder(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position);



        /**
         * 提醒发货
         * @param position
         */
        void onRemindDelivery(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position);



        /**
         * 确认收货
         * @param position
         */
        void onOkGoods(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position);
        /**
         *
         */
        void onCancelorder(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position);
    }
}
