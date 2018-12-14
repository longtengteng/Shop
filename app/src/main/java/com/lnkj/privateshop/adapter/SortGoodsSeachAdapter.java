package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.R;


/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class SortGoodsSeachAdapter extends BaseAdapter {
    private Context mContext ;
    private  String[] sort;

    public SortGoodsSeachAdapter(Context mContext,String[] sort) {
        this.mContext = mContext;
        this.sort = sort;
    }


    @Override
    public int getCount() {
        return sort==null?0:sort.length;
    }

    @Override
    public Object getItem(int i) {
        return sort[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private int checkedPosition;
    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        System.out.println("数据"+i);
        if (view==null){
            view=View.inflate(mContext, R.layout.list_item_class,null);
        }
        TextView mTextView = (TextView) view.findViewById(R.id.mTextView);
        ImageView mImageView = (ImageView) view.findViewById(R.id.mImageView);
        mTextView.setText(sort[i]);
        if (i == checkedPosition) {
            mTextView.setEnabled(true);
            mImageView.setVisibility(View.VISIBLE);
//            mTextView.setBackgroundColor(Color.parseColor("#FF7722"));
        } else {
//            mTextView.setBackgroundColor(Color.parseColor("#ffffff"));
            mTextView.setEnabled(false);
            mImageView.setVisibility(View.GONE);
        }
        return view;
    }
}
