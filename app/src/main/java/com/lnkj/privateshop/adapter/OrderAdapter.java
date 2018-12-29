package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.OrderAllBean;
import com.lnkj.privateshop.view.MyListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lnkj.privateshop.R.id.tv_goods_count_bottom;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {


    private Context mContext;
    private int index;
    List<OrderAllBean.DataBean.OrderListBean> orderlist;

    public OrderAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addAllData(List<OrderAllBean.DataBean.OrderListBean> orderlist, int index) {
        this.orderlist = orderlist;
        this.index = index;
        notifyDataSetChanged();
    }

    public void clearData() {
        this.orderlist.clear();
        notifyDataSetChanged();
    }

    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_orderall, parent, false);
        return new ViewHolder(v);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, final int position) {
        final OrderAllBean.DataBean.OrderListBean bean = orderlist.get(position);
        holder.itemView.setTag(position);
        holder.tvShopsName.setText(bean.getShop_name());

        final String roder_status = bean.getOrder_status();
        if (roder_status.equals("0")) {
            holder.tvStatus.setText("交易取消");
            holder.tvStatus.setTextColor(Color.parseColor("#999999"));
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnDelete.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("删除订单");
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj);
            holder.tvBtnDelete.setTextColor(Color.parseColor("#999999"));
        } else if (roder_status.equals("1")) {
            holder.tvStatus.setText("等待付款");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeft.setVisibility(View.VISIBLE);
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setText("取消订单");
            holder.tvBtnDelete.setText("前往付款");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj_orange);
        } else if (roder_status.equals("2")) {
            holder.tvStatus.setText("等待发货");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("提醒发货");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj_orange);
        } else if (roder_status.equals("3")) {
            holder.tvStatus.setText("等待收货");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setVisibility(View.VISIBLE);
            holder.tvBtnDelete.setText("确认收货");
            holder.tvBtnLeft.setText("查看物流");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj_orange);
        } else if (roder_status.equals("4")) {
            holder.tvStatus.setText("交易完成");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeftTow.setVisibility(View.VISIBLE);
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnLeft.setText("再次购买");
            holder.tvBtnDelete.setText("前往评价");
            holder.tvBtnLeftTow.setText("查看物流");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj_orange);
            if (bean.getIs_evaluation().equals("0")) {
                holder.tvBtnDelete.setVisibility(View.VISIBLE);
            } else {
                holder.tvBtnDelete.setVisibility(View.GONE);
            }
        }
        String str = "共 <font color='#FF7722'>" + bean.getTotal_buy_number() + "</font> 件";
        holder.tvGoodsCountBottom.setText((Html.fromHtml(str)));
        if (TextUtils.isEmpty(bean.getExpress_amount()) || bean.getExpress_amount().equals("0.00")) {
            holder.tvYunfei.setText("免运费");
        } else {
            holder.tvYunfei.setText("含运费" + bean.getExpress_amount() + "元");
        }
        holder.tvGoodsPriceCombined.setText("¥" + bean.getTotal_amount());
        holder.tvBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roder_status.equals("0")) {
//                    ToastUtil.showToast("删除订单");
                    mOrderClickListener.onDeleteOrderCilck(orderlist, position);
                } else if (roder_status.equals("1")) {
//                    ToastUtil.showToast("前往付款");
                    mOrderClickListener.onGoPayCilck(orderlist, position);
                } else if (roder_status.equals("2")) {
//                    ToastUtil.showToast("提醒发货");
                    mOrderClickListener.onRemindDeliveryCilck(orderlist, position);
                } else if (roder_status.equals("3")) {
//                    ToastUtil.showToast("确认收货");
                    mOrderClickListener.onOkGoodsCilck(orderlist, position);
                } else if (roder_status.equals("4")) {
//                    ToastUtil.showToast("前往评价");
                    mOrderClickListener.onGoEvaluationCilck(orderlist, position);

                }
            }
        });
        holder.tvBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roder_status.equals("1")) {
//                    ToastUtil.showToast("删除订单");
                    mOrderClickListener.onCancelorder(orderlist, position);
                } else if (roder_status.equals("3")) {
//                    ToastUtil.showToast("查看物流");
                    mOrderClickListener.onLookLogisticsCilck(orderlist, position);
                } else if (roder_status.equals("4")) {
//                    ToastUtil.showToast("查看物流");
                    mOrderClickListener.onLookLogisticsCilck(orderlist, position);
//                 mOrderClickListener.onAgainCilck(orderlist,position);

                }
            }
        });
        holder.tvBtnLeftTow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roder_status.equals("4")) {
//                    ToastUtil.showToast("删除订单");
//                    mOrderClickListener.onDeleteOrderCilck(orderlist,position);
                    mOrderClickListener.onLookLogisticsCilck(orderlist, position);
                }

            }
        });
        OrderListAdapter adapter = new OrderListAdapter(mContext, bean.getGoods_info());
        holder.myListView.setAdapter(adapter);
        holder.myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mOrderClickListener.onItemClick(orderlist, position);
            }
        });
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrderClickListener.onItemClick(orderlist, position);
            }
        });

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

    public void setmIDeleteBtnClickListener(OrderClickListener mOrderClickListener) {
        this.mOrderClickListener = mOrderClickListener;
    }

    public interface OrderClickListener {
        void onDeleteOrderCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); //删除订单

        void onLookLogisticsCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); //查看物流

        void onRemindDeliveryCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); // 提醒发货

        void onGoPayCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); //去支付

        void onOkGoodsCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); //确认收货

        void onGoEvaluationCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position);// 前往评价

        void onAgainCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position);// 再次购买

        void onCancelorder(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); //取消订单

        void onItemClick(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position);
    }

}
