package com.lnkj.privateshop.fragment.user.sell.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.SellOrderAdapter;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.entity.OrderAllBean;
import com.lnkj.privateshop.ui.mybuy.myorder.looktransit.LookTransitActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.SellOrderActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed.SellOrderDetaildeActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed.modify.ModifyPriceActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.shipping.ShipPingActivity;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lnkj.privateshop.view.ProgreesDialog;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class OredrFragment extends BaseFragment implements OrderContract.View, PullLoadMoreRecyclerView.PullLoadMoreListener{
    OredrPresenter mPresenter = new OredrPresenter(this);
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    SellOrderAdapter adapter;
    @Override
    protected int getContentResid() {
        return R.layout.fragment_sell_order;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        mPresenter.getToken(token);
        mpullLoadMoreRecyclerView= (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(true);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new SellOrderAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        adapter.setmIDeleteBtnClickListener(new SellOrderAdapter.OrderClickListener() {
            @Override
            public void onDeleteOrderCilck(final List<OrderAllBean.DataBean.OrderListBean> orderlist, final int position) {
                LLog.d(TAG,"删除订单");
                CenterActionDialog dialog =   new CenterActionDialog(getActivity());
                dialog.setActionString("您要删除订单吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        mPresenter.onDeleteOrder(orderlist,position,100);
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();

            }

            @Override
            public void onCancelCilck(final List<OrderAllBean.DataBean.OrderListBean> orderlist, final int position) {
                //取消订单
                CenterActionDialog dialog =   new CenterActionDialog(getActivity());
                dialog.setActionString("您要取消订单吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        mPresenter.onDeleteOrder(orderlist,position,0);
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();

            }

            @Override
            public void onLookLogisticsCilck( List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
                //查看物流
                Intent intent = new Intent(getActivity(),LookTransitActivity.class);
                intent.putExtra("orderId",orderlist.get(position).getOrder_sn());
                try {

                intent.putExtra("goods_pic", Constants.Base_URL+orderlist.get(position).getGoods_info().get(0).getGoods_img());
                }catch (Exception e){}
                startActivity(intent);

            }

            @Override
            public void onRemindDeliveryCilck( List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
//                mPresenter.onRemindDelivery( orderlist,position); //前往发货
                Intent intent = new Intent(getActivity(),ShipPingActivity.class);
                intent.putExtra("orderId",orderlist.get(position).getOrder_sn());
                getActivity().startActivityForResult(intent,20);

            }



            @Override
            public void onModifyThePrice(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
                //修改价格
                Intent intent = new Intent(getActivity(),ModifyPriceActivity.class);
                intent.putExtra("orderId",orderlist.get(position).getOrder_sn());
                startActivityForResult(intent,20);

            }

            @Override
            public void onRemindPay(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
                        //提醒付款
                 mPresenter.onRemindPay(orderlist.get(position).getOrder_sn());
            }

            @Override
            public void onItemClick(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
                Intent intent = new Intent(getActivity(),SellOrderDetaildeActivity.class);
                intent.putExtra("orderId",orderlist.get(position).getOrder_sn());
                getActivity().startActivityForResult(intent,20);
            }
        });
    }
    List<OrderAllBean.DataBean.OrderListBean> list = new ArrayList<>();
    int index;
    int p =1;
    @Override
    protected void loadDatas() {
        super.loadDatas();
        Bundle bundle = getArguments();
        index = bundle.getInt("index");

    }

    @Override
    public void onResume() {
        super.onResume();
        p=1;
        SellOrderActivity sellAct = (SellOrderActivity) getActivity();
        sellAct.getDataFromServer();
        mPresenter.getDataFromService(p,index);
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
        orderlist.remove(position);
        adapter.notifyDataSetChanged();

        SellOrderActivity sellAct = (SellOrderActivity) getActivity();
        sellAct.getDataFromServer();
    }

    @Override
    public void okGoods(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position, String s) {
        mPresenter.getDataFromService(p,index);
        SellOrderActivity sellAct = (SellOrderActivity) getActivity();
        sellAct.getDataFromServer();
    }

    @Override
    public void remindDelivery(String s) {
        SellOrderActivity sellAct = (SellOrderActivity) getActivity();
        sellAct.getDataFromServer();
    }

    @Override
    public void getOrderData(OrderAllBean beass) {
        List<OrderAllBean.DataBean.OrderListBean> orderlist =   beass.getData().getOrder_list();
        if (p==1){
        list.clear();
        }
        list.addAll(orderlist);
        adapter.addAllData(list,index);
        if (list.size()==0){
            layout_no_datas.setVisibility(View.VISIBLE);
            mpullLoadMoreRecyclerView.setVisibility(View.GONE);
        }else {
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRemindPaySucreed() {
    }

    @Override
    public void onRefresh() {
        p=1;
        mPresenter.getDataFromService(p,index);
    }

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getDataFromService(p,index);
    }
}
