package com.lnkj.privateshop.fragment.user;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.StartShopBean;

import java.util.List;

public class StartShopAdapter extends BaseQuickAdapter<StartShopBean.DataBean, BaseViewHolder> {

    public StartShopAdapter(@Nullable List<StartShopBean.DataBean> data) {
        super(R.layout.item_startshop, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StartShopBean.DataBean item) {
        helper.setText(R.id.btn_start, item.getName());
    }
}
