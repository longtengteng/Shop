package com.lnkj.privateshop.ui.near.shopgoods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ShopGoodsAdapter;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShopGoodsActivity extends BaseActivity implements ShopGoodsContract.View {
    ShopGoodsPresenter mPresenter = new ShopGoodsPresenter(this);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.layout_no_datas)
    LinearLayout layoutNoDatas;
    ShopGoodsAdapter adapter;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;

    @Override
    public int initContentView() {
        return R.layout.activity_shop_goods;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        tvTitle.setText("选择商品");
        tvRightBlue.setText("确定");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setTextColor(Color.parseColor("#ff7722"));
        String shop_id = PreferencesUtils.getString(this, "shop_id");
        //设置是否可以下拉刷新
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        pullLoadMoreRecyclerView.setGridLayout(2);
        adapter = new ShopGoodsAdapter(this);
        pullLoadMoreRecyclerView.setAdapter(adapter);
        if (!TextUtils.isEmpty(shop_id)) {
            mPresenter.getDataFromServer(shop_id);
        }
    }

    @Override
    public void initUiAndListener() {

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
    }

    @Override
    public void initView() {

    }

    List<ShopMerchandiseListBean.DataBean.GoodsListBean> lists = new ArrayList<>();

    @Override
    public void getOrderData(ShopMerchandiseListBean beass) {
        lists.addAll(beass.getData().getGoodsList());
        adapter.addAllData(lists);
    }

    @OnClick({R.id.img_back, R.id.tv_right_blue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_blue:
                List<ShopMerchandiseListBean.DataBean.GoodsListBean> listss= new ArrayList<>();
                for (int i = 0; i < lists.size(); i++) {
                    if (lists.get(i).getIscheck()){
                        listss.add(lists.get(i));
                    }
                }
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("lists", (Serializable) listss);
                intent.putExtras(bundle);
                setResult(30,intent);
                finish();
                break;
        }
    }


}
