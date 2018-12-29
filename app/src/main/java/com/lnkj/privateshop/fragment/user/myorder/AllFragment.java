package com.lnkj.privateshop.fragment.user.myorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderAdapter;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.entity.OrderAllBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.goodscar.gopay.GoPayOrderActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.MyOrderActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.commentaries.OredrCommetActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.looktransit.LookTransitActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.orderdetailed.OrderDetailedActivity;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lnkj.privateshop.view.ProgreesDialog;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class AllFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener, AllContract.View {
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    OrderAdapter adapter;
    AllPresenter mPresenter = new AllPresenter(this);
    int p = 1;
    private static final String TAG = "AllFragment";
//    private OrderAllBean.DataBean beans;


    @Override
    protected int getContentResid() {
        return R.layout.fragment_order_all;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        mPresenter.getToken(token);
        mpullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(true);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new OrderAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);

        adapter.setmIDeleteBtnClickListener(new OrderAdapter.OrderClickListener() {
            @Override
            public void onDeleteOrderCilck(final List<OrderAllBean.DataBean.OrderListBean> orderlist, final int position) {
                LLog.d(TAG, "删除订单");
                CenterActionDialog dialog = new CenterActionDialog(getActivity());
                dialog.setActionString("您要删除订单吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        mPresenter.onDeleteOrder(orderlist, position);
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();

            }

            @Override
            public void onLookLogisticsCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
//                ToastUtil.showToast("查看物流");
                Intent intent = new Intent(getActivity(), LookTransitActivity.class);
                intent.putExtra("orderId", orderlist.get(position).getOrder_sn());
                try {

                    intent.putExtra("goods_pic", Constants.Base_URL + orderlist.get(position).getGoods_info().get(0).getGoods_img());
                } catch (Exception e) {
                }
                getActivity().startActivity(intent);
            }

            //提醒发货
            @Override
            public void onRemindDeliveryCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
                mPresenter.onRemindDelivery(orderlist, position);
            }

            @Override
            public void onGoPayCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
//                ToastUtil.showToast("去支付");
                Intent intent = new Intent(getActivity(), GoPayOrderActivity.class);
                intent.putExtra("order_sn", orderlist.get(position).getOrder_sn());
                intent.putExtra("total_goods_num", orderlist.get(position).getTotal_buy_number());
                intent.putExtra("total_amount", orderlist.get(position).getReal_pay_amount());
                intent.putExtra("express", orderlist.get(position).getExpress_amount());
                try {
                    double f = Double.parseDouble(orderlist.get(position).getReal_pay_amount()) - Double.parseDouble(orderlist.get(position).getExpress_amount());
                    BigDecimal bg = new BigDecimal(f);
                    double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    intent.putExtra("total_goods_amount", f1 + "");
                } catch (Error e) {

                }


//                total_goods_num = getIntent().getStringExtra("total_goods_num");  商品数量
//                total_goods_amount = getIntent().getStringExtra("total_goods_amount"); 商品价格

//                express = getIntent().getStringExtra("express"); 、、物流费用
//                total_amount = getIntent().getStringExtra("total_amount"); 订单总价
                startActivity(intent);
            }

            //取消订单
            @Override
            public void onCancelorder(final List<OrderAllBean.DataBean.OrderListBean> orderlist, final int position) {
                CenterActionDialog dialog = new CenterActionDialog(getActivity());
                dialog.setActionString("您要取消该订单吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        mPresenter.onCancelorder(orderlist, position);
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();
            }

            //确认收货
            @Override
            public void onOkGoodsCilck(final List<OrderAllBean.DataBean.OrderListBean> orderlist, final int position) {

                CenterActionDialog dialog = new CenterActionDialog(getActivity());
                dialog.setActionString("您要确认收货吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        mPresenter.onOkGoods(orderlist, position);
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();
            }

            @Override
            public void onGoEvaluationCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
//                ToastUtil.showToast("去评价");
                Intent intent = new Intent(getActivity(), OredrCommetActivity.class);
                intent.putExtra("orderId", orderlist.get(position).getOrder_sn());
                intent.putExtra("shop_id", orderlist.get(position).getShop_id());
                getActivity().startActivityForResult(intent, 20);
            }

            //再次购买
            @Override
            public void onAgainCilck(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
                //再次购买
                Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                intent.putExtra("goods_id", orderlist.get(position).getGoods_info().get(0).getGoods_id());
                startActivity(intent);
            }


            //订单详情
            @Override
            public void onItemClick(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
                Intent intent = new Intent(getActivity(), OrderDetailedActivity.class);
                intent.putExtra("orderId", orderlist.get(position).getOrder_sn());
                intent.putExtra("goods_id", orderlist.get(position).getGoods_info().get(0).getGoods_id());
                getActivity().startActivityForResult(intent, 20);

            }

        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==20){
//            if (resultCode==30){
//                mPresenter.getDataFromService(p,index);
//            }
//        }
//    }

    List<OrderAllBean.DataBean.OrderListBean> list = new ArrayList<>();
    int index;

    @Override
    protected void loadDatas() {
        super.loadDatas();
        Bundle bundle = getArguments();
        index = bundle.getInt("index");

    }

    @Override
    public void onResume() {
        super.onResume();
        MyOrderActivity parentActivity = (MyOrderActivity) getActivity();
        parentActivity.getDataFromSercer();
        p = 1;
        mPresenter.getDataFromService(p, index);
    }

    @Override
    public void onRefresh() {
        p = 1;
        mPresenter.getDataFromService(p, index);
    }

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getDataFromService(p, index);
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {
        ProgreesDialog.ProgeesDialogShow();
    }

    @Override
    public void hideLoading() {
        ProgreesDialog.ProgeesDialogDiss();
        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void initView() {

    }

    @Override
    public void updateView(int id) {

    }

    @Override
    public void upateFragmentData(int type) {

    }

    @Override
    public void liftData(GoodsCategoryBean beans) {

    }

    @Override
    public void remoOrder(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position, String s) {
        mPresenter.getDataFromService(p, index);

        MyOrderActivity parentActivity = (MyOrderActivity) getActivity();
        parentActivity.getDataFromSercer();
    }

    @Override
    public void okGoods(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position, String s) {

        mPresenter.getDataFromService(p, index);
        MyOrderActivity parentActivity = (MyOrderActivity) getActivity();
        parentActivity.getDataFromSercer();
    }

    @Override
    public void onCancelorderScuurdde(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position, String s) {


        MyOrderActivity parentActivity = (MyOrderActivity) getActivity();
        parentActivity.getDataFromSercer();
    }

    @Override
    public void remindDelivery(String s) {
        mPresenter.getDataFromService(p, index);

        MyOrderActivity parentActivity = (MyOrderActivity) getActivity();
        parentActivity.getDataFromSercer();
    }

    @Override
    public void Scrrueed(OrderAllBean beass) {
        List<OrderAllBean.DataBean.OrderListBean> orderlist = beass.getData().getOrder_list();
        if (p == 1) {
            list.clear();
        }
        list.addAll(orderlist);
        adapter.addAllData(list, index);
        if (list.size() == 0) {
            layout_no_datas.setVisibility(View.VISIBLE);
            mpullLoadMoreRecyclerView.setVisibility(View.GONE);
        } else {
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==20){
//            if (resultCode==30){
////                mPresenter.getDataFromServer();
//            }
//        }
//    }

}
