package com.lnkj.privateshop.ui.mybuy.address;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AddressListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class AddressContract  {
    //界面处理逻辑
    interface View extends BaseView {

        void initView();
        void showFragment(int position);//fragment切换

        void updatePraiseView(int dif, int position);
        void setData(AddressListBean beans);
        void PullLoadMoreComplete();
        void deldetAddressSuccress(List<AddressListBean.DataBean> lists, int position);
        void setuAddressSuccress();
    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void initView();
        void exist();//退出
        void getDataFromServer();
        void deldetAddress(List<AddressListBean.DataBean> lists, int position);
        void SetuAddress(List<AddressListBean.DataBean> lists, int position,int flag);
    }
}
