package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.SellGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lnkj.privateshop.R.id.ll_prent;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {


    private Context mContext;
    private int index;
    private List<SellGoods.DataBean.GoodsListBean> orderlist=new ArrayList<>();

    public GoodsAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public OnItemClickListener mOnItemClickListener = null;

    public  interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }



    public   void listisfalst(){
        if (orderlist!=null){
            for (int i = 0; i < orderlist.size(); i++) {
                    orderlist.get(i).setIschecked(false);
            }
        }
    }
    public void addAllData(List<SellGoods.DataBean.GoodsListBean> orderlist, int index) {
//        this.orderlist.clear();
        this.orderlist =orderlist;
        this.index = index;
        notifyDataSetChanged();
    }
    public void addIndex(int index){
        this.index = index;
        notifyDataSetChanged();
    }

    public void clearData() {
        this.orderlist.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sell_goods, parent, false);
        return new ViewHolder(v);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        Glide
                .with(mContext)
                .load(Constants.Base_URL + orderlist.get(position).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(holder.imgGoods);
        holder.tvGoodsName.setText(orderlist.get(position).getGoods_name());
        holder.tvPrice.setText("￥" + orderlist.get(position).getPack_price() + " - ￥" + orderlist.get(position).getShop_price());
        holder.shopAddress.setText(orderlist.get(position).getClick_count() + "次浏览   销量" + orderlist.get(position).getSale_num());
//        holder.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    orderlist.get(position).setIschecked(b);
//                System.out.println(position+"__"+orderlist.get(position).getIschecked());
//            }
//        });

       holder.cbCheck.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               boolean checked = ((CheckBox) view).isChecked();
               orderlist.get(position).setIschecked(checked);
           }
       });

        if (index==1){
            holder.cbCheck.setVisibility(View.VISIBLE);
        }else {
            holder.cbCheck.setVisibility(View.GONE);
        }
        holder.cbCheck.setChecked(orderlist.get(position).getIschecked());

        holder.llPrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("mOnItemClickListener"+mOnItemClickListener);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }
    public String  getGoodsId() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderlist.size(); i++) {
            if (orderlist.get(i).getIschecked()){
                if (sb.length()!=0){
                    sb.append(",");
                }
                sb.append(orderlist.get(i).getGoods_id());
            }
        }

        return  sb.toString();
    }
    @Override
    public int getItemCount() {
        return orderlist == null ? 0 : orderlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cb_check)
        CheckBox cbCheck;
        @Bind(R.id.img_goods)
        ImageView imgGoods;
        @Bind(R.id.tv_goods_name)
        TextView tvGoodsName;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.shop_address)
        TextView shopAddress;
        @Bind(ll_prent)
        LinearLayout llPrent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }



}
