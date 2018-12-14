package com.lnkj.privateshop.fragment.user.lookredund;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.LookBedundBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface LookRedundContract {

    //界面处理逻辑
    interface View extends BaseView {
        void initView();
        void setData(LookBedundBean beans);
        void PullLoadMoreComplete();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void getDataFromServer(String type,int p);
    }
}
