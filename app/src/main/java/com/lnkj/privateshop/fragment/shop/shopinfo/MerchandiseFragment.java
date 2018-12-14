package com.lnkj.privateshop.fragment.shop.shopinfo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.MembersAdapter;
import com.lnkj.privateshop.adapter.MerchandiseListAdapter;
import com.lnkj.privateshop.entity.MenGoodsListBean;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class MerchandiseFragment extends BaseFragment implements MerchandiseContract.View, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private int index;
    int p = 1;
    int pv = 1; //会员商品分页
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;

    MerchandiseListAdapter adapter;
    MembersAdapter huiAdapter;

    MerchandisePresenter mPresenter = new MerchandisePresenter(this);
    String sort;
    String shop_id;

    public static MerchandiseFragment newInstance() {
        MerchandiseFragment fragment = new MerchandiseFragment();
        return fragment;
    }


    @Override
    protected int getContentResid() {
        return R.layout.fragment_shop_merchandise;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        mpullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        index = getArguments().getInt("index");
        shop_id = getArguments().getString("shopID");
        mPresenter.getToken(token);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(true);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setGridLayout(2);
        mpullLoadMoreRecyclerView.setRefreshing(true);
        mpullLoadMoreRecyclerView.setGridLayout(2);


        adapter = new MerchandiseListAdapter(getActivity());
        adapter.setOnItemClickListener(new MerchandiseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                intent.putExtra("goods_id", lists.get(position).getGoods_id());
                startActivity(intent);
            }
        });



        huiAdapter = new MembersAdapter(getActivity());
        huiAdapter.setOnItemClickListener(new MembersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                intent.putExtra("goods_id", datas.get(position).getGoods_id());
            }
        });
        if (index == 0) {
            sort = "";
            mpullLoadMoreRecyclerView.setAdapter(adapter);
            mPresenter.getDataFromServer(p, sort, shop_id);
        } else if (index == 1) {
            sort = "new";
            mpullLoadMoreRecyclerView.setAdapter(adapter);
            mPresenter.getDataFromServer(p, sort, shop_id);
        } else if (index == 2) {
            sort = "sales";
            mpullLoadMoreRecyclerView.setAdapter(adapter);
            mPresenter.getDataFromServer(p, sort, shop_id);
        } else if (index == 3) {
            mpullLoadMoreRecyclerView.setAdapter(huiAdapter);
            mPresenter.getDataFromServer(p, shop_id);
        }


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

    List<ShopMerchandiseListBean.DataBean.GoodsListBean> lists = new ArrayList<>();

    //非会员商品
    @Override
    public void getGoodsListSucceed(ShopMerchandiseListBean beass) {
        if (p == 1) {
            lists.clear();
        }

        lists.addAll(beass.getData().getGoodsList());
        adapter.addAllData(lists);
        if (lists.size() == 0 && p == 1) {
            mpullLoadMoreRecyclerView.setBackgroundColor(Color.parseColor("#00000000"));
            layout_no_datas.setVisibility(View.VISIBLE);
        } else {
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

    }

    @Override
    public void okGoods() {

    }

    List<MenGoodsListBean.DataBean> datas = new ArrayList<>();

    @Override
    public void onPause() {
        super.onPause();
        datas.clear();
    }

    //会员商品
    @Override
    public void getMenGoodsSuccreed(MenGoodsListBean beans) {
        if (pv == 1) {
            datas.clear();
        }

        datas.addAll(beans.getData());
        huiAdapter.addAllData(datas);

        if (lists.size() == 0 && p == 1) {
            mpullLoadMoreRecyclerView.setBackgroundColor(Color.parseColor("#00000000"));
            layout_no_datas.setVisibility(View.VISIBLE);
        } else {
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setBackgroundColor(Color.parseColor("#ffffff"));
        }


    }

    @Override
    public void onRefresh() {
        if (index != 3) {
            lists.clear();
            p = 1;
            mPresenter.getDataFromServer(p, sort, shop_id);
        } else {
            datas.clear();
            pv = 1;
            mPresenter.getDataFromServer(p, shop_id);
        }
    }

    @Override
    public void onLoadMore() {

        if (index != 3) {
            p++;
            mPresenter.getDataFromServer(p, sort, shop_id);

        } else {
            mPresenter.getDataFromServer(pv++, shop_id);
        }
    }
}
