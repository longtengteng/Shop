package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.LimitSaleListBean;
import com.lnkj.privateshop.entity.SeachGoodsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class LimitSaleAdapter extends RecyclerView.Adapter<LimitSaleAdapter.ViewHolder> {

    public OrderClickListener mOrderClickListener;

    public void setmClickListener(OrderClickListener mOrderClickListener) {
        this.mOrderClickListener = mOrderClickListener;
    }

    public interface OrderClickListener {
        void oncollectShop(int position);

    }

    private Context mContext;
    public List<LimitSaleListBean.DataBean> beas = new ArrayList<>();


    public LimitSaleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAllData(List<LimitSaleListBean.DataBean> beas) {
        this.beas = beas;
        notifyDataSetChanged();
    }


    public void clearData() {
        beas.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(mContext, R.layout.item_limitsale, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        Glide
                .with(mContext)
                .load(Constants.Base_URL + beas.get(position).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(holder.imgGoods);
        holder.tv_title.setText(beas.get(position).getGoods_name());
        holder.tv_new_price.setText("现价:¥" + beas.get(position).getNow_price());
        holder.tv_origianl.setText("原价:¥" + beas.get(position).getShop_price());

        holder.tv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOrderClickListener != null) {
                    mOrderClickListener.oncollectShop(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return beas == null ? 0 : beas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_goods)
        ImageView imgGoods;
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_origianl)
        TextView tv_origianl;
        @Bind(R.id.tv_new_price)
        TextView tv_new_price;
        @Bind(R.id.tv_buy)
        TextView tv_buy;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
