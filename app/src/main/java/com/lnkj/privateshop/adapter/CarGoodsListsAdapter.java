package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.OrderConBean;
import com.lnkj.privateshop.view.MyListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class CarGoodsListsAdapter extends BaseAdapter {
    private Context mContext;
    private List<OrderConBean.DataBean.ListBean> goodslist;

    public CarGoodsListsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addData(List<OrderConBean.DataBean.ListBean> goodslist) {
        this.goodslist = goodslist;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return goodslist == null ? 0 : goodslist.size();
    }

    @Override
    public Object getItem(int i) {
        return goodslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_goods_car, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvShopName.setText(goodslist.get(i).getShop_name());
        holder.tv_price_freight.setText("¥" +goodslist.get(i).getShop_express_price());
        holder.tv_speak.setText(goodslist.get(i).getShop_name());
        final List<OrderConBean.DataBean.ListBean.GoodsListBean> goodslists = goodslist.get(i).getGoods_list();
        final CarGoodslistAdaptre adapter = new CarGoodslistAdaptre(mContext, goodslists);
        holder.mListView.setAdapter(adapter);
         holder.llChb.setVisibility(View.GONE);
        holder.ll_1.setVisibility(View.VISIBLE);
        holder.tv_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAddressClickListener!=null){
                    mAddressClickListener.onEditCilck(i);
                }

            }
        });
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.cb_check_shop)
        CheckBox cbCheckShop;
        @Bind(R.id.ll_chb)
        LinearLayout llChb;
        @Bind(R.id.tv_shop_name)
        TextView tvShopName;
        @Bind(R.id.mListView)
        MyListView mListView;
        @Bind(R.id.tv_price_freight)
                TextView tv_price_freight;
        @Bind(R.id.tv_speak)
                TextView tv_speak;
    @Bind(R.id.ll_1)
            LinearLayout ll_1;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public AddressClickListener mAddressClickListener;
    public void setAddressClickListener( AddressClickListener mAddressClickListener) {
        this.mAddressClickListener = mAddressClickListener;
    }

    public interface AddressClickListener {


        void onEditCilck(int position); //查看物流


    }
}
