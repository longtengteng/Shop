package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.LookBedundBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class LookRedundAdapter extends RecyclerView.Adapter<LookRedundAdapter.ViewHolder> {


    private Context mContext;
    private int index;
    List<LookBedundBean.DataBean.LogListBean> orderlist;

    public LookRedundAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addAllData(List<LookBedundBean.DataBean.LogListBean> orderlist) {
        this.orderlist = orderlist;

        notifyDataSetChanged();
    }

    public void clearData() {
        this.orderlist.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_lookredund, parent, false);
        return new ViewHolder(v);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final LookBedundBean.DataBean.LogListBean beans = orderlist.get(position);
        holder.itemView.setTag(position);

       String date =  beans.getDate();
        holder.tvTime.setText(date);
        holder.tvAnount.setText(beans.getChange_sign()+beans.getUser_money());
        if (beans.getChange_sign().startsWith("-")){
            holder.tvTitle.setText("支");
            holder.tvTitle.setBackgroundResource(R.drawable.round_blue);
        }else {
            holder.tvTitle.setText("收");
            holder.tvTitle.setBackgroundResource(R.drawable.round_orange);
        }
         holder.tvContent.setText(beans.getChange_desc());
//    }
        if (position!=0){
            if (beans.getDate().equals(orderlist.get(position-1).getDate())){
                holder.tvTime.setVisibility(View.GONE);
            }else {
                holder.tvTime.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return orderlist == null ? 0 : orderlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_anount)
        TextView tvAnount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
