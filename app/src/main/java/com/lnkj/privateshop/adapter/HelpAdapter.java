package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.HelpBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class HelpAdapter extends BaseAdapter {
    private Context mContext;
    private List<HelpBean.DataBean> lists;

    public HelpAdapter(Context mContext, List<HelpBean.DataBean> lists) {
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
        ViewHolder holder ;
        if (view ==null){
            view = View.inflate(mContext, R.layout.list_item_help, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tvTite.setText(lists.get(i).getTitle());
        holder.tvContext.setText(lists.get(i).getIntroduce());

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_tite)
        TextView tvTite;
        @Bind(R.id.tv_context)
        TextView tvContext;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
