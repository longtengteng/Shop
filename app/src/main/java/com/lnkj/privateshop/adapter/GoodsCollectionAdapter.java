package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.lnkj.privateshop.entity.GoodsCollectionBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class GoodsCollectionAdapter extends RecyclerView.Adapter<GoodsCollectionAdapter.ViewHolder> {

    private Context mContext;
    private List<GoodsCollectionBean.DataBean> beas;
    private int index;

    public GoodsCollectionAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAllData(List<GoodsCollectionBean.DataBean> beas, int index) {
        this.beas = beas;
        this.index = index;
        notifyDataSetChanged();
    }

    public void addIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

    public void clearData() {

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(mContext, R.layout.list_item_goodscollection, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        Glide
                .with(mContext)
                .load(Constants.Base_URL + beas.get(position).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(holder.imgGoods);
        holder.tvGoodsName.setText(beas.get(position).getGoods_name());
        holder.tvGoodsPrice.setText("¥" + beas.get(position).getShop_price());
        if (index == 0) {
            holder.rlChoos.setVisibility(View.GONE);
            holder.mCheckBox.setChecked(beas.get(position).getIscheck());
        } else if (index == 1) {
            holder.rlChoos.setVisibility(View.VISIBLE);
            holder.mCheckBox.setChecked(beas.get(position).getIscheck());
        }
        holder.rlChoos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.mCheckBox.isChecked()){
                    holder.mCheckBox.setChecked(false);
                    beas.get(position).setIscheck(false);
                }else {
                    holder.mCheckBox.setChecked(true);
                    beas.get(position).setIscheck(true);
                }
            }
        });
        holder. mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                intent.putExtra("goods_id",beas.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beas == null ? 0 : beas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rl_choos)
        RelativeLayout rlChoos;
        @Bind(R.id.img_goods)
        ImageView imgGoods;
        @Bind(R.id.tv_goods_name)
        TextView tvGoodsName;
        @Bind(R.id.tv_goods_price)
        TextView tvGoodsPrice;
        @Bind(R.id.cb_check)
        CheckBox mCheckBox;
        @Bind(R.id.mCardView)
        CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
    public  String getChaechData(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < beas.size(); i++) {
            if (beas.get(i).getIscheck()){
            if (i!=0){
            sb.append(",");
            }
            sb.append(beas.get(i).getFavorite_id());
            }
        }

        return sb.toString();
    }

}
