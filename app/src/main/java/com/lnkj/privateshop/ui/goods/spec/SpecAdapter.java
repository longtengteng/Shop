package com.lnkj.privateshop.ui.goods.spec;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class SpecAdapter extends BaseQuickAdapter<GoodsBean.DataBean.GoodsSpecBean, BaseViewHolder> {


    interface CheckClick {
        void checkSpec(GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean itemArrayBean, int list_position);
    }

    CheckClick m;

    public CheckClick getM() {
        return m;
    }

    public void setM(CheckClick m) {
        this.m = m;
    }


    public SpecAdapter(@Nullable List<GoodsBean.DataBean.GoodsSpecBean> data) {
        super(R.layout.item_spec, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodsBean.DataBean.GoodsSpecBean item) {
        helper.setText(R.id.tv_title, item.getSpec_name());
        //   helper.addOnClickListener(R.id.rvSpec_item);
        RecyclerView rvSpec_item = helper.getView(R.id.rvSpec_item);
        final SpecItemAdapter specItemAdapter;
        rvSpec_item.setLayoutManager(new GridLayoutManager(mContext, 6));
        specItemAdapter = new SpecItemAdapter(item.getItem_array());
        specItemAdapter.bindToRecyclerView(rvSpec_item);
        specItemAdapter.setAutoLoadMoreSize(1);
        specItemAdapter.disableLoadMoreIfNotFullPage(rvSpec_item);

        //    final List<GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean> itemArrayBeans = item.getItem_array();
        specItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < item.getItem_array().size(); i++) {
                    if (position == i) {
                        item.getItem_array().get(i).setCheck(true);
                    } else {
                        item.getItem_array().get(i).setCheck(false);
                    }
                }
                specItemAdapter.setNewData(item.getItem_array());
                m.checkSpec(item.getItem_array().get(position), helper.getAdapterPosition());
            }
        });


    }


}
