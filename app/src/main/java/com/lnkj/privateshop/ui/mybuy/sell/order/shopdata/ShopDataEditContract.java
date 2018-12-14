package com.lnkj.privateshop.ui.mybuy.sell.order.shopdata;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AddGoodsBean;
import com.lnkj.privateshop.entity.EditShopBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface ShopDataEditContract {

    //界面处理逻辑
    interface View extends BaseView {

        void getData(EditShopBean.DataBean beass);
        void getClassSucceed(AddGoodsBean beass);
        void  succreed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        /**
         * 获取分类
         */
        void getDataFromServer();

        /**
         * 获取店铺信息
         * @param shop_id
         */
        void getDataFromServer(String shop_id);

        /**
         * 保存网店信息
         */
        void saveNetShopData(String shop_real_pic,String contacts_name,String user_mobile,String province,String city,String address,String category_id,String basic_amount
        ,String retail_amount,String allow_return,String market_name,String floor_number,String room_number,String description,String company_website);

    }
}
