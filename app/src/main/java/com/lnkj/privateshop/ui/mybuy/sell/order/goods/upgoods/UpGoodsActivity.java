package com.lnkj.privateshop.ui.mybuy.sell.order.goods.upgoods;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.GoodsAdapter;
import com.lnkj.privateshop.entity.SellGoods;
import com.lnkj.privateshop.utils.ToastUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UpGoodsActivity extends BaseActivity implements UpGoodsContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    @Bind(R.id.cb_check)
    CheckBox cbCheck;
    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.layout_no_datas)
    LinearLayout layout_no_datas;
    public GoodsAdapter adapter;
    private UpGoodsPresenter mPresenter = new UpGoodsPresenter(this);

    @Override
    public int initContentView() {
        return R.layout.activity_up_goods;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("管理上架商品");
        adapter = new GoodsAdapter(this);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(false);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
//        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setRefreshing(true);
        mpullLoadMoreRecyclerView.setLinearLayout();

        mpullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.getToken(token);
        mPresenter.getUpGoodsFromServer();
        adapter.setOnItemClickListener(new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Intent intent = new Intent(UpGoodsActivity.this,GoodsSdetailedActivity.class);
//                intent.putExtra("goodsid",list.get(position).getGoods_id());
//                intent.putExtra("flag","up");
//                startActivityForResult(intent,20);
            }
        });

        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                for (int i = 0; i < list.size(); i++) {
                    if (b){
                        list.get(i).setIschecked(true);
                    }else {
                        list.get(i).setIschecked(false);
                    }
                }
            adapter.notifyDataSetChanged();
            }
        });

    }
    @Override
    public void hideLoading() {
//        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        progressDialog.dismiss();
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
    public void initView() {

    }
    public List<SellGoods.DataBean.GoodsListBean> list = new ArrayList<>();
    @Override
    public void getUpGOodssucceed(SellGoods beass) {
        list.clear();
        list.addAll(beass.getData().getGoods_list());
        adapter.addAllData(list,1);
        if (list.size()==0){
        layout_no_datas.setVisibility(View.VISIBLE);
        mpullLoadMoreRecyclerView.setVisibility(View.GONE);

        }else {
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void upGoodsSucceed() {
        mPresenter.getUpGoodsFromServer();
    }

    @Override
    public void DeleteGoodsSucceed() {
        mPresenter.getUpGoodsFromServer();
    }

    @OnClick({R.id.img_back, R.id.tv_delete, R.id.tv_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_delete:
//                ToastUtil.showToast("删除商品");
                String goodsid1 =   adapter.getGoodsId();
                if (TextUtils.isEmpty(goodsid1)){
                    ToastUtil.showToast("请选择商品");
                }else {
                    mPresenter.DeleteGoods(goodsid1);
                }

                break;
            case R.id.tv_down:
              String goodsid =   adapter.getGoodsId();
                if (TextUtils.isEmpty(goodsid)){
                    ToastUtil.showToast("请选择商品");
                }else {
                    mPresenter.upGoods(goodsid);
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==20){
            if (resultCode==30){
            mPresenter.getUpGoodsFromServer();
            }
        }
    }
}
