package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lnkj.privateshop.R;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26 0026.
 */

public class ReturnListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> lists;

    public ReturnListAdapter(Context mContext, List<String> lists) {
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
        view = View.inflate(mContext, R.layout.list_item_return,null);
        TextView mTextView = (TextView) view.findViewById(R.id.mTextView);
        mTextView.setText(lists.get(i));
        return view;
    }
}
