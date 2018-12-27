package com.lnkj.privateshop.ui.mybuy.followshops;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.FollowShopListBean;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class FollowContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();

        void noData();

        void fullData();

        void showFragment(int position);//fragment切换

        void setGoodsCollectData(FollowShopListBean beass);

        void deleteSuccess();

        void PullLoadMoreComplete();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void initView();

        void deldetGoods(String goodsid);

        void getDataFromServer(int p);
//        boolean isLogin();//是否登录
    }
}
