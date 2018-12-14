package com.lnkj.privateshop.fragment.looking;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.GoodsCategoryBean;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class LookingContract {

    interface View extends BaseView {
        void initView();
        void updateView(int id);
        void upateFragmentData(int type);
        void liftData(GoodsCategoryBean beans);

    }

    interface Presenter extends BasePresenter<View> {
        void clearAttention(int type);
        void getDataFromService();

    }

}
