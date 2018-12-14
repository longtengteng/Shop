package com.lnkj.privateshop.ui.shop.shopclass.shopclassddetailed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ShopClassGoodsListAdapter;
import com.lnkj.privateshop.entity.ShopClassgoodsBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class ShopClasssFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener, ShopClassContract.View {


    @Bind(R.id.layout_no_datas)
    LinearLayout layoutNoDatas;

    ShopClassGoodsListAdapter adapter;
    @Bind(R.id.pullLoadMoreRecyclerView)

    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private String goods_class_id;
    private String shop_id;
    private int p = 1;
    private String sort = "add_time";
    ShopClassPresenter mPresenter = new ShopClassPresenter(this, getActivity());

    @Override
    protected int getContentResid() {
        return R.layout.fragment_shop_class;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        ButterKnife.bind(this, view);
        //设置是否可以下拉刷新
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setPushRefreshEnable(true);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        pullLoadMoreRecyclerView.setGridLayout(2);
        pullLoadMoreRecyclerView.setRefreshing(true);
        adapter = new ShopClassGoodsListAdapter(getActivity());
        pullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.setToken(token);

        adapter.setOnItemClickListener(new ShopClassGoodsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                intent.putExtra("goods_id", lists.get(position).getGoods_id());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();
        Bundle bundle = getArguments();
        int index = bundle.getInt("index");
        goods_class_id = bundle.getString("goods_class_id");
        shop_id = bundle.getString("shop_id");
        switch (index) {
            case 0:
                sort = "add_time";
                break;
            case 1:
                sort = "collect";
                break;
            case 2:
                sort = "price_asc";
                break;
            case 3:
                sort = "price_desc";
                break;
        }
        mPresenter.getShopClass(shop_id, sort, goods_class_id, p);
//        System.out.println("shop_id:"+shop_id);
//        System.out.println("sort:"+sort);
//        System.out.println("goods_class_id:"+goods_class_id);
    }

    @Override
    public void onRefresh() {
        lists.clear();
        p = 1;
        mPresenter.getShopClass(shop_id, sort, goods_class_id, p);
    }

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getShopClass(shop_id, sort, goods_class_id, p);
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        if (pullLoadMoreRecyclerView!=null){

        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        }
    }

    @Override
    public void setTitle(String s) {

    }

    List<ShopClassgoodsBean.DataBean> lists = new ArrayList<>();

    @Override
    public void getShopClassSucceed(ShopClassgoodsBean beans) {
        if (p==1){
        lists.clear();
        }
        lists.addAll(beans.getData());
        adapter.addAllData(lists);
        if (lists.size() == 0) {
            layoutNoDatas.setVisibility(View.VISIBLE);
            pullLoadMoreRecyclerView.setVisibility(View.GONE);
        } else {
            layoutNoDatas.setVisibility(View.GONE);
            pullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
