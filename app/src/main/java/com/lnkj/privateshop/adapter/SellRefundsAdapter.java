package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.SellReutrnBean;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.MyListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lnkj.privateshop.R.id.tv_goods_count_bottom;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class SellRefundsAdapter extends RecyclerView.Adapter<SellRefundsAdapter.ViewHolder> {


    private Context mContext;
    private int index;
    List<SellReutrnBean.DataBean> orderlist;

    public SellRefundsAdapter(Context mContext, int intex) {
        this.mContext = mContext;
        this.index = index;

    }

    public void addAllData(List<SellReutrnBean.DataBean> orderlist) {
        this.orderlist = orderlist;

        notifyDataSetChanged();
    }

    public void clearData() {
        this.orderlist.clear();
        notifyDataSetChanged();
    }

    @Override
    public SellRefundsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_orderall, parent, false);
        return new ViewHolder(v);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(SellRefundsAdapter.ViewHolder holder, final int position) {
        final SellReutrnBean.DataBean bean = orderlist.get(position);
        holder.itemView.setTag(position);
        holder.tvShopsName.setText(bean.getShop_name());
        String refund_type = bean.getRefund_type();
        final String roder_status = bean.getShop_state();

        if (roder_status.equals("1")) {
            try {
                if (bean.getRefund_type().equals("1")) {
                    holder.tvStatus.setText("申请退款");
                } else {
                    holder.tvStatus.setText("申请退货退款");
                }

            } catch (Exception e) {
            }
            holder.tvStatus.setTextColor(Color.parseColor("#999999"));
            holder.tvBtnLeft.setVisibility(View.VISIBLE);
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setText(bean.getRefund_type().equals("1") ? "拒绝退款" : "拒绝退货退款");
            holder.tvBtnDelete.setText(bean.getRefund_type().equals("1") ? "同意退款" : "同意退货退款");
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj_orange);
            holder.tvBtnDelete.setTextColor(Color.parseColor("#ff7722"));
        } else if (roder_status.equals("10")) {
            holder.tvStatus.setText("等待收货");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("确认收货并退款");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj_orange);
        } else if (roder_status.equals("2")) {
            holder.tvStatus.setText("等待买家发货");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("提醒退货");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj_orange);
        } else if (roder_status.equals("3")) {
            holder.tvStatus.setText(bean.getRefund_type().equals("1") ? "拒绝退款" : "拒绝退货退款");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("删除订单");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#999999"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj);
        } else if (roder_status.equals("4")) {
            holder.tvStatus.setText(bean.getRefund_type().equals("1") ? "仅退款 退款成功" : "退货退款 退款成功");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("删除订单");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#999999"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj);
        }
        String str = "共<font color='#ff7722'>" + bean.getGoods_count() + "</font>件";
        holder.tvGoodsCountBottom.setText(Html.fromHtml(str));

        holder.tvYunfei.setText("(含运费" + bean.getExpress_amount() + "元)");
        holder.tvGoodsPriceCombined.setText("¥" + bean.getReal_pay_amount());

        holder.tvBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roder_status.equals("1")) {
                    if (bean.getRefund_type().equals("1")) {
                        if (mOrderClickListener != null) {
                            mOrderClickListener.onOkRoNoGoods(position, "1");
                        }

                    } else {
                        if (mOrderClickListener != null) {
                            mOrderClickListener.onOkRoNoGoods(position, "2");
                        }
                    }

                } else if (roder_status.equals("10")) {
                    ToastUtil.showToast("确认收货");
                    if (mOrderClickListener != null) {
                        mOrderClickListener.onReceiveGoods(position);
                    }
                } else if (roder_status.equals("2")) {
//                    ToastUtil.showToast("催促买家");
                    if (mOrderClickListener != null) {
                        mOrderClickListener.onUrged(bean.getRefund_id());
                    }
                } else if (roder_status.equals("3")) {
//                    ToastUtil.showToast("删除订单");
                    if (mOrderClickListener != null) {
                        mOrderClickListener.onDeleteOrder(position);
                    }

                } else if (roder_status.equals("4")) {
//                    ToastUtil.showToast("删除订单");
                    if (mOrderClickListener != null) {
                        mOrderClickListener.onDeleteOrder(position);
                    }

                }
            }
        });
        holder.tvBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roder_status.equals("1")) {
//                 ToastUtil.showToast("确认退款");
                    if (bean.getRefund_type().equals("1")) {
//                        holder.tvStatus.setText("买家申请退款");
                        if (mOrderClickListener != null) {
                            mOrderClickListener.onOkRoNoGoods(position, "3");
                        }

                    } else {
//                        holder.tvStatus.setText("买家申请退货退款");
                        if (mOrderClickListener != null) {
                            mOrderClickListener.onOkRoNoGoods(position, "4");
                        }
                    }
                }
            }
        });

        RefundsrListAdapter adapter = new RefundsrListAdapter(mContext, bean.getGoods_list());
        holder.myListView.setAdapter(adapter);
        holder.myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mOrderClickListener != null) {
                    mOrderClickListener.onItemClick(bean.getOrder_sn(), bean.getGoods_list().get(i).getOrder_goods_id(),
                            roder_status, bean.getRefund_type(), position);

                }
            }
        });
//        holder.llItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mOrderClickListener!=null){
//                    mOrderClickListener.onItemClick(bean.getOrder_sn(),bean.getGoods_list().get(0).getOrder_goods_id(),roder_status,bean.getGoods_list().get(0).getRefund_type());
//
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return orderlist == null ? 0 : orderlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_shops_name)
        TextView tvShopsName;
        @Bind(R.id.tv_status)
        TextView tvStatus;
        @Bind(tv_goods_count_bottom)
        TextView tvGoodsCountBottom;
        @Bind(R.id.tv_yunfei)
        TextView tvYunfei;
        @Bind(R.id.tv_goods_price_combined)
        TextView tvGoodsPriceCombined;
        @Bind(R.id.tv_btn_left)
        TextView tvBtnLeft;
        @Bind(R.id.tv_btn_delete)
        TextView tvBtnDelete;
        @Bind(R.id.tv_btn_left_tow)
        TextView tvBtnLeftTow;
        @Bind(R.id.meListView)
        MyListView myListView;
        @Bind(R.id.ll_item)
        LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


    public OrderClickListener mOrderClickListener;

    public void setmOrderClickListener(OrderClickListener mOrderClickListener) {
        this.mOrderClickListener = mOrderClickListener;
    }

    public interface OrderClickListener {
        void onReceiveGoods(int p); //确认收货

        void onUrged(String order_sn); //催促买家

        void onDeleteOrder(int p);// 删除订单

        void onOkRoNoGoods(int p, String refund_type); //同意、拒绝退款

        void onItemClick(String order_sn, String order_goods_id, String roder_status, String Refund_type, int position);
    }

}
