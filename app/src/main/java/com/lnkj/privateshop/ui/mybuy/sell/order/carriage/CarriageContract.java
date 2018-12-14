package com.lnkj.privateshop.ui.mybuy.sell.order.carriage;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ExpressTemplateBean;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class CarriageContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void getExpressTemplateSuccree(ExpressTemplateBean beans);
        void deletesrccreed();
        void addSuccreed();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getExpressTemplate();
        void delete(String express_template_id);
            void getToken(String token);
        void addExpressTempLate(String default_first_money,String default_add_money,String first_money,String add_money,String region_id,
                                String area_name,String express_template_name);
    }
}
