package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.FootPrintBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class FootPrintListAdapter extends BaseAdapter {

    private Context mContext;
    List<FootPrintBean.DataBean.GoodsListBean> lists;
    private int index;

    public FootPrintListAdapter(Context mContext, List<FootPrintBean.DataBean.GoodsListBean> lists, int index) {
        this.mContext = mContext;
        this.lists = lists;
        this.index = index;
    }

    public void addIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_footprintlist, null);
            holder = new ViewHolder(view);




            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Glide
                .with(mContext)
                .load(Constants.Base_URL + lists.get(i).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(holder.imgGoods);
        holder.tvGoodsName.setText(lists.get(i).getGoods_name());
        holder.tvGoodsPrice.setText("¥" + lists.get(i).getPack_price() + "—" + "¥" + lists.get(i).getShop_price());
        holder. mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                intent.putExtra("goods_id",lists.get(i).getGoods_id());
                mContext.startActivity(intent);
            }
        });
        if (index == 0) {
            holder.rlChoos.setVisibility(View.GONE);
//            holder.cbCheck.setChecked(lists.get(i).getInchenk());
        } else if (index == 1) {
            holder.rlChoos.setVisibility(View.VISIBLE);
            holder.cbCheck.setChecked(lists.get(i).getInchenk());
        }
        holder.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                lists.get(i).setInchenk(b);
            }
        });
        holder. mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                intent.putExtra("goods_id",lists.get(i).getGoods_id());
                mContext.startActivity(intent);
            }
        });



        return view;
    }

    public class ViewHolder {
        @Bind(R.id.img_goods)
        ImageView imgGoods;
        @Bind(R.id.cb_check)
        CheckBox cbCheck;
        @Bind(R.id.rl_choos)
        RelativeLayout rlChoos;
        @Bind(R.id.rl_goods)
        RelativeLayout rlGoods;
        @Bind(R.id.tv_goods_name)
        TextView tvGoodsName;
        @Bind(R.id.tv_goods_price)
        TextView tvGoodsPrice;
        @Bind(R.id.mCardView)
        CardView mCardView;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
//
//    public String getChaechData() {
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < lists.size(); i++) {
//            if (i != 0) {
//                sb.append(",");
//            }
//            sb.append(lists.get(i).getFav_id());
//        }
//
//        return sb.toString();
//    }

}
