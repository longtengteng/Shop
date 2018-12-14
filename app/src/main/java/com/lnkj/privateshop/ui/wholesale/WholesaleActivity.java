package com.lnkj.privateshop.ui.wholesale;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.WholesaleListAdapter;
import com.lnkj.privateshop.entity.WhoLesaleBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WholesaleActivity extends BaseActivity implements WhoLesaleContract.View,PullLoadMoreRecyclerView.PullLoadMoreListener{
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.layout_no_datas)
    LinearLayout layoutNoDatas;
    WhoLesalePresenter mPresenter = new WhoLesalePresenter(this);
    WholesaleListAdapter adapter ;
    int p =1;
    @Override
    public int initContentView() {
        return R.layout.activity_wholesale;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("定制批发");
        ivRight.setVisibility(View.GONE);
        ivRight.setImageResource(R.mipmap.nav_icon_share);
        pullLoadMoreRecyclerView.setPullRefreshEnable(true);
        pullLoadMoreRecyclerView.setPushRefreshEnable(true);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        pullLoadMoreRecyclerView.setLinearLayout();
        adapter=new WholesaleListAdapter(this);
        pullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.getToken(token);
        mPresenter.getDataFromServer(p);
    }
    @Override
    public void initUiAndListener() {
        adapter.setOnItemClickListener(new WholesaleListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(WholesaleActivity.this, GoodsInfoActivity.class);
                intent.putExtra("goods_id",lists.get(position).getGoods_id());
                startActivity(intent);
            }
        });
    }
    @OnClick({R.id.img_back, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_right:

                break;
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
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void initView() {

    }
    List<WhoLesaleBean.DataBean> lists = new ArrayList<>();
    @Override
    public void getOrderData(List<WhoLesaleBean.DataBean> beans) {
        if (p==1){
            lists.clear();
        }
        lists.addAll(beans);
        if (lists.size()==0){
            pullLoadMoreRecyclerView.setVisibility(View.GONE);
            layoutNoDatas.setVisibility(View.VISIBLE);
        }else {
            pullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
            layoutNoDatas.setVisibility(View.GONE);
            adapter.addAllData(lists);
        }
    }

    @Override
    public void onRefresh() {
        p=1;
        mPresenter.getDataFromServer(p);
    }

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getDataFromServer(p);
    }
}
