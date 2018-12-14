package com.lnkj.privateshop.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
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
import com.lnkj.privateshop.entity.OrderDetailderBean;
import com.lnkj.privateshop.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class OrderDetaildeAdapter extends BaseAdapter {
    private Context mContext;
    private Boolean isgone = false;
    public List<OrderDetailderBean.DataBean.OrderGoodsBean> lists;

    public OrderDetaildeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addData(List<OrderDetailderBean.DataBean.OrderGoodsBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public void setGone(Boolean isgone) {
        this.isgone = isgone;
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
        holder.tvGoodsPrice.setText("¥" + lists.get(i).getPrice());
        Glide
                .with(mContext)
                .load(Constants.Base_URL + lists.get(i).getGoods_img())
                .error(R.mipmap.bg_img)
                .into(holder.imgGoods);
        holder.tv_refunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null) {
                    mClickListener.onDeleteCilck(i);
                }
            }
        });


        if (isgone) {
            holder.tv_refunds.setVisibility(View.VISIBLE);
            try {
                if (lists.get(i).getAllow_return().equals("1")) {
                    if (!TextUtils.isEmpty(lists.get(i).getOrder_return_status())) {
                        if (lists.get(i).getOrder_return_status().equals("0")) {
                            holder.tv_refunds.setClickable(true);
                            holder.tv_refunds.setText("申请退款");
                            holder.tv_refunds.setBackgroundResource(R.drawable.button_default);
                        } else if (lists.get(i).getOrder_return_status().equals("1")) {
                            holder.tv_refunds.setText("退款退货中");
                            holder.tv_refunds.setBackgroundResource(R.drawable.button_default_gray);
                            holder.tv_refunds.setClickable(false);
                        } else if (lists.get(i).getOrder_return_status().equals("3")) {
                            holder.tv_refunds.setBackgroundResource(R.drawable.button_default);
                            holder.tv_refunds.setText("再次申请");
                            holder.tv_refunds.setClickable(true);
                        } else if (lists.get(i).getOrder_return_status().equals("4")) {
                            holder.tv_refunds.setText("退款完成");
                            holder.tv_refunds.setClickable(false);
                            holder.tv_refunds.setBackgroundResource(R.drawable.button_default_gray);
                        } else if (lists.get(i).getOrder_return_status().equals("10")) {
                            holder.tv_refunds.setText("退款中");
                            holder.tv_refunds.setClickable(false);
                            holder.tv_refunds.setBackgroundResource(R.drawable.button_default_gray);
                        } else {
    //                holder.tv_refunds.setText("隐藏");
                            holder.tv_refunds.setBackgroundResource(R.drawable.button_default_gray);
                            holder.tv_refunds.setClickable(false);
                            holder.tv_refunds.setVisibility(View.GONE);
                        }
                    } else {
                        holder.tv_refunds.setVisibility(View.GONE);
                    }

                } else {
                    holder.tv_refunds.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            final List<String> strList = new ArrayList<>();
            List<OrderDetailderBean.DataBean.Spec_list> Spec_list = lists.get(i).getSpec_list();
            if (Spec_list == null || Spec_list.size() == 0) {
                holder.mGridView.setVisibility(View.INVISIBLE);
            } else {
                holder.mGridView.setVisibility(View.VISIBLE);
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
