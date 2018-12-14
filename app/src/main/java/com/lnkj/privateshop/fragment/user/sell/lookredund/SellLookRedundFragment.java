package com.lnkj.privateshop.fragment.user.sell.lookredund;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.LookRedundAdapter;
import com.lnkj.privateshop.entity.LookBedundBean;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class SellLookRedundFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener,DetailContract.View{
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    LookRedundAdapter adapter;
    DetailPresenter mPresenter = new DetailPresenter(this);
    @Override
    protected int getContentResid() {
        return R.layout.fragment_lookredund;
    }

    @Override
    protected void init(View view) {
        mpullLoadMoreRecyclerView= (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(true);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new LookRedundAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.getToken(token);

        super.init(view);
    }
    int index;
    int p=1;
    @Override
    protected void loadDatas() {
        super.loadDatas();
        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        switch (index){
            case 0:
                mPresenter.getDataFromServer("",p);
                break;
            case 1:
                mPresenter.getDataFromServer("1",p);
                break;
            case 2:
                mPresenter.getDataFromServer("2",p);
        }
    }
    @Override
    public void onRefresh() {
        p=1;
        switch (index){
            case 0:
                mPresenter.getDataFromServer("",p);
                break;
            case 1:
                mPresenter.getDataFromServer("1",p);
                break;
            case 2:
                mPresenter.getDataFromServer("2",p);
        }
    }

    @Override
    public void onLoadMore() {
        p++;
        switch (index){
            case 0:
                mPresenter.getDataFromServer("",p);
                break;
            case 1:
                mPresenter.getDataFromServer("1",p);
                break;
            case 2:
                mPresenter.getDataFromServer("2",p);
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

    List<LookBedundBean.DataBean.LogListBean> list = new ArrayList<>();
    @Override
    public void setData(LookBedundBean beans) {
        if (p==1){
        list.clear();
        }
        List<LookBedundBean.DataBean> lists =  beans.getData();
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).getLog_list().size(); j++) {
                LookBedundBean.DataBean.LogListBean listbean = new LookBedundBean.DataBean.LogListBean();
                listbean.setChange_desc(lists.get(i).getLog_list().get(j).getChange_desc());
                listbean.setChange_sign(lists.get(i).getLog_list().get(j).getChange_sign());
                listbean.setChange_type(lists.get(i).getLog_list().get(j).getChange_type());
                listbean.setUser_money(lists.get(i).getLog_list().get(j).getUser_money());
                listbean.setDate(lists.get(i).getDate());
                list.add(listbean);
            }
        }
    if (list.size()==0){
        layout_no_datas.setVisibility(View.VISIBLE);
        mpullLoadMoreRecyclerView.setVisibility(View.GONE);
    }else{
        layout_no_datas.setVisibility(View.GONE);
        mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);

    }


        adapter.addAllData(list);
    }

    @Override
    public void PullLoadMoreComplete() {

    }
}
