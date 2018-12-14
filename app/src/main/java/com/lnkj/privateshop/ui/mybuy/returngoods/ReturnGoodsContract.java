package com.lnkj.privateshop.ui.mybuy.returngoods;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ReturnGoodsBean;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ReturnGoodsContract {

    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void finisht();
        void succree();
        void getRetrunSuccreed(ReturnGoodsBean beass);
        void getReturnPrice(String pice);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void getPutFromServer(String type,String goods_id,String order_goods_id,String description,String order_sn, String reason, ArrayList<ImageItem> images);

        void getReturn();
        void getReturnPrice(String order_sn,String goods_id);
    }
}
