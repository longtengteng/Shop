package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class GoodsInfoGalleryAdapter extends BaseAdapter {
    private Context mContext;
    private int width;
    private List<GoodsBean.DataBean.GoodsDescBean> godsLists;

    public GoodsInfoGalleryAdapter(Context mContext, List<GoodsBean.DataBean.GoodsDescBean> godsLists, int width) {
        this.mContext = mContext;
        this.godsLists = godsLists;
        this.width = width;
    }

    @Override
    public int getCount() {
        return godsLists == null ? 0 : godsLists.size();
    }

    @Override
    public Object getItem(int i) {
        return godsLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_gods_gall, null);
            holder = new ViewHolder(view);
           view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }


        int screenWidth = width;

        ViewGroup.LayoutParams lp = holder.mImageView.getLayoutParams();
        lp.width = screenWidth;
        lp.height = (int)screenWidth*4/3;

        holder.mImageView.setLayoutParams(lp);

//        holder.mImageView.setMaxWidth(screenWidth);
//        holder.mImageView.setMaxHeight((int) (screenWidth * 5));// 这里其实可以根据需求而定，我这里测试为最大宽度的1.5倍


        Glide.with(mContext).load(Constants.Base_URL + godsLists.get(i).getImage_path())
                .error(R.mipmap.bg_img)
                .placeholder(R.mipmap.bg_img)
                .into(holder.mImageView);
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.mImageView)
        ImageView mImageView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
