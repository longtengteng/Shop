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
import com.lnkj.privateshop.entity.SeachGoodsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class SeachGoodsAdapter extends RecyclerView.Adapter<SeachGoodsAdapter.ViewHolder> {

    public OrderClickListener mOrderClickListener;

    public void setmClickListener(OrderClickListener mOrderClickListener) {
        this.mOrderClickListener = mOrderClickListener;
    }

    public interface OrderClickListener {
        void oncollectShop(int position);

    }

    private Context mContext;
    public List<SeachGoodsBean.DataBean> beas = new ArrayList<>();


    public SeachGoodsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAllData(List<SeachGoodsBean.DataBean> beas) {
        this.beas=beas;
        notifyDataSetChanged();
    }


    public void clearData() {
        beas.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(mContext, R.layout.list_item_seach_goods, null);
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
        holder.tvGoodsName.setText(beas.get(position).getGoods_name());
        holder.tvGoodsName.setText(beas.get(position).getGoods_name());
        holder.tvGoodsPrice.setText("¥" + beas.get(position).getPack_price() + "—" + "¥" + beas.get(position).getShop_price());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOrderClickListener!=null){
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
        @Bind(R.id.img_goods)
        ImageView imgGoods;
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
