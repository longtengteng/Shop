package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ShopClassBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class ShopClassAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopClassBean.DataBean> lists;

    public ShopClassAdapter(Context mContext, List<ShopClassBean.DataBean> lists) {
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
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_shop_class, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
           holder= (ViewHolder) view.getTag();
        }

        holder.mTextView.setText(lists.get(i).getCat_name());
        Glide
                .with(mContext)
                .load(Constants.Base_URL + lists.get(i).getImage())
                .error(R.mipmap.bg_img)
                .into(holder.ivMImageView);
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.iv_mImageView)
        ImageView ivMImageView;
        @Bind(R.id.mTextView)
        TextView mTextView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
