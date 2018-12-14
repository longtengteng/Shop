package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.AddriGoodsBean;

import java.util.List;


/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class SeachAddriAdapter extends BaseAdapter {
    private Context mContext ;
    private   List<AddriGoodsBean.DataBean> classlists;

    public SeachAddriAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void addData(   List<AddriGoodsBean.DataBean> classlists){
        this.classlists=classlists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return classlists==null?0:classlists.size();
    }

    @Override
    public Object getItem(int i) {
        return classlists.get(i);
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
        mImageView.setVisibility(View.GONE);
        mTextView.setText(classlists.get(i).getAttr_name());
        if (i == checkedPosition) {
            mTextView.setEnabled(true);
            mTextView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            mTextView.setBackgroundColor(Color.parseColor("#F0F0F0"));
            mTextView.setEnabled(false);
        }
        return view;
    }
}
