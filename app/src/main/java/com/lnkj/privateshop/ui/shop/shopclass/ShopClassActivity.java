package com.lnkj.privateshop.ui.shop.shopclass;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ShopClassAdapter;
import com.lnkj.privateshop.entity.ShopClassBean;
import com.lnkj.privateshop.ui.shop.shopclass.shopclassddetailed.ShopClassDetActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShopClassActivity extends BaseActivity implements ShopClassContract.View {
    String shop_id;
    ShopClassPresenter mPresenter = new ShopClassPresenter(this, this);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.mListView)
    ListView mListView;
    private ShopClassAdapter adapter;

    @Override
    public int initContentView() {
        return R.layout.activity_shop_classctivity;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("商品分类");
        shop_id = getIntent().getStringExtra("shop_id");
        mPresenter.setToken(token);
        mPresenter.getShopClass(shop_id);
    }

    @Override
    public void initUiAndListener() {
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(ShopClassActivity.this, ShopClassDetActivity.class);
            intent.putExtra("goods_class_name",beans.getData().get(i).getCat_name());
            intent.putExtra("goods_class_id",beans.getData().get(i).getCat_id());
            intent.putExtra("shop_id",shop_id);
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
    public void initView() {

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
    public void setTitle(String s) {

    }
    ShopClassBean beans;
    @Override
    public void getShopClassSucceed(ShopClassBean beans) {
        this.beans=beans;
        adapter = new ShopClassAdapter(this,beans.getData());
        mListView.setAdapter(adapter);
    }



    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
