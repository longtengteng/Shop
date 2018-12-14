package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.TimeGoodsBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class TimeGoodsListAdapter extends RecyclerView.Adapter<TimeGoodsListAdapter.ViewHolder> {


    private Context mContext;
    List<TimeGoodsBean.DataBean.ListBean> beans;
//    private HashMap<TextView,CountDownUtil> leftTimeMap = new HashMap<>();
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private OnItemClickListener mOnItemClickListener = null;

    public static interface OnItemClickListener {
        void onItemClick(int position);
    }

    public TimeGoodsListAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addAllData(List<TimeGoodsBean.DataBean.ListBean> beans) {
        this.beans = beans;

        notifyDataSetChanged();
    }


    public void clearData() {

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item_time_goods, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);

        Glide
                .with(mContext)
                .load(Constants.Base_URL + beans.get(position).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(holder.ivGoodsImg);
        holder.tvGoodsName.setText(beans.get(position).getGoods_name());
        holder.tvGoodsCount.setText(beans.get(position).getBasic_amount()+"件起批");
        holder.tvGoodsAboliPrice.setText("¥"+beans.get(position).getShop_price());
        holder.tvGoodsAboliPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        holder.tvGoodsPrice.setText("¥"+beans.get(position).getActivity_price());
//        int goods_stock =Integer.parseInt(beans.get(position).getGoods_stock());
//        int goods_sale = Integer.parseInt(beans.get(position).getGoods_sale());

        try {
        holder.tvSellCout.setText(("已售"+beans.get(position).getSale_rate()+"%"));
        holder.progressBar.setProgress(beans.get(position).getSale_rate());
        }catch (Exception e){
            holder.tvSellCout.setText("已售0%");
            holder.progressBar.setProgress(0);
        }

        holder.tvBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_goods_img)
        ImageView ivGoodsImg;
        @Bind(R.id.tv_goods_name)
        TextView tvGoodsName;
        @Bind(R.id.tv_goods_count)
        TextView tvGoodsCount;
        @Bind(R.id.tv_goods_price)
        TextView tvGoodsPrice;
        @Bind(R.id.tv_goods_aboli_price)
        TextView tvGoodsAboliPrice;
        @Bind(R.id.tv_sell_cout)
        TextView tvSellCout;
        @Bind(R.id.progressBar)
        ProgressBar progressBar;
        @Bind(R.id.tv_btn_go)
        TextView tvBtnGo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
