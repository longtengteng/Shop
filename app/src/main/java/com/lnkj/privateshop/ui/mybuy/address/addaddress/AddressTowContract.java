package com.lnkj.privateshop.ui.mybuy.address.addaddress;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.smatrAddressBean;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class AddressTowContract {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void showFragment(int position);//fragment切换
         void setClickable();
        void setClickalbeTrue();
        void updatePraiseView(int dif, int position);

        void PullLoadMoreComplete();
        void saveSuccess();
        void identifyAddressSuccess(smatrAddressBean adressBean);
        void setView(String name,String address,String province,String city,String district,String mobile,String is_default);;
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void exist();//退出
        void getDataFromServer(String addressid);
        void checkData(String addressid,String name,String phone,String address,String street,String detail,Boolean approve);
        void identifyAddress(String asddress);
    }
}
