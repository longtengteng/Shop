package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsSpecBean;
import com.lnkj.privateshop.view.NumberButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class AddShopPingCarAdapter extends BaseAdapter {
    Context mContext;
    List<GoodsSpecBean.DataBean> lists;
    public AddShopPingCarAdapter(Context mContext,List<GoodsSpecBean.DataBean> lists) {
        this.mContext = mContext;
        this.lists=lists;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
//        if (view==null){
            view = View.inflate(mContext, R.layout.list_item_addshopping, null);
            holder=new ViewHolder(view);
//            view.setTag(holder);
//        }else {
//            holder= (ViewHolder) view.getTag();
//        }
        String max = lists.get(i).getStore_count();
        holder.numberButton.setBuyMax(Integer.parseInt(max))//最大购买数量
                .setInventory(Integer.parseInt(max)) //当前库存
                .setCurrentNumber(lists.get(i).getHas_number())
                .setOnWarnListener(new NumberButton.OnWarnListener() {
                    @Override
                    public void onWarningForInventory(int inventory) {
                        Toast.makeText(mContext, "当前库存:" + inventory, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onWarningForBuyMax(int buyMax) {

                        Toast.makeText(mContext, "超过最大购买数:" + buyMax, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNumber(int num) {
                        lists.get(i).setHas_number(num);

                        if (mNumberClickListener!=null){
                            mNumberClickListener.onNumberCilck(num,i);
                        }

                    }
                });
        holder.tvColor.setText(lists.get(i).getSpec_name());
        holder.tvStock.setText(lists.get(i).getStore_count());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_color)
        TextView tvColor;
        @Bind(R.id.tv_stock)
        TextView tvStock;
        @Bind(R.id.number_button)
        NumberButton numberButton;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public NumberClickListener mNumberClickListener;
    public void setNumberClickListener( NumberClickListener mNumberClickListener) {
        this.mNumberClickListener = mNumberClickListener;
    }

    public interface NumberClickListener {
        void onNumberCilck(int number,int position );
    }
}
