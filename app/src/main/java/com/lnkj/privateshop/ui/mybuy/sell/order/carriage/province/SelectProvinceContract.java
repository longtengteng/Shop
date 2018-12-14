package com.lnkj.privateshop.ui.mybuy.sell.order.carriage.province;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AddressBean;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class SelectProvinceContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getAddressSuccree(AddressBean beans);
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getAddress();
            void getToken(String token);
    }
}
