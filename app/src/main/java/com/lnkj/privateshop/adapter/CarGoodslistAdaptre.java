package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.OrderConBean;
import com.lnkj.privateshop.ui.addshoppingcart.altercart.AlterGoodsCarActivity;
import com.lnkj.privateshop.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class CarGoodslistAdaptre extends BaseAdapter {
    private Context mContext;
    List<OrderConBean.DataBean.ListBean.GoodsListBean> goodslist;

    //public int index;
    public CarGoodslistAdaptre(Context mContext, List<OrderConBean.DataBean.ListBean.GoodsListBean> goodslist) {
        this.mContext = mContext;
        this.goodslist = goodslist;
    }

    @Override
    public int getCount() {
        return goodslist == null ? 0 : goodslist.size();
    }

    @Override
    public Object getItem(int i) {
        return goodslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_time_goods_list, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvGoodsName.setText(goodslist.get(i).getGoods_name());
        holder.tvPrice.setText("￥" + goodslist.get(i).getPrice());
        Glide.with(mContext).load(Constants.Base_URL + goodslist.get(i).getGoods_img())
                .error(R.mipmap.bg_img)
                .placeholder(R.mipmap.bg_img)
                .into(holder.ivGoodsImg);
       /* holder.tvBtnAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AlterGoodsCarActivity.class);
                intent.putExtra("goods_id", goodslist.get(i).getGoods_id());
                mContext.startActivity(intent);
            }
        });*/
        holder.ll_check.setVisibility(View.GONE);
        holder.tvBtnAlter.setVisibility(View.VISIBLE);
        holder.tvBtnAlter.setText(goodslist.get(i).getSpec_name());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_btn_alter)
        TextView tvBtnAlter;
        @Bind(R.id.cb_check_goods)
        CheckBox cbCheckGoods;
        @Bind(R.id.iv_goods_img)
        ImageView ivGoodsImg;
        @Bind(R.id.tv_goods_name)
        TextView tvGoodsName;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_count)
        TextView tvCount;
        @Bind(R.id.mGridView)
        MyGridView mGridView;
        @Bind(R.id.ll_check)
        LinearLayout ll_check;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
