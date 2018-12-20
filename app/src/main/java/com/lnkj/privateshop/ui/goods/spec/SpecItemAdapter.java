package com.lnkj.privateshop.ui.goods.spec;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsBean;

import java.util.List;

public class SpecItemAdapter extends BaseQuickAdapter<GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean, BaseViewHolder> {
    public SpecItemAdapter(@Nullable List<GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean> data) {
        super(R.layout.item_specitem, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean item) {
        helper.setText(R.id.tv_value,item.getSpec_item_name());
    }
}
