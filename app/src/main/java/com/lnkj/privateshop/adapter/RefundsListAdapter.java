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
import com.lnkj.privateshop.entity.ReutrnBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/5 0005.
 */

public class RefundsListAdapter extends BaseAdapter {
    private Context mContext;
    List<ReutrnBean.DataBean.GoodsListBean> beans;
    public RefundsListAdapter(Context mContext, List<ReutrnBean.DataBean.GoodsListBean> beans) {
        this.mContext = mContext;
        this.beans=beans;
    }

    @Override
    public int getCount() {
        return beans==null?0:beans.size();
    }

    @Override
    public ReutrnBean.DataBean.GoodsListBean getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder  holder =null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_orderlist, null);
            holder=new viewHolder(view);

            view.setTag(holder);
        }else {
            holder= (viewHolder) view.getTag();
        }
        holder.tvGoodsName.setText(beans.get(i).getGoods_name());
        holder.tvGoodsCount.setText(beans.get(i).getBuy_number()+"件");
        holder.tvGoodsPrice.setText("￥"+beans.get(i).getPrice());
        Glide
                .with(mContext)
                .load(Constants.Base_URL + beans.get(i).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(holder.imgGoods);
        return view;
    }
    class viewHolder{
          @Bind(R.id.img_goods)
          ImageView imgGoods;
        @Bind(R.id.tv_goods_name)
        TextView tvGoodsName;
        @Bind(R.id.tv_goods_price)
        TextView tvGoodsPrice;
        @Bind(R.id.tv_goods_count)
        TextView tvGoodsCount;

        public viewHolder(View view ) {
            ButterKnife.bind(this,view);
        }
    }
}
