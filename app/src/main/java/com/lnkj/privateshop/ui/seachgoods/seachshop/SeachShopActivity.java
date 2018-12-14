package com.lnkj.privateshop.ui.seachgoods.seachshop;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.SeachShopAdapter;
import com.lnkj.privateshop.entity.SeachShopBean;
import com.lnkj.privateshop.ui.shop.shopInfo.ShopInfoActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SeachShopActivity extends BaseActivity implements SeachShopContract.View,PullLoadMoreRecyclerView.PullLoadMoreListener{
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.ll_nodata)
    LinearLayout llNodata;
    @Bind(R.id.et_seach)
    TextView etSeach;
    @Bind(R.id.rl_seach)
    RelativeLayout rl_seach;
    @Bind(R.id.img_back)
    ImageView img_back;
    private SeachShopAdapter shopadapter;
    private SeachShopPresenter mPresenter = new SeachShopPresenter(this);
    private int p=1;
    private String keywords;
    @Override
    public int initContentView() {
        return R.layout.activity_seach_shop;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        shopadapter = new SeachShopAdapter(this);
        keywords = getIntent().getStringExtra("keyWords");
        etSeach.setText(keywords);
        if (TextUtils.isEmpty(keywords)){
            keywords="";
            etSeach.setText("搜索店铺");
        }
        //设置是否可以下拉刷新
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setPushRefreshEnable(true);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setAdapter(shopadapter);
        mPresenter.getDataFromServer(keywords,p);
    }

    @Override
    public void initUiAndListener() {
        shopadapter.setmClickListener(new SeachShopAdapter.OrderClickListener() {
            @Override
            public void oncollectShop(int position) {
                Intent intent = new Intent(SeachShopActivity.this, ShopInfoActivity.class);
                intent.putExtra("shop_id", shopdata.get(position).getShop_id());
                startActivity(intent);
            }
        });
        rl_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
    List<SeachShopBean.DataBean.ListBean> shopdata = new ArrayList<>();
    @Override
    public void getOrderData(SeachShopBean beans) {
        System.out.println("________");
        System.out.println(p+"");
        if (p==1){
            shopdata.clear();
        }
        shopdata.addAll(beans.getData().getList());
        if (shopdata.size() == 0 && p == 1) {
            pullLoadMoreRecyclerView.setVisibility(View.GONE);
            llNodata.setVisibility(View.VISIBLE);
            ToastUtil.showToast("店铺不存在");
        } else {
            pullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
            llNodata.setVisibility(View.GONE);
        }
        shopadapter.addAllData(shopdata);
    }

    @Override
    public void onRefresh() {
        p=1;
        mPresenter.getDataFromServer(keywords,p);
    }

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getDataFromServer(keywords,p);
    }
}
