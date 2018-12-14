package com.lnkj.privateshop.fragment.shop.shopinfo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.MemListAdapter;
import com.lnkj.privateshop.entity.MenGoodsListBean;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class Membersragment extends BaseFragment implements MerchandiseContract.View,PullLoadMoreRecyclerView.PullLoadMoreListener{
    private int index ;
    int p = 1;
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    MemListAdapter adapter ;
    MerchandisePresenter  mPresenter = new MerchandisePresenter(this);
    String shop_id;
    public static Membersragment newInstance() {
        Membersragment fragment = new Membersragment();
        return fragment;
    }


    @Override
    protected int getContentResid() {
        return R.layout.fragment_shop_merchandise;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        mpullLoadMoreRecyclerView= (PullLoadMoreRecyclerView)view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        index= getArguments().getInt("index");
        shop_id=getArguments().getString("shopID");
        mPresenter.getToken(token);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(true);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setGridLayout(2);
        mpullLoadMoreRecyclerView.setRefreshing(true);
        mpullLoadMoreRecyclerView.setGridLayout(2);
        adapter = new MemListAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);

        mPresenter.getDataFromServer(p,shop_id);

        adapter.setOnItemClickListener(new MemListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                intent.putExtra("goods_id",lists.get(position).getGoods_id());
                startActivity(intent);
            }
        });
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
    public void getGoodsListSucceed(ShopMerchandiseListBean beass) {

    }

    @Override
    public void okGoods() {

    }
    List<MenGoodsListBean.DataBean> lists = new ArrayList<>();
    @Override
    public void getMenGoodsSuccreed(MenGoodsListBean beans) {
        if (p==1){
            lists.clear();
        }
        lists.addAll(beans.getData());
        adapter.addAllData(lists);
        if (lists.size()==0&&p==1){
            mpullLoadMoreRecyclerView.setBackgroundColor(Color.parseColor("#00000000"));
            layout_no_datas.setVisibility(View.VISIBLE);
        }else {
            mpullLoadMoreRecyclerView.setBackgroundColor(Color.parseColor("#ffffff"));
            layout_no_datas.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRefresh() {
        p=1;
        mPresenter.getDataFromServer(p,shop_id);
    }

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getDataFromServer(p,shop_id);
    }
}
