package com.lnkj.privateshop.ui.mybuy.userinfo;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface EditUserContract {

    //界面处理逻辑
    interface View extends BaseView {

        void toLoging();
        void upladSuccreed();

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void upladData(String head_img, String nackname);


    }
}
