package com.lnkj.privateshop.fragment.user.refunds;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ReutrnBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ReturnContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void finisht();
        void succree(ReutrnBean beass);
        void DeleteSuccreed();
        void RevocationSucreed(int position);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getReturnOrder(String shop_state);
        void onDeleteOrder(String Order_sn,int position);
        void onRevocation(String order_sn,int position);
    }
}
