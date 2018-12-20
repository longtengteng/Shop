package com.lnkj.privateshop.ui.goods;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsBean;

import java.util.List;

/*商品详情的商品属性的dialogActivity*/
public class AttrAdapter extends BaseQuickAdapter<GoodsBean.DataBean.GoodsAttrBean, BaseViewHolder> {
    public AttrAdapter(@Nullable List<GoodsBean.DataBean.GoodsAttrBean> data) {
        super(R.layout.item_attr, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean.GoodsAttrBean item) {
        helper.setText(R.id.tv_name, item.getAttr_name());
        helper.setText(R.id.tv_value, item.getAttr_value());
    }
}
