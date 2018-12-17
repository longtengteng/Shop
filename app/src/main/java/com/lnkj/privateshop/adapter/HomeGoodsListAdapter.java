package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsToListBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class HomeGoodsListAdapter extends RecyclerView.Adapter{
    public static final int ITEM_TYPE_AFVERTIS = 0;
    public static final int ITEM_TYPE_CONTENT = 1;


    private Context mContext;
    List<GoodsToListBean> beans;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private OnItemClickListener mOnItemClickListener = null;
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public HomeGoodsListAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addAllData(List<GoodsToListBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }


    public void clearData() {
        notifyDataSetChanged();
    }
    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        if (beans.get(position).getType()==0) {
//广告View
            return ITEM_TYPE_AFVERTIS;
        } else {
//内容View
            return ITEM_TYPE_CONTENT;
        }
    }
    public int getItemCount() {
        return beans==null?0:beans.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View v ;
        if (viewType==ITEM_TYPE_AFVERTIS){
           v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_adver, parent, false);
            holder = new AdvertisViewHolder(v);
            return holder;
        }else {
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_footprintlist, parent, false);
            holder = new ViewHolder(v);
            return holder;
        }
    }
    @Override
    public void onBindViewHolder( final  RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        if (getItemViewType(position)==ITEM_TYPE_AFVERTIS){
            Glide
                    .with(mContext)
                    .load(Constants.Base_URL + beans.get(position).getContent())
                    .error(R.mipmap.bg_img)
                    .into(((AdvertisViewHolder) holder).mImegeView);
            ((AdvertisViewHolder) holder).mImegeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(view,position);
                    }
                }
            });
        }else {
            Glide
                    .with(mContext)
                    .load(Constants.Base_URL + beans.get(position).getGoods_img())
                    .error(R.mipmap.bg_img)
                    .into(((ViewHolder) holder).imgGoods);
            ((ViewHolder) holder).tvGoodsName.setText(beans.get(position).getGoods_name());
            ((ViewHolder) holder).tvGoodsPrice.setText("¥" + beans.get(position).getShop_price());
            ((ViewHolder) holder).mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(view,position);
                    }
                }
            });
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_goods)
        public ImageView imgGoods;
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

    //头部 ViewHolder
    public static class AdvertisViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImegeView;

        public AdvertisViewHolder(View itemView) {
            super(itemView);
            mImegeView = (ImageView) itemView.findViewById(R.id.mImageView);
        }
    }

}
