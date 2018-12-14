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
 * Created by Administrator on 2017/10/10 0010.
 */

public class NoticeAdapter extends BaseAdapter {
    private Context mContext;
    private List<HelpBean.DataBean> lists;

    public NoticeAdapter(Context mContext, List<HelpBean.DataBean> lists) {
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
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_notice, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tvTime.setText(lists.get(i).getAddtime());
        holder.tvContent.setText(lists.get(i).getIntroduce());
        holder.tvTitle.setText(lists.get(i).getTitle());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
