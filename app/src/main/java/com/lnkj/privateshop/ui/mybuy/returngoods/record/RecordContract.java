package com.lnkj.privateshop.ui.mybuy.returngoods.record;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.TalkRecordBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface RecordContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void succree(TalkRecordBean beass);

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getPutFromServer(String order_sn);

    }
}
