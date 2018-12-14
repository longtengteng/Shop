package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsCraListBean;
import com.lnkj.privateshop.view.MyListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class GoodsListCarAdapter extends RecyclerView.Adapter<GoodsListCarAdapter.ViewHolder> {


    private Context mContext;
    private List<GoodsCraListBean.DataBean> beas;
    private int index;

    public GoodsListCarAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAllData(List<GoodsCraListBean.DataBean> beas) {
        this.beas = beas;
//        this.index = index;

        notifyDataSetChanged();
    }

    public void addIndex(int index) {
        this.index = index;
        if (index == 1) {
            for (int j = 0; j < getItemCount(); j++) {
                beas.get(j).setIscheck(true);
            }
        } else {
            for (int j = 0; j < getItemCount(); j++) {
                beas.get(j).setIscheck(false);
            }
        }
        notifyDataSetChanged();
    }

    public void clearData() {

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(mContext, R.layout.list_item_goods_car, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.tvShopName.setText(beas.get(position).getShop_name());
        final List<GoodsCraListBean.DataBean.GoodsBean> goodslist = beas.get(position).getGoods();
        final GoodslistAdaptre adapter = new GoodslistAdaptre(mContext, goodslist);
        holder.mListView.setAdapter(adapter);

        adapter.setCheckListener(new GoodslistAdaptre.onCheckListener() {
            @Override
            public void check() {
                onCheck.check();
            }
        });

        holder.cbCheckShop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onCheck.check();
            }
        });

        if (beas.get(position).getIscheck()) {
            holder.cbCheckShop.setChecked(true);
            adapter.addIntex(1);
        } else {
            holder.cbCheckShop.setChecked(false);
            adapter.addIntex(0);
        }

        holder.ll_chb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (beas.get(position).getIscheck()) {
                    beas.get(position).setIscheck(false);
                    adapter.addIntex(0);
                } else {
                    beas.get(position).setIscheck(true);
                    adapter.addIntex(1);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return beas == null ? 0 : beas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cb_check_shop)
        CheckBox cbCheckShop;
        @Bind(R.id.tv_shop_name)
        TextView tvShopName;
        @Bind(R.id.mListView)
        MyListView mListView;
        @Bind(R.id.ll_chb)
        LinearLayout ll_chb;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public onCheckListener onCheck;
    public void setCheckListener(onCheckListener onCheck){
        this.onCheck=onCheck;
    }
    public interface onCheckListener{
        void check();
    }

}
