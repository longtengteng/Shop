package com.lnkj.privateshop.fragment.shop.shopinfo;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.MenGoodsListBean;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class MerchandiseContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getGoodsListSucceed(ShopMerchandiseListBean beass);
        void okGoods();
        void getMenGoodsSuccreed(MenGoodsListBean beans);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void initView();
        void getToken(String token);
        void getDataFromServer(int p, String sort,String shop_id);
        void getDataFromServer(int p,String shop_id);


    }
}
