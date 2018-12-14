package com.lnkj.privateshop.ui.mybuy.sttting.chagepwd;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ChagePwdContract {

    //界面处理逻辑
    interface View extends BaseView {

        void toLoging();

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void upladData(String pwd,String pwdto,String pwdthree);
    }
}
