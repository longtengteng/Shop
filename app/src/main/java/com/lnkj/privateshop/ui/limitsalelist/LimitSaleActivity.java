package com.lnkj.privateshop.ui.limitsalelist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.LimitSaleAdapter;
import com.lnkj.privateshop.adapter.SeachGoodsAdapter;
import com.lnkj.privateshop.entity.DynamicragBean;
import com.lnkj.privateshop.entity.LimitSaleListBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.seachgoods.SeachGoodsPresenter;
import com.lnkj.privateshop.utils.ToastUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*从首页进入的限时特惠列表*/
public class LimitSaleActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener, LimitSaleContract.View, View.OnClickListener {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.iv_sheach)
    ImageView ivSheach;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.ll_nodata)
    LinearLayout llNodata;

    private int page = 1;
    private LimitSalePresenter mPresenter = new LimitSalePresenter(this);
    private LimitSaleAdapter adapter;
    LinearLayout layout_no_datas;

    @Override
    public int initContentView() {
        return R.layout.activity_limit;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("限时特惠");
        //设置是否可以下拉刷新
        pullLoadMoreRecyclerView.setPullRefreshEnable(true);
        pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setPushRefreshEnable(true);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        pullLoadMoreRecyclerView.setGridLayout(1);


        adapter = new LimitSaleAdapter(this);
        pullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.setToken(token);
        mPresenter.getDataFromServer(page);
        adapter.setmClickListener(new LimitSaleAdapter.OrderClickListener() {
            @Override
            public void oncollectShop(int position) {
                Intent intent = new Intent(LimitSaleActivity.this, GoodsInfoActivity.class);
                intent.putExtra("act_id", lists.get(position).getAct_id());
                intent.putExtra("goods_id", lists.get(position).getGoods_id());
                startActivity(intent);
                // ToastUtil.showToast("haha" + position);
            }
        });

    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void toLoging() {

    }

    @Override
    public void PullLoadMoreComplete() {

    }

    @Override
    public void succeed(LimitSaleListBean beass) {
        if (page == 1) {
            lists.clear();
        }
        lists.addAll(beass.getData());
        adapter.addAllData(lists);
        if (lists.size() == 0 && page == 1) {
            layout_no_datas.setVisibility(View.VISIBLE);
            pullLoadMoreRecyclerView.setVisibility(View.GONE);
        } else {
            layout_no_datas.setVisibility(View.GONE);
            pullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
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
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    List<LimitSaleListBean.DataBean> lists = new ArrayList<>();

    @Override
    public void onRefresh() {
        page = 1;
        lists.clear();
        mPresenter.getDataFromServer(page);
    }

    @Override
    public void onLoadMore() {
        page = 1;
        mPresenter.getDataFromServer(page);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
