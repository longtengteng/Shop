package com.lnkj.privateshop.ui.ease;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.HelpBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface EaseConversationListContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getOrderData(HelpBean beans);
        void getServiceInfo(ServiceEmchatBean.DataBean serviceEmchatBean);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer();
        void getServiceInfo();
//        boolean isLogin();//是否登录
    }
}
