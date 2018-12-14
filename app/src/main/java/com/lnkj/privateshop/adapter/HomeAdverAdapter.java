package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.AdvertisingBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class HomeAdverAdapter extends BaseAdapter {

    private Context mContext;
    private List<AdvertisingBean.DataBean> lists;

    public HomeAdverAdapter(Context mContext, List<AdvertisingBean.DataBean> lists) {
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
            view=View.inflate(mContext, R.layout.list_item_home_adver,null);
        }
        ImageView mImageView = (ImageView) view.findViewById(R.id.mImageView);
//        mImageView.requestLayout();
//        mImageView.setScaleType( ImageView.ScaleType.FIT_XY);
//        Glide.with(mContext).load(Constants.Base_URL +lists.get(i).getContent())
//                .placeholder(R.mipmap.bg_img)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(mImageView);
//                .error(R.mipmap.bg_img)
        Picasso.with(mContext).load(Constants.Base_URL +lists.get(i).getContent()).into(mImageView);
        return view;
    }
}
