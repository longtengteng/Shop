package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.BankCardBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class BankCardAdapter extends BaseAdapter {
    private Context mContext;
    private List<BankCardBean.DataBean> lists;

    public BankCardAdapter(Context mContext, List<BankCardBean.DataBean> lists) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_bankacar, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tvName.setText(lists.get(i).getBank_name());
        holder.tvNumber.setText(lists.get(i).getBank_num());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDelClickListener!=null);
                mDelClickListener.onDeleteCilck(i);
            }
        });


        return view;

    }

    static class ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_number)
        TextView tvNumber;
        @Bind(R.id.iv_delete)
        ImageView ivDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public onDelClickListener mDelClickListener;
    public void setAddressClickListener( onDelClickListener mDelClickListener) {
        this.mDelClickListener = mDelClickListener;
    }

    public interface onDelClickListener {
        void onDeleteCilck(int position ); //删除订单
    }
}
