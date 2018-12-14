package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;
import com.lnkj.privateshop.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class ShopGoodsAdapter extends RecyclerView.Adapter<ShopGoodsAdapter.ViewHolder> {

    private Context mContext;
    private List<ShopMerchandiseListBean.DataBean.GoodsListBean> beas;

    public ShopGoodsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAllData(List<ShopMerchandiseListBean.DataBean.GoodsListBean> beas) {
        this.beas = beas;
        notifyDataSetChanged();
    }


    public void clearData() {
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shop_goods, parent, false);
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
        holder.tvGoodsPrice.setText("¥" + beas.get(position).getPack_price() + "—" + "¥" + beas.get(position).getShop_price());
        if (beas.get(position).getIscheck()) {
            holder.mCheckBox.setChecked(true);
        } else {
            holder.mCheckBox.setChecked(false);
        }
        holder.mLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (beas.get(position).getIscheck()) {
                    beas.get(position).setIscheck(false);
                } else {
                    int ischeck = 0;
                    for (int i = 0; i < beas.size(); i++) {
                        if (beas.get(i).getIscheck()){
                        ischeck ++;
                        }
                    }
                    if (ischeck>8){
                        ToastUtil.showToast("商品最多只能选择9件");
                    }else {
                    beas.get(position).setIscheck(true);
                    }
                }
                notifyDataSetChanged();
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
        @Bind(R.id.mLinear)
        LinearLayout mLinear;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public List<ShopMerchandiseListBean.DataBean.GoodsListBean> getChaechId() {
        List<ShopMerchandiseListBean.DataBean.GoodsListBean> lists = new ArrayList<>();
//        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < getItemCount(); i++) {
            System.out.println("————");
            if (beas.get(i).getIscheck()) {
                lists.add(beas.get(i));
                System.out.println("————————————————");
//            if (sb.length()!=0){
//            sb.append(",");
//            }
//            sb.append(beas.get(i).getGoods_id());
            }
        }
        return lists;
    }

    public String getCheachImg() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < beas.size(); i++) {
            if (beas.get(i).getIscheck()) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(beas.get(i).getGoods_img());
            }
        }
        return sb.toString();
    }

}
