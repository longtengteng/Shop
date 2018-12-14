package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class MerchandiseListAdapter extends RecyclerView.Adapter<MerchandiseListAdapter.ViewHolder> {

    private Context mContext;
    List<ShopMerchandiseListBean.DataBean.GoodsListBean> beans;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private OnItemClickListener mOnItemClickListener = null;
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public MerchandiseListAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addAllData(List<ShopMerchandiseListBean.DataBean.GoodsListBean> beans) {
        this.beans = beans;

        notifyDataSetChanged();
    }



    public void clearData() {

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(mContext, R.layout.list_item_footprintlist, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        Glide
                .with(mContext)
                .load(Constants.Base_URL + beans.get(position).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(holder.imgGoods);
        holder.tvGoodsName.setText(beans.get(position).getGoods_name());
        holder.tvGoodsPrice.setText("¥" + beans.get(position).getShop_price()+"—¥" + beans.get(position).getPack_price());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_goods)
        ImageView imgGoods;
        @Bind(R.id.cb_check)
        CheckBox cbCheck;
        @Bind(R.id.rl_choos)
        RelativeLayout rlChoos;
        @Bind(R.id.rl_goods)
        RelativeLayout rlGoods;
        @Bind(R.id.tv_goods_name)
        TextView tvGoodsName;
        @Bind(R.id.tv_goods_price)
        TextView tvGoodsPrice;
        @Bind(R.id.mCardView)
        CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}
