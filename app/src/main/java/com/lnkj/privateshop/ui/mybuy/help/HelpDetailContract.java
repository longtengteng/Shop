package com.lnkj.privateshop.ui.mybuy.help;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;

/**
 * Created by zjh on 2016/5/11.
 */
public interface HelpDetailContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void succree(String title,String content,String addtime);

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void getPutFromServer(String id);

    }
}
