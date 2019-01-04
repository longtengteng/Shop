package com.lnkj.privateshop.fragment.user.sell.refunds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.SellRefundsAdapter;
import com.lnkj.privateshop.entity.SellReutrnBean;
import com.lnkj.privateshop.ui.mybuy.sell.order.refunds.details.SellRetrunDetailsActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.paypwd.ChagePayPwdActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class SellRefundsFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener, SellReturnContract.View {
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    SellRefundsAdapter adapter;
    SellReturnPresenter mPresenter = new SellReturnPresenter(this);
    int pos;

    @Override
    protected int getContentResid() {
        return R.layout.fragment_sell_return;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getReturnOrder(index + 1 + "", p);
    }

    @Override
    protected void init(View view) {
        super.init(view);
        mPresenter.getToken(token);
        mpullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(false);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new SellRefundsAdapter(getActivity(), 0);
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        Bundle bundle = getArguments();
        index = bundle.getInt("index");

        adapter.setmOrderClickListener(new SellRefundsAdapter.OrderClickListener() {
            //确认收货
            @Override
            public void onReceiveGoods(final int position) {
                mPresenter.ReceiveGoods(lists.get(position).getOrder_sn(), "");
            }

            @Override
            public void onUrged(String order_sn) {
                //催促买家
                mPresenter.onUrged(order_sn);
            }

            //删除订单
            @Override
            public void onDeleteOrder(final int position) {
                CenterActionDialog dialog = new CenterActionDialog(getActivity());
                dialog.setActionString("您要删除该订单吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {

                        mPresenter.onDeleteOrder(lists.get(position).getOrder_sn());

                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();
            }
            //同意或者拒绝
            @Override
            public void onOkRoNoGoods(final int position, final String refund_type) {
                String message = null;
                if (refund_type.equals("1")) {
                    message = "同意退款";
                    mPresenter.OkRoNoGoods(lists.get(position).getOrder_sn(), refund_type, "");
                    return;
                } else if (refund_type.equals("2")) {
                    message = "同意退货退款";
                } else if (refund_type.equals("3")) {
                    message = "拒绝退款";
                } else if (refund_type.equals("4")) {
                    message = "拒绝退货退款";
                }
                CenterActionDialog dialog = new CenterActionDialog(getActivity());
                dialog.setActionString(message,
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        pos = position;
                        mPresenter.OkRoNoGoods(lists.get(position).getOrder_sn(), refund_type, "");
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();

            }

            @Override
            public void onItemClick(String order_sn, String order_goods_id, String roder_status, String refund_type, int position) {
                Intent intent = new Intent(getActivity(), SellRetrunDetailsActivity.class);
                pos = position;
                intent.putExtra("order_sn", order_sn);
                intent.putExtra("order_goods_id", order_goods_id);
                intent.putExtra("roder_status", roder_status);
                intent.putExtra("refund_type", refund_type);

                startActivityForResult(intent, 20);
            }


        });
    }

    int index;

    @Override
    protected void loadDatas() {
        super.loadDatas();


    }

    @Override
    public void onRefresh() {

    }

    int p = 1;

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getReturnOrder(index + 1 + "", p);
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void initView() {

    }

    @Override
    public void finisht() {

    }

    List<SellReutrnBean.DataBean> lists = new ArrayList<>();

    @Override
    public void succree(SellReutrnBean beass) {
        if (p == 1) {
            lists.clear();
        }
        lists.addAll(beass.getData());
        if (lists.size() == 0) {
            mpullLoadMoreRecyclerView.setVisibility(View.GONE);
            layout_no_datas.setVisibility(View.VISIBLE);
        } else {
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
            layout_no_datas.setVisibility(View.GONE);
        }
        adapter.addAllData(lists);
    }

    @Override
    public void OkRoNoGOodssuccree() {
        lists.remove(pos);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void OnDeleteOrderSucreed() {
        lists.remove(pos);
        adapter.notifyDataSetChanged();
    }


}
