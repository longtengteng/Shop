package com.lnkj.privateshop.fragment.user.sell;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.entity.SellUserBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class selluserContract {

    interface View extends BaseView {
        void initView();

        void updateView(int id);

        void upateFragmentData(int type);

        void liftData(GoodsCategoryBean beans);

        void getUserInfoSuccreed(SellUserBean beans);

        void getOrderDataSuccreed(int status_1, int status_2, int status_3, String status_5);

        void getServiceInfo(ServiceEmchatBean.DataBean serviceEmchatBean);
    }

    interface Presenter extends BasePresenter<View> {
        void clearAttention(int type);

        void getDataFromService();

        void setToken(String token);

        void getOrderData();

        void getServiceInfo();
    }

}
