package com.lnkj.privateshop.ui.mybuy.feedback;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface BeedBackContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void succree();

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void getPutFromServer(String content,String phone);

    }
}
