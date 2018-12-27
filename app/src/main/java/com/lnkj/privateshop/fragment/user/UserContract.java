package com.lnkj.privateshop.fragment.user;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;
import com.lnkj.privateshop.entity.StartShopBean;
import com.lnkj.privateshop.entity.UseInfoBean;

public class UserContract {
    interface View extends BaseView {
        void initView();

        void apply_start_shop(StartShopBean startShopBean);
    }

    interface Presenter extends BasePresenter<View> {

        void setToken(String token);

        void apply_start_shop();
    }
}
