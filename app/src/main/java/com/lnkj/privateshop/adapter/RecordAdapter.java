package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.TalkRecordBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class RecordAdapter extends BaseAdapter {
    private Context mContext;
    List<TalkRecordBean.DataBean> lists;

    public RecordAdapter(Context mContext, List<TalkRecordBean.DataBean> lists) {
        this.mContext = mContext;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_record, null);
            holder = new ViewHolder(view);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvTitel.setText(lists.get(i).getTitle());
        holder.tvTime.setText(lists.get(i).getAdd_time());
        if (lists.get(i).getAdmin_type().equals("0")) {
            holder.mCardView.setCardBackgroundColor(Color.parseColor("#ff7722"));
        } else {
            holder.mCardView.setCardBackgroundColor(Color.parseColor("#27a2f8"));
        }
    if (i==0){
        holder.tv_context.setVisibility(View.GONE);
    }else {
        holder.tv_context.setVisibility(View.VISIBLE);
    }
        holder.tv_context.setText(lists.get(i).getContent());

        if (TextUtils.isEmpty(lists.get(i).getReason())) {
            holder.tv_yuanyin.setVisibility(View.GONE);
        } else {
            holder.tv_yuanyin.setVisibility(View.VISIBLE);
            holder.tv_yuanyin.setText("退款原因    " + lists.get(i).getReason());
        }

        if (TextUtils.isEmpty(lists.get(i).getRefundAmont())) {
            holder.tv_price.setVisibility(View.GONE);
        } else {
            holder.tv_price.setVisibility(View.VISIBLE);
            holder.tv_price.setText("退款金额    " + lists.get(i).getRefundAmont() + "元");
        }
        if (TextUtils.isEmpty(lists.get(i).getDesc())){
            holder.tv_liuy.setVisibility(View.GONE);
        }else {
            holder.tv_liuy.setVisibility(View.VISIBLE);
            holder.tv_liuy.setText("买家留言    "+lists.get(i).getDesc());
        }

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_titel)
        TextView tvTitel;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.ll_prent)
        LinearLayout llPrent;
        @Bind(R.id.mCardView)
        CardView mCardView;
        @Bind(R.id.tv_context)
        TextView tv_context;
        @Bind(R.id.tv_yuanyin)
        TextView tv_yuanyin;
        @Bind(R.id.tv_price)
        TextView tv_price;
        @Bind(R.id.tv_liuy)
                TextView tv_liuy;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
