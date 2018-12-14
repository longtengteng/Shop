package com.lnkj.privateshop.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.SellOrderDetaildeBean;
import com.lnkj.privateshop.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class SellOrderDetaildeAdapter extends BaseAdapter {
    private Context mContext;
    public List<SellOrderDetaildeBean.DataBean.OrderGoodsBean> lists;

    public SellOrderDetaildeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addData(List<SellOrderDetaildeBean.DataBean.OrderGoodsBean> lists) {
        this.lists = lists;
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
        viewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_orderlist_two, null);
            holder = new viewHolder(view);

            view.setTag(holder);
        } else {
            holder = (viewHolder) view.getTag();
        }
        holder.tvGoodsName.setText(lists.get(i).getGoods_name());
        holder.tvGoodsCount.setText(lists.get(i).getTotal_buy_number() + "件");
        holder.tvGoodsPrice.setText("￥" + lists.get(i).getPrice());
        Glide
                .with(mContext)
                .load(Constants.Base_URL + lists.get(i).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(holder.imgGoods);
        holder.tv_refunds.setVisibility(View.GONE);
        holder.tv_refunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null) {
                    mClickListener.onDeleteCilck(i);
                }
            }
        });
        try {
            if (lists.get(i).getRefund_state() == 1) {
                holder.tv_goods_return.setVisibility(View.VISIBLE);
                holder.tv_goods_return.setText("退款中");
            } else if (lists.get(i).getRefund_state() == 2) {
                holder.tv_goods_return.setText("退货退款中");
                holder.tv_goods_return.setVisibility(View.VISIBLE);
            } else if (lists.get(i).getRefund_state() == 3) {
                holder.tv_goods_return.setText("已退款");
                holder.tv_goods_return.setVisibility(View.VISIBLE);
            } else {
                holder.tv_goods_return.setVisibility(View.GONE);
            }

            List<SellOrderDetaildeBean.DataBean.Spec_list> Spec_list = lists.get(i).getSpec_list();
            if (Spec_list == null || Spec_list.size() == 0) {
                holder.mGridView.setVisibility(View.INVISIBLE);
            } else {
                holder.mGridView.setVisibility(View.VISIBLE);
                final List<String> strList = new ArrayList<>();
                for (int j = 0; j < Spec_list.size(); j++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(Spec_list.get(j).getSpec() + "*" + Spec_list.get(j).getBuy_number());

                    if (j != Spec_list.size() - 1) {
                        sb.append(",");
                    }
                    strList.add(sb.toString());
                }
                GoodsListGridAdapter adapter = new GoodsListGridAdapter(mContext, strList);
                holder.mGridView.setAdapter(adapter);

                holder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ClipboardManager cm2 = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                        StringBuffer sb = new StringBuffer();
                        for (int j = 0; j < strList.size(); j++) {
                            sb.append(strList.get(j));
                            sb.append(" ");
                        }
                        // 将文本内容放到系统剪贴板里。
                        cm2.setText(sb.toString());
                        Toast.makeText(mContext, "复制成功", Toast.LENGTH_LONG).show();
                    }
                });
            }

        } catch (Exception e) {
        }

        return view;
    }

    class viewHolder {
        @Bind(R.id.img_goods)
        ImageView imgGoods;
        @Bind(R.id.tv_goods_name)
        TextView tvGoodsName;
        @Bind(R.id.tv_goods_price)
        TextView tvGoodsPrice;
        @Bind(R.id.tv_goods_count)
        TextView tvGoodsCount;
        @Bind(R.id.tv_refunds)
        TextView tv_refunds;
        @Bind(R.id.tv_goods_return)
        TextView tv_goods_return;
        @Bind(R.id.mGridView)
        MyGridView mGridView;

        public viewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public AddClickListener mClickListener;

    public void setAddressClickListener(AddClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public interface AddClickListener {
        void onDeleteCilck(int position); //删除订单

    }
}
