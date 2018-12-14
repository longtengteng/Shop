package com.lnkj.privateshop.ui.mybuy.myorder.looktransit;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.LookTransitAdapter;
import com.lnkj.privateshop.entity.ExpressBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LookTransitActivity extends BaseActivity implements LookTransitContract.View {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.mImageView)
    ImageView mImageView;
    @Bind(R.id.tv_status)
    TextView tvStatus;
    @Bind(R.id.tv_goods_count)
    TextView tvGoodsCount;
    @Bind(R.id.tv_company)
    TextView tvCompany;
    @Bind(R.id.tv_order_num)
    TextView tvOrderNum;
    @Bind(R.id.mListView)
    ListView mListView;
    LookTransitPresenter mPresenter = new LookTransitPresenter(this, this);
    String orderId;
    String goods_pic;
    @Bind(R.id.layout_no_datas)
    LinearLayout layoutNoDatas;
    @Bind(R.id.tv_wul)
    TextView tv_wul;

    @Override
    public int initContentView() {
        return R.layout.activity_look_transit;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        tvTitle.setText("查看物流");
        orderId = getIntent().getStringExtra("orderId");
        goods_pic = getIntent().getStringExtra("goods_pic");
        mPresenter.getPutFromServer(orderId);
    }

    @Override
    public void initUiAndListener() {

    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
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

    @Override
    public void finisht() {

    }

    @Override
    public void succree(ExpressBean b) {
        if (!TextUtils.isEmpty(b.getData().getState())) {
            if (b.getData().getState().equals("2")) {
                tvStatus.setText("物流状态： 在途中");
            } else if (b.getData().getState().equals("3")) {
                tvStatus.setText("物流状态： 已签收");
                tvStatus.setTextColor(Color.parseColor("#ff7722"));
            } else if (b.getData().getState().equals("4")) {
                tvStatus.setText("物流状态： 问题件");
            } else if (b.getData().getState().equals("1")) {
                tvStatus.setText("物流状态： 待揽件");
            }
        }
        tvCompany.setText("承运公司： " + b.getData().getExpress_name());
        tvOrderNum.setText("运单编号： " + b.getData().getExpress_code());
        tvGoodsCount.setText("共" + b.getData().getGoods_counts() + "件商品");

        Glide
                .with(this)
                .load(goods_pic)
                .error(R.mipmap.de_photo)
                .into(mImageView);

        if (b.getData().getTraces().size() == 0) {
            layoutNoDatas.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
            tv_wul.setText("没有物流信息");
        } else {
            layoutNoDatas.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            LookTransitAdapter adapter = new LookTransitAdapter(this, b.getData().getTraces());
            mListView.setAdapter(adapter);
        }
    }


}
