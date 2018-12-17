package com.lnkj.privateshop.fragment.home.goodslist;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AdvertisingBean;
import com.lnkj.privateshop.entity.HomeGoodsListbean;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class GoodsListContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();

        void getGoodsListSucceed(HomeGoodsListbean beass);

        void okGoods();

        void getAdvertisingSuccreed(AdvertisingBean beass);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void initView();

        void getToken(String token);

        void getDataFromServer(int p, String cat_id);

        void getAdvertising();

    }
}
