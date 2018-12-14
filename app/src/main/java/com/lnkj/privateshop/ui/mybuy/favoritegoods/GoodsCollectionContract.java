package com.lnkj.privateshop.ui.mybuy.favoritegoods;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsCollectionBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface GoodsCollectionContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void noData();
        void fullData();
        void showFragment(int position);//fragment切换
    void setGoodsCollectData(GoodsCollectionBean beass);
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
