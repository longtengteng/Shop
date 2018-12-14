package com.lnkj.privateshop.fragment.user.sell.comment;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ClientCommentBean;

/**
 * Created by WRJ on 2016/8/30.
 */
public class TotalCommentContract {

    interface View extends BaseView {

        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void setTitle(String s);
        void getShopCommentSucceed(ClientCommentBean beans);
        void puCommentSuccreed();
        void putCommentFailure();
    }


    interface Presenter extends BasePresenter<View> {
        void initView();
        void setToken(String token);
        void  getShomComment(String shopid, int p,String level);
        void putCommtent(String context,String commentid);
    }
}
