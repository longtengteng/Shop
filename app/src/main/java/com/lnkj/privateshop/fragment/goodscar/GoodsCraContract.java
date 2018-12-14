package com.lnkj.privateshop.fragment.goodscar;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsCraListBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class GoodsCraContract {

    interface View extends BaseView {

        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void getShopCommentSucceed(GoodsCraListBean beans);
        void deleteGoodsCarSuccreed();
        void getGoodsInfoSucceed();
        void btnClickable(boolean clickable);

    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void setToken(String token);
        void  getGoodsCar();
        void deleteGoodsCar(String goods_id);
        void getGoodsInfo(String goodsid);
    }
}
