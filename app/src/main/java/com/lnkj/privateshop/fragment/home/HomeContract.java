package com.lnkj.privateshop.fragment.home;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AdvertisingBean;
import com.lnkj.privateshop.entity.BannerBean;
import com.lnkj.privateshop.entity.LimitedFavourBean;
import com.lnkj.privateshop.entity.OrderWholeSaleBean;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class HomeContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getBannerSucceed(BannerBean beass);
        void okGoods();
        void getTimeSuccreed(LimitedFavourBean beass);
        void getWholesaleSuccreed(OrderWholeSaleBean beass);
        void getAdvertisingSuccreed(AdvertisingBean beass);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void initView();
        void getToken(String token);
        void getBannerFromServer();
        void  getAdvertising();
        void getTime();
        void getWholesale();

    }
}
