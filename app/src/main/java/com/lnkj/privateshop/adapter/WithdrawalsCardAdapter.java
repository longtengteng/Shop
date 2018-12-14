package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.BankCardBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class WithdrawalsCardAdapter extends BaseAdapter {
    private Context mContext;
    private List<BankCardBean.DataBean> lists;

    public WithdrawalsCardAdapter(Context mContext, List<BankCardBean.DataBean> lists) {
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
        final ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_withdrawals, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(lists.get(i).getBank_name());
        holder.tvNumber.setText(lists.get(i).getBank_num());
        holder.mCheckbox.setChecked(lists.get(i).getIscheck());
        holder.ll_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lists.get(i).getIscheck()) {
                    holder.mCheckbox.setChecked(false);
                    lists.get(i).setIscheck(false);
                } else {
                    for (int j = 0; j < lists.size(); j++) {
                        lists.get(j).setIscheck(false);
                    }
                    holder.mCheckbox.setChecked(true);
                    lists.get(i).setIscheck(true);
                }
                notifyDataSetChanged();
            }

        });

        return view;

    }

    static class ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_number)
        TextView tvNumber;
        @Bind(R.id.mCheckbox)
        CheckBox mCheckbox;
        @Bind(R.id.ll_check)
        LinearLayout ll_check;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public String getIsChexk() {
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getIscheck()) {
                return lists.get(i).getId();
            }
        }
        return "";
    }

}
