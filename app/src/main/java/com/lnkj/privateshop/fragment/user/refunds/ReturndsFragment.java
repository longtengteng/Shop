package com.lnkj.privateshop.fragment.user.refunds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.RefundsAdapter;
import com.lnkj.privateshop.entity.ReutrnBean;
import com.lnkj.privateshop.ui.mybuy.refunds.shipping.ShipPingReturnActivity;
import com.lnkj.privateshop.ui.mybuy.refunds.shipping.details.RetrunDetailsActivity;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lnkj.privateshop.view.ProgreesDialog;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class ReturndsFragment extends BaseFragment implements ReturnContract.View,PullLoadMoreRecyclerView.PullLoadMoreListener{
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    RefundsAdapter adapter;
    ReturnPresenter mPresenter = new ReturnPresenter(this);
    int index;
    @Override
    protected int getContentResid() {
        return R.layout.fragment_returnds;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        mPresenter.getToken(token);
        mpullLoadMoreRecyclerView= (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(false);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new RefundsAdapter(getActivity(),0);
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        mPresenter.getReturnOrder(index+1+"");
        adapter.setmOrderClickListener(new RefundsAdapter.OrderClickListener() {
            @Override
            public void onRevocation(final int position) {
                CenterActionDialog dialog =   new CenterActionDialog(getActivity());
                dialog.setActionString("撤销退货申请",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        try {
                            mPresenter.onRevocation(lists.get(position).getOrder_sn(),position);
                        }catch (Exception e){}
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();

            }

            @Override
            public void onDeleteOrder(final int position) {
                CenterActionDialog dialog =   new CenterActionDialog(getActivity());
                dialog.setActionString("删除订单",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                 mPresenter.onDeleteOrder(lists.get(position).getOrder_sn(),position);
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();
            }
            @Override
            public void onGoShipPing(String Order_sn) {
                Intent intent = new Intent(getActivity(),ShipPingReturnActivity.class);
                intent.putExtra("order_sn",Order_sn);
                startActivityForResult(intent,20);
            }
            @Override
            public void onItemClick(String order_sn, String order_goods_id,String roder_status) {
                Intent intent = new Intent(getActivity(),RetrunDetailsActivity.class);
                intent.putExtra("order_sn",order_sn);
                intent.putExtra("order_goods_id",order_goods_id);
                intent.putExtra("roder_status",roder_status);
                startActivity(intent);
            }



        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getReturnOrder(index+1+"");
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==20&&resultCode==30){
//
//        }
//    }

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
        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        ProgreesDialog.ProgeesDialogDiss();
    }

    @Override
    public void initView() {

    }

    @Override
    public void finisht() {

    }
    List<ReutrnBean.DataBean> lists ;
    @Override
    public void succree(ReutrnBean beass) {
        if (beass.getData().size()==0){
            mpullLoadMoreRecyclerView.setVisibility(View.GONE);
            layout_no_datas.setVisibility(View.VISIBLE);
        }else {
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
            layout_no_datas.setVisibility(View.GONE);
        }
        lists = beass.getData();
        adapter.addAllData(lists);
    }

    @Override
    public void DeleteSuccreed() {
        mPresenter.getReturnOrder(index+1+"");
    }

    @Override
    public void RevocationSucreed(int position) {
        lists.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
