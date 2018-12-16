package com.lnkj.privateshop.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.HomeLimitFavourBean;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public class HomeLimitAdapter extends BaseQuickAdapter<HomeLimitFavourBean.DataBean, BaseViewHolder> {
    public HomeLimitAdapter(@Nullable List<HomeLimitFavourBean.DataBean> data) {
        super(R.layout.item_homelimit, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeLimitFavourBean.DataBean item) {
        if (item == null)
            return;
        helper.setText(R.id.tv_name, item.getGoods_name());
        helper.setText(R.id.tv_original_price, item.getShop_price());
        helper.setText(R.id.tv_money_now, item.getNow_price());

        CountdownView mCvCountdownView = (CountdownView) helper.getView(R.id.mCvCountdownView);
        long time = (item.getEnd_time() - item.getNow_time()) * 1000;
        mCvCountdownView.start(time); // 毫秒


        Glide
                .with(mContext)
                .load(Constants.Base_URL + item.getGoods_img())
                .error(R.mipmap.bg_img)
                .into((ImageView) helper.getView(R.id.iv_goods));

    }
}
