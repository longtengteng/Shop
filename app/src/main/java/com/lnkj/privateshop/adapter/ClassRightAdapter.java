package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.AddGoodsBean;

import java.util.List;


/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class ClassRightAdapter extends BaseAdapter {
    private Context mContext;
    private List<AddGoodsBean.DataBean.ChildBean> classlists;

    public ClassRightAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addData(List<AddGoodsBean.DataBean.ChildBean> classlists) {
        this.classlists = classlists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return classlists == null ? 0 : classlists.size();
    }

    @Override
    public Object getItem(int i) {
        return classlists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private int checkedPosition=-1;

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        System.out.println("数据"+i);
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_class, null);
        }
        TextView mTextView = (TextView) view.findViewById(R.id.mTextView);
        ImageView mImageView = (ImageView) view.findViewById(R.id.mImageView);
        mTextView.setText(classlists.get(i).getCat_name_mobile());
        mImageView.setVisibility(View.GONE);
        if (i == checkedPosition) {
            mTextView.setEnabled(true);
            mTextView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            mTextView.setBackgroundColor(Color.parseColor("#ffffff"));
            mTextView.setEnabled(false);
        }
        return view;
    }
}
