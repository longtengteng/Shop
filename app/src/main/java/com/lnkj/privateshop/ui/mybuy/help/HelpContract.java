package com.lnkj.privateshop.ui.mybuy.help;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.HelpBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface HelpContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void finisht();
        void succree( HelpBean beass);

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void getPutFromServer();

    }
}
