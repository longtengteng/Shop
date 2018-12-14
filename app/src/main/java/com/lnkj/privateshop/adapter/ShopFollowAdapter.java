package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.FollowShopListBean;
import com.lnkj.privateshop.ui.shop.shopInfo.ShopInfoActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class ShopFollowAdapter extends RecyclerView.Adapter<ShopFollowAdapter.ViewHolder> {

    private Context mContext;
    private List<FollowShopListBean.DataBean> beas;
    private int index;

    public ShopFollowAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAllData(List<FollowShopListBean.DataBean> beas, int index) {
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
        View v = View.inflate(mContext, R.layout.list_item_shopfollow, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        Glide
                .with(mContext)
                .load(Constants.Base_URL + beas.get(position).getShop_info().getShop_logo())
                .error(R.mipmap.bg_img)
                .into(holder.imgShop);
        holder.tvShopName.setText(beas.get(position).getShop_info().getShop_name());
        holder.shopAddress.setText(beas.get(position).getShop_info().getProvince()+"·"+beas.get(position).getShop_info().getCity()+"·"+beas.get(position).getShop_info().getAddress());
        if (index == 0) {
            holder.cbCheck.setVisibility(View.GONE);
            holder.cbCheck.setChecked(beas.get(position).getIscheck());
        } else if (index == 1) {
            holder.cbCheck.setVisibility(View.VISIBLE);
            holder.cbCheck.setChecked(beas.get(position).getIscheck());
        }
        holder.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                beas.get(position).setIscheck(b);
            }
        });
        holder.ll_prent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShopInfoActivity.class);
                intent.putExtra("shop_id",beas.get(position).getShop_info().getShop_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beas == null ? 0 : beas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cb_check)
        CheckBox cbCheck;
        @Bind(R.id.img_shop)
        ImageView imgShop;
        @Bind(R.id.tv_shop_name)
        TextView tvShopName;
        @Bind(R.id.shop_address)
        TextView shopAddress;
        @Bind(R.id.ll_prent)
        LinearLayout ll_prent;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public String getChaechData() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < beas.size(); i++) {
            if (beas.get(i).getIscheck()){

            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(beas.get(i).getFav_id());
            }
        }

        return sb.toString();
    }

}
