package com.lnkj.privateshop.ui.mybuy.sell.order.goods.upgoods;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.SellGoods;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class UpGoodsContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getUpGOodssucceed( SellGoods beass);
        void upGoodsSucceed();
        void DeleteGoodsSucceed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void initView();
        void getToken(String token);
        void getUpGoodsFromServer();
        void upGoods(String goodsid);
        void DeleteGoods(String id);
    }
}
