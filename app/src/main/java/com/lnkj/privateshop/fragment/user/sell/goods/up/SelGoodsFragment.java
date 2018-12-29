package com.lnkj.privateshop.fragment.user.sell.goods.up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.SellGoodsAdapter;
import com.lnkj.privateshop.entity.SellGoods;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.goods.goodsdetailed.GoodsSdetailedActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;


/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class SelGoodsFragment extends BaseFragment implements SellGoodsContract.View, PullLoadMoreRecyclerView.PullLoadMoreListener {
    public SellGoodsPresenter mPresenter = new SellGoodsPresenter(this);
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;

    public SellGoodsAdapter adapter;

    @Override
    protected int getContentResid() {
        return R.layout.fragment_sell_goods;
    }

    int index;

    //    public SelGoodsFragment() {
//
//    }
    public static SelGoodsFragment newInstance() {
        SelGoodsFragment fragment = new SelGoodsFragment();
        return fragment;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        Bundle bundle = getArguments();
        System.out.println("bundle:" + bundle);
        index = bundle.getInt("index");
        mPresenter.getToken(token);
        mpullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(false);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setRefreshing(true);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new SellGoodsAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new SellGoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
              /*  if (index==0){
                    Intent intent = new Intent(getActivity(),GoodsSdetailedActivity.class);
                intent.putExtra("goodsid",beans.getData().getGoods_list().get(position).getGoods_id());
                intent.putExtra("flag","down");
                startActivityForResult(intent,20);
                }else {
                    Intent intent = new Intent(getActivity(),GoodsSdetailedActivity.class);
                intent.putExtra("goodsid",beans.getData().getGoods_list().get(position).getGoods_id());
                intent.putExtra("flag","up");
                startActivityForResult(intent,20);
                }*/
                if (index == 0) {
                    Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                    intent.putExtra("goods_id", beans.getData().getGoods_list().get(position).getGoods_id());
                    intent.putExtra("from_sell", "from_sell");
                    startActivityForResult(intent, 20);
                } else {
                    Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                    intent.putExtra("goods_id", beans.getData().getGoods_list().get(position).getGoods_id());
                    intent.putExtra("from_sell", "from_sell");
                    startActivityForResult(intent, 20);
                }
            }
        });

//        mPresenter.getDataFromService(index);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getDataFromService(index);
    }

    SellGoods beans;

    @Override
    public void liftData(SellGoods beans) {
        this.beans = beans;
        if (beans.getData().getGoods_list() != null && beans.getData().getGoods_list().size() == 0) {
            layout_no_datas.setVisibility(View.VISIBLE);
            mpullLoadMoreRecyclerView.setVisibility(View.GONE);
        } else {
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
        adapter.addAllData(beans.getData().getGoods_list(), 0);
    }

    public void addIndex(int index) {
        adapter.addIndex(index);
    }

    @Override
    public String getGoodsId() {

        return adapter.getGoodsId();
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
    public void updateView(int id) {

    }

    @Override
    public void upateFragmentData(int type) {

    }


    @Override
    public void remindDelivery(String s) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
