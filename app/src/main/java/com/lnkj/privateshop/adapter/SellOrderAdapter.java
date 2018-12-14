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
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.MyListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.tv_goods_count_bottom;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class SellOrderAdapter extends RecyclerView.Adapter<SellOrderAdapter.ViewHolder> {



    private Context mContext;
    private int index;
    List<OrderAllBean.DataBean.OrderListBean> orderlist;

    public SellOrderAdapter(Context mContext) {
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
    public SellOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_orderall, parent, false);
        return new ViewHolder(v);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(SellOrderAdapter.ViewHolder holder, final int position) {
        final OrderAllBean.DataBean.OrderListBean bean =   orderlist.get(position);
        holder.itemView.setTag(position);
        holder.tvShopsName.setText(bean.getShop_name());
        holder.tv_s.setVisibility(View.VISIBLE);
        holder.tv_time.setVisibility(View.VISIBLE);
        holder.tv_time.setText(bean.getAdd_time());
      final String roder_status =   bean.getOrder_status();
        if (roder_status.equals("0")){
        holder.tvStatus.setText("交易取消");
            holder.tvStatus.setTextColor(Color.parseColor("#999999"));
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnDelete.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("删除订单");
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj);
            holder.tvBtnDelete.setTextColor(Color.parseColor("#383838"));
        }else if (roder_status.equals("1")){
            holder.tvStatus.setText("等待付款");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeft.setVisibility(View.VISIBLE);
            holder.tvBtnLeftTow.setVisibility(View.VISIBLE);
            holder.tvBtnLeft.setText("取消订单");
            holder.tvBtnDelete.setText("催促买家");
            holder.tvBtnLeftTow.setText("修改价格");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj_orange);
            holder.tvBtnLeftTow.setTextColor(Color.parseColor("#383838"));
            holder.tvBtnLeftTow.setBackgroundResource(R.drawable.button_bj);
        }else if (roder_status.equals("2")){
            holder.tvStatus.setText("等待发货");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("前往发货");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj_orange);
        }else if (roder_status.equals("3")){
            holder.tvStatus.setText("等待收货");
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("查看物流");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#383838"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj);
        }else if (roder_status.equals("4")){
            holder.tvStatus.setTextColor(Color.parseColor("#FF7722"));
            holder.tvBtnLeftTow.setVisibility(View.GONE);
            holder.tvBtnLeft.setVisibility(View.GONE);
            holder.tvBtnDelete.setText("查看物流");
            holder.tvBtnDelete.setTextColor(Color.parseColor("#383838"));
            holder.tvBtnDelete.setBackgroundResource(R.drawable.button_bj);
                holder.tvStatus.setText("交易完成");
//            if (bean.getIs_evaluation().equals("0")){
//            }else {
//                holder.tvStatus.setText("交易完成已评价");
//            }
        }
        String str="共 <font color='#FF7722'>"+ bean.getTotal_buy_number()+"</font> 件";
        holder.tvGoodsCountBottom.setText((Html.fromHtml(str)));
        if (TextUtils.isEmpty(bean.getExpress_amount())||bean.getExpress_amount().equals("0.00")){
            holder.tvYunfei.setText("免运费");
        }else {
            holder.tvYunfei.setText("含运费"+ bean.getExpress_amount()+"元");
        }
        holder.tvGoodsPriceCombined.setText("¥"+bean.getReal_pay_amount());
        holder.tvBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roder_status.equals("0")){
                    ToastUtil.showToast("删除订单");
                    mOrderClickListener.onDeleteOrderCilck( orderlist,position);
                }else if (roder_status.equals("1")){
//                    ToastUtil.showToast("催促买家");
                    ToastUtil.showToast("提醒付款");
                    mOrderClickListener.onRemindPay(orderlist,position);
                }
                else if (roder_status.equals("2")){
//                    ToastUtil.showToast("前往发货");
                    mOrderClickListener.onRemindDeliveryCilck(orderlist,position);
                }
                else if (roder_status.equals("3")){
//                    ToastUtil.showToast("查看物流");
                    mOrderClickListener.onLookLogisticsCilck(orderlist,position);
                }
                else if (roder_status.equals("4")){
//                    ToastUtil.showToast("删除订单");
                    mOrderClickListener.onLookLogisticsCilck(orderlist,position);
//                    mOrderClickListener.onDeleteOrderCilck(orderlist,position);
                }
            }
        });
        holder.tvBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (roder_status.equals("1")){
//                    ToastUtil.showToast("取消订单");
                mOrderClickListener.onCancelCilck(orderlist,position);
                }

            }
        });
        holder.tvBtnLeftTow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roder_status.equals("1")){
//
                    //修改价格
                    mOrderClickListener.onModifyThePrice(orderlist,position);
                }
            }
        });


        OrderListAdapter  adapter =new OrderListAdapter(mContext,bean.getGoods_info());
        holder.myListView.setAdapter(adapter);
        holder.myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mOrderClickListener.onItemClick(orderlist,position);
            }
        });
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrderClickListener.onItemClick(orderlist,position);
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
        LinearLayout ll_item;
        @Bind(R.id.tv_s)
        TextView tv_s;
        @Bind(R.id.tv_time)
        TextView tv_time;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

    @OnClick({R.id.tv_btn_left, R.id.tv_btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_left:
                break;
            case R.id.tv_btn_delete:
                break;
        }
    }
    public OrderClickListener mOrderClickListener;
    public void setmIDeleteBtnClickListener( OrderClickListener mOrderClickListener) {
        this.mOrderClickListener = mOrderClickListener;
    }

    public interface OrderClickListener {
        void onDeleteOrderCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); //删除订单
        void onCancelCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); //取消订单

        void onLookLogisticsCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); //查看物流

        void onRemindDeliveryCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position); // 前往发货

        void onModifyThePrice(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position);// 修改价格

        void onRemindPay(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position);// 提醒付款

        void onItemClick(List<OrderAllBean.DataBean.OrderListBean> orderlist,int position); // 详情

    }

}
