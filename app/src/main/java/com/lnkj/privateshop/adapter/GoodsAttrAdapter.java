package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsAttrBean;

import java.util.List;

/**
 * 作者：赵林 on 2017/10/31 0031.
 */

public class GoodsAttrAdapter extends BaseAdapter {
    private Context mContext ;
    private List<GoodsAttrBean> lists;

    public GoodsAttrAdapter(Context mContext, List<GoodsAttrBean> lists) {
        this.mContext = mContext;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists==null?0:lists.size();
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
        if (view==null){
            view=View.inflate(mContext, R.layout.list_item_good_attr,null);
        }
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_value = (TextView) view.findViewById(R.id.tv_value);
        tv_name.setText(lists.get(i).getName());
        tv_value.setText(lists.get(i).getValue());
        return view;
    }
}
