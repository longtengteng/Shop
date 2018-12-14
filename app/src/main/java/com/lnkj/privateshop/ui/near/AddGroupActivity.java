package com.lnkj.privateshop.ui.near;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.AlbumShopGoodsAdapter;
import com.lnkj.privateshop.entity.ShopMerchandiseListBean;
import com.lnkj.privateshop.ui.near.shopgoods.ShopGoodsActivity;
import com.lnkj.privateshop.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddGroupActivity extends BaseActivity implements AddGoodsContract.View{
    /**
     * 发表圈子
     */

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.mGridView)
    MyGridView mGridView;
    AddGoodsPresenter mPresenter = new AddGoodsPresenter(this);


    @Override
    public int initContentView() {
        return R.layout.activity_add_group;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        tvTitle.setText("发布圈子");
        tvRightBlue.setText("发表");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setTextColor(Color.parseColor("#ff7722"));
    }
    AlbumShopGoodsAdapter mAdapter;
    @Override
    public void initUiAndListener() {

        mAdapter = new AlbumShopGoodsAdapter(this,lists);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == getDataSize()) {
                 Intent intent = new Intent(AddGroupActivity.this,ShopGoodsActivity.class);
                    startActivityForResult(intent,20);

                }
            }
        });
    }
    private int getDataSize() {
        return lists == null ? 0 : lists.size();
    }

    @OnClick({R.id.img_back, R.id.tv_right_blue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_blue:
                mPresenter.getDataFromServer(lists,etContent.getText().toString());
                break;
        }
    }
    List<ShopMerchandiseListBean.DataBean.GoodsListBean> lists = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==20&&resultCode==30){
        if (data!=null){
            Bundle bundle =  data.getExtras();
             lists = (List<ShopMerchandiseListBean.DataBean.GoodsListBean>) bundle.getSerializable("lists");
            mAdapter.bindList(lists);
        }
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
    }

    @Override
    public void initView() {

    }

    @Override
    public void getOrderData() {
            setResult(30);
            finish();
    }
}
