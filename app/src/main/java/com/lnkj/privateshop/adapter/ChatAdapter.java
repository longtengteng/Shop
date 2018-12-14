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
import com.lnkj.privateshop.entity.ChatBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context mContext;
    List<ChatBean.DataBean> orderlist;

    public ChatAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addAllData(List<ChatBean.DataBean> orderlist) {
        this.orderlist = orderlist;
        notifyDataSetChanged();
    }

    public void clearData() {
        this.orderlist.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item_chat, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ChatBean.DataBean bean = orderlist.get(position);
        holder.itemView.setTag(position);
        Glide
                .with(mContext)
                .load(Constants.Base_URL +bean.getLitpic())
                .error(R.mipmap.bg_img)
                .into(holder.imgHead);
        holder.tvName.setText(bean.getTitle());
        holder.tvTime.setText(bean.getCreate_time());
        holder.tvCcount.setText(bean.getDesc());
        holder.tvCum.setText(bean.getMax_member());
        holder.ll_prent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrderClickListener.onOrderCilck(bean.getRoom_id(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderlist == null ? 0 : orderlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_head)
        CircleImageView imgHead;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_cum)
        TextView tvCum;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_ccount)
        TextView tvCcount;
        @Bind(R.id.ll_prent)
        LinearLayout ll_prent;
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
        void onOrderCilck(String goodsId,int pos);
    }

}
