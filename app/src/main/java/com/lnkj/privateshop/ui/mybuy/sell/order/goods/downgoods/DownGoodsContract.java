package com.lnkj.privateshop.ui.mybuy.sell.order.goods.downgoods;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.SellGoods;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class DownGoodsContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getDownGOodssucceed(SellGoods beass);
        void DownGoodsSucceed();
        void DeleteGoodsSucceed();

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void initView();
        void getToken(String token);
        void getDownGoodsFromServer();
        void DownGoods(String goodsid);
        void DeleteGoods(String id);
    }
}
