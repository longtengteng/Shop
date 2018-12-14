package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class RetrunImageAdapter extends BaseAdapter {
    List<String> images ;
    Context mContext;

    public RetrunImageAdapter(List<String> images, Context mContext) {
        this.images = images;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return images==null?0:images.size();
    }

    @Override
    public Object getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.list_item_retrun_images,null);
        ImageView imageview = (ImageView) v.findViewById(R.id.imageview);
        Glide
                .with(mContext)
                .load(Constants.Base_URL +images.get(i))
                .error(R.mipmap.bg_img)
                .into(imageview);
        return v;
    }
}
