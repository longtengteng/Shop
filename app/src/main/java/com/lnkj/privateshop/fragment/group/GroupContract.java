package com.lnkj.privateshop.fragment.group;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ChatBean;
import com.lnkj.privateshop.entity.DynamicragBean;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class GroupContract {

    interface View extends BaseView {
        void initView();
        void getDynamicragSuceed( DynamicragBean beass);
        void getChatFromSuceed(ChatBean beass);
        void SetColloectShopSuccreed();
    }

    interface Presenter extends BasePresenter<View> {

        void getDynamicragFromService(int page);
        void getChatFromService(int page);
        void setToken(String token);
        void setCollectShop(String shopid);
    }

}
