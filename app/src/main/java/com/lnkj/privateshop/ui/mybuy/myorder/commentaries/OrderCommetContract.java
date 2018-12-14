package com.lnkj.privateshop.ui.mybuy.myorder.commentaries;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ShopBean;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

/**
 * Created by zjh on 2016/5/11.
 */
public interface OrderCommetContract {

    //界面处理逻辑
    interface View extends BaseView {

      void succreed();
        void getShopInfoSuccreed( ShopBean beass);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
       void getDataFromServer(String order_sn,String content,int rank,int express_rank,int service_rank, ArrayList<ImageItem> images);
        void getShopInfo(String shopid);
    }
}
