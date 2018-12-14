package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.DynamicragBean;
import com.lnkj.privateshop.utils.UiUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26 0026.
 */

public class DynamlistAdapter extends BaseAdapter {
    private List<DynamicragBean.DataBean.GoodsListBean> lists;
    private Context mContext;
    private int  w;
    public DynamlistAdapter(List<DynamicragBean.DataBean.GoodsListBean> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
        w = UiUtils.getScreenWidth(mContext);
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
        view = View.inflate(mContext, R.layout.list_item_dynamlist,null);
        RelativeLayout.LayoutParams  params = new RelativeLayout.LayoutParams(w/3-6,w/3-6);
        ImageView mImageView = (ImageView) view.findViewById(R.id.mImageView);
        mImageView.setLayoutParams(params);
        TextView mTextView = (TextView) view.findViewById(R.id.mTextView);
        Glide
                .with(mContext)
                .load(Constants.Base_URL +lists.get(i).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(mImageView);
        mTextView.setText("¥"+lists.get(i).getShop_price()+"－¥"+lists.get(i).getPack_price());
        return view;
    }
}
