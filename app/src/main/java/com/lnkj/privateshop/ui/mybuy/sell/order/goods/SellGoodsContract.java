package com.lnkj.privateshop.ui.mybuy.sell.order.goods;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class SellGoodsContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void noData();
        void fullData();
        void succeed();
        void showFragment(int position);//fragment切换

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void initView();
        void downSold(String goodsid,String flag);
        void getToken(String token);
    }
}
