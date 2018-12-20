package com.lnkj.privateshop.ui.goods.spec;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsBean;

import java.util.List;

public class SpecAdapter extends BaseQuickAdapter<GoodsBean.DataBean.GoodsSpecBean, BaseViewHolder> {

    SpecItemAdapter specItemAdapter;

    public SpecAdapter(@Nullable List<GoodsBean.DataBean.GoodsSpecBean> data) {
        super(R.layout.item_spec, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean.GoodsSpecBean item) {
        helper.setText(R.id.tv_title, item.getSpec_name());
        RecyclerView rvSpec_item=helper.getView(R.id.rvSpec_item);
        rvSpec_item.setLayoutManager(new GridLayoutManager(mContext,6));
        specItemAdapter = new SpecItemAdapter(item.getItem_array());
        specItemAdapter.bindToRecyclerView(rvSpec_item);
        specItemAdapter.setAutoLoadMoreSize(1);
        specItemAdapter.disableLoadMoreIfNotFullPage(rvSpec_item);


    }
}
