package com.lnkj.privateshop.ui.limitsalelist;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AddriGoodsBean;
import com.lnkj.privateshop.entity.ClassGoodsBean;
import com.lnkj.privateshop.entity.LimitSaleListBean;
import com.lnkj.privateshop.entity.SeachGoodsBean;
import com.lnkj.privateshop.entity.SizeBean;
import com.lnkj.privateshop.ui.seachgoods.SeachGoodsContract;

public class LimitSaleContract {
    //界面处理逻辑
    interface View extends BaseView {

        void toLoging();

        void PullLoadMoreComplete();

        void succeed(LimitSaleListBean beass);


    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<LimitSaleContract.View> {
        void getToken(String token);

        void getDataFromServer( int page);

        void setToken(String token);

        void getSize();
    }
}
