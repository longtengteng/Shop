package com.lnkj.privateshop.ui.goods.spec;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsBean;

import java.util.List;

public class SpecItemAdapter extends BaseQuickAdapter<GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean, BaseViewHolder> {
    public SpecItemAdapter(@Nullable List<GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean> data) {
        super(R.layout.item_specitem, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean item) {
        helper.setText(R.id.tv_value, item.getSpec_item_name());
        TextView tv_value = helper.getView(R.id.tv_value);
        if (item.isCheck()) {
            tv_value.setBackground(mContext.getResources().getDrawable(R.drawable.bg_00));
        } else {
            tv_value.setBackground(mContext.getResources().getDrawable(R.drawable.bg_af));
        }
    }
}
