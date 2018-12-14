package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ExpressBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class LookTransitAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExpressBean.DataBean.TracesBean> lists;

    public LookTransitAdapter(Context mContext, List<ExpressBean.DataBean.TracesBean> lists) {
        this.mContext = mContext;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists==null?0:lists.size();

    }

    @Override
    public ExpressBean.DataBean.TracesBean getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_look_transit, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
            holder.tvTitle.setText(lists.get(i).getAcceptStation());
             holder.tvTime.setText(lists.get(i).getAcceptTime());
        if (i==getCount()-1){
            holder.tvRound.setVisibility(View.GONE);
        }else {
            holder.tvRound.setVisibility(View.VISIBLE);
        }
        try {
        if (lists.get(i).getAcceptStation().substring(0,3).equals("已签收")){
            holder.ivRound.setImageResource(R.drawable.round);
        }else {
            holder.ivRound.setImageResource(R.drawable.round_2);
        }
        }catch (Exception e){}
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.iv_round)
        ImageView ivRound;
        @Bind(R.id.tv_round)
        TextView tvRound;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_time)
        TextView tvTime;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }
}
