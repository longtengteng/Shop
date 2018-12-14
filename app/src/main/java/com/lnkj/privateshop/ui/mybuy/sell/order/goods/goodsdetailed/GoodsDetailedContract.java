package com.lnkj.privateshop.ui.mybuy.sell.order.goods.goodsdetailed;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsBean;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class GoodsDetailedContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getGoodssucceed(GoodsBean beass);
        void GoodsSucceed();
        void DownGoodsSucceed();
        void upGoodsSucceed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void initView();
        void getToken(String token);
        void getGoodsFromServer(String goodsid);
        void upGoods(String goodsid);
        void downGoods(String goodsid);


    }
}
