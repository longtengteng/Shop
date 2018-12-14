package com.lnkj.privateshop.fragment.user.sell.goods.up;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.SellGoods;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class SellGoodsContract {
    interface View extends BaseView {
        void initView();
        void updateView(int id);
        void upateFragmentData(int type);
        void liftData(SellGoods beans);
        void remindDelivery(String s);
        void addIndex(int index);
        String getGoodsId();
    }

    interface Presenter extends BasePresenter<View> {
        void clearAttention(int type);
        void getDataFromService(int goods_state);
        void getToken(String token);



    }
}
