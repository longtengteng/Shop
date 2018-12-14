package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.FootPrintBean;
import com.lnkj.privateshop.utils.UiUtils;
import com.lnkj.privateshop.view.MyGridView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class FootPrintAdapter extends RecyclerView.Adapter<FootPrintAdapter.ViewHolder> {

    private Context mContext;
    List<FootPrintBean.DataBean> beans;
    private int index;

    public FootPrintAdapter(Context mContext,int index) {
        this.mContext = mContext;
        this.index=index;
    }

    public void addAllData(List<FootPrintBean.DataBean> beans, int index) {
        this.beans = beans;
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
//        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_footprint, parent, false);
        View v = View.inflate(mContext,R.layout.list_item_footprint,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.tvTitle.setText(beans.get(position).getDate());
        List<FootPrintBean.DataBean.GoodsListBean> lists=   beans.get(position).get_goods_list();
        FootPrintListAdapter adapter = new FootPrintListAdapter(mContext,lists,index);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(UiUtils.getScreenWidth(mContext), ViewGroup.LayoutParams.MATCH_PARENT);
        holder.meGridview.setLayoutParams(param);
        holder.meGridview.setAdapter(adapter);
        if (index==1){
            adapter.addIndex(1);
        }else {
            adapter.addIndex(0);

        }

    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.mgridview)
        MyGridView meGridview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

//    public String getChaechData() {
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < beas.size(); i++) {
//            if (i != 0) {
//                sb.append(",");
//            }
//            sb.append(beas.get(i).getFav_id());
//        }
//
//        return sb.toString();
//    }

}
