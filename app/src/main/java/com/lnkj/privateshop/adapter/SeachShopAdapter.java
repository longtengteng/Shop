package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.SeachShopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class SeachShopAdapter extends RecyclerView.Adapter<SeachShopAdapter.ViewHolder> {



    private Context mContext;
    public List<SeachShopBean.DataBean.ListBean> beas = new ArrayList<>();


    public SeachShopAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAllData(List<SeachShopBean.DataBean.ListBean> beas) {
        this.beas=beas;
        notifyDataSetChanged();
    }


    public void clearData() {
        beas.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shop_seach, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        Glide
                .with(mContext)
                .load(Constants.Base_URL + beas.get(position).getShop_logo())
                .error(R.mipmap.bg_img)
                .into(holder.imgShopHead);
        holder.tvShopName.setText(beas.get(position).getShop_name());
        holder.tvShopAddress.setText(beas.get(position).getProvince()+"·"+beas.get(position).getCity()+"·"+beas.get(position).getAddress());
        holder.tvOpenShopDate.setText(beas.get(position).getAdd_time());
        holder.tvShopSales.setText(beas.get(position).getGoods_counts());
        holder.tvShopUpdate.setText(beas.get(position).getMonth_goods_count());
        holder.tvShopSupplement.setText(beas.get(position).getSupplement()+"%");
        holder.ll_parent.setOnClickListener(new View.OnClickListener() {
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
        @Bind(R.id.img_shop_head)
        CircleImageView imgShopHead;
        @Bind(R.id.tv_shop_name)
        TextView tvShopName;
        @Bind(R.id.tv_shop_address)
        TextView tvShopAddress;
        @Bind(R.id.tv_open_shop_date)
        TextView tvOpenShopDate;
        @Bind(R.id.tv_shop_sales)
        TextView tvShopSales;
        @Bind(R.id.tv_shop_update)
        TextView tvShopUpdate;
        @Bind(R.id.tv_shop_supplement)
        TextView tvShopSupplement;
    @Bind(R.id.ll_parent)
        LinearLayout ll_parent;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public OrderClickListener mOrderClickListener;

    public void setmClickListener(OrderClickListener mOrderClickListener) {
        this.mOrderClickListener = mOrderClickListener;
    }

    public interface OrderClickListener {
        void oncollectShop(int position);

    }

}
