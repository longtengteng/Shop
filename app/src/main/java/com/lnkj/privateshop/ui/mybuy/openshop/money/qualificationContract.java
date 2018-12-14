package com.lnkj.privateshop.ui.mybuy.openshop.money;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ClassGoodsBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface qualificationContract {

    //界面处理逻辑
    interface View extends BaseView {

        void succeed();
        void classSucceed(ClassGoodsBean beass);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer(String shop_id,String name,String http,String path_zhizhao,String path_factory,String chenjia);
        void putQealityFromServer(String shop_id,String name,String http,String path_zhizhao);


        void setToken(String token);
    }
}
