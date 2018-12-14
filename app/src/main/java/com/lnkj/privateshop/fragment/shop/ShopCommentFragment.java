package com.lnkj.privateshop.fragment.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ShopCommentListAdapter;
import com.lnkj.privateshop.entity.shopcommentBean;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class ShopCommentFragment extends BaseFragment  implements ShopCommentContract.View, PullLoadMoreRecyclerView.PullLoadMoreListener{
    private int index ;
    private String shopId;
    private String type;
    int p = 1;
    private ShopCommentPresenter mPresenter = new ShopCommentPresenter(this,getActivity());

    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    ShopCommentListAdapter adapter ;

    @Override
    protected int getContentResid() {
        return R.layout.fragment_shopcomment;
    }

    public static ShopCommentFragment newInstance() {
        ShopCommentFragment fragment = new ShopCommentFragment();
        return fragment;
    }
    @Override
    protected void init(View view) {
        super.init(view);
        mpullLoadMoreRecyclerView= (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas= (LinearLayout) view.findViewById(R.id.layout_no_datas);
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();
        index= getArguments().getInt("index");
        shopId = getArguments().getString("shopID");
        if (index==0){
            type="all";
        }else if (index==1){
            type="like ";
        }else if (index==2){
            type="med ";
        }else if (index==3){
            type="low";
        }else if (index==4){
            type="img";
        }
        mPresenter.setToken(token);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(true);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setLinearLayout();
        mpullLoadMoreRecyclerView.setRefreshing(true);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new ShopCommentListAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.getShomComment(shopId,p,type);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onRefresh() {
        p=1;
        mPresenter.getShomComment(shopId,p,type);
    }

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getShomComment(shopId,p,type);
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
        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void setTitle(String s) {

    }
    List<shopcommentBean.DataBean> data  = new ArrayList<>();
    @Override
    public void getShopCommentSucceed(shopcommentBean beans) {
        if (p==1){
            data.clear();
        }
        data.addAll(beans.getData());
        adapter.addAllData(data);
        if (data.size()==0){
            layout_no_datas.setVisibility(View.VISIBLE);
            mpullLoadMoreRecyclerView.setVisibility(View.GONE);
        }else {
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
    }


}
