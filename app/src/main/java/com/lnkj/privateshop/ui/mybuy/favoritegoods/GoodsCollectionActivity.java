package com.lnkj.privateshop.ui.mybuy.favoritegoods;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.GoodsCollectionAdapter;
import com.lnkj.privateshop.entity.GoodsCollectionBean;
import com.lnkj.privateshop.utils.ToastUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收藏的商品
 */

public class GoodsCollectionActivity extends BaseActivity implements GoodsCollectionContract.View ,PullLoadMoreRecyclerView.PullLoadMoreListener {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;

    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    CoogsCollectionPresenter mPresenter = new CoogsCollectionPresenter(this);
    GoodsCollectionAdapter adapter ;
    int p = 1;
    @Override
    public int initContentView() {
        return R.layout.activity_goods_collection;
    }
    private int index ;
    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        mpullLoadMoreRecyclerView= (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) findViewById(R.id.layout_no_datas);
        tvTitle.setText("收藏的商品");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setTextColor(Color.parseColor("#333333"));
        tvRightBlue.setText("编辑");

        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(true);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setRefreshing(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setGridLayout(2);
        adapter = new GoodsCollectionAdapter(this);

        mpullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.getDataFromServer(p);
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

    @Override
    public void noData() {
        layout_no_datas.setVisibility(View.VISIBLE);
        tvRightBlue.setVisibility(View.GONE);
        mpullLoadMoreRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void fullData() {
        tvRightBlue.setVisibility(View.VISIBLE);
        layout_no_datas.setVisibility(View.GONE);
        mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragment(int position) {

    }
    List<GoodsCollectionBean.DataBean> beas = new ArrayList<>();
    @Override
    public void setGoodsCollectData(GoodsCollectionBean beass) {
        if (p==1){
            beas.clear();
        }
        beas.addAll(beass.getData());
        index=0;
        adapter.addAllData(beas,index);
        if (beas.size()==0){
            tvRightBlue.setVisibility(View.GONE);
            layout_no_datas.setVisibility(View.VISIBLE);
            mpullLoadMoreRecyclerView.setVisibility(View.GONE);
        }else {
            tvRightBlue.setVisibility(View.VISIBLE);
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void deleteSuccess() {
//        for (int i = 0; i < beas.size(); i++) {
//            if ( beas.get(i).getIscheck()){
//                beas.remove(i);
//            }
//        }
//        adapter.notifyDataSetChanged();
        p=1;
        mPresenter.getDataFromServer(p);
    }

    @Override
    public void PullLoadMoreComplete() {
        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
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
    public static void  startActivity(Context context){
        Intent intent = new Intent(context,GoodsCollectionActivity.class);
        context.startActivity(intent);
    }
    @OnClick({R.id.img_back,R.id.tv_right_blue,R.id.tv_left_blue})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_blue:
                if (index==0){
                     tvRightBlue.setTextColor(Color.parseColor("#FF7722"));
                    tvRightBlue.setText("删除");
                    tvLeftBlue.setText("取消");
                    imgBack.setVisibility(View.GONE);
                    tvLeftBlue.setVisibility(View.VISIBLE);
                index=1;
                }else {
                    if (!TextUtils.isEmpty(adapter.getChaechData())){

                    index=0;
                    tvRightBlue.setTextColor(Color.parseColor("#333333"));

                    //删除
                    mPresenter.deldetGoods(adapter.getChaechData());
                    imgBack.setVisibility(View.VISIBLE);
                    tvLeftBlue.setVisibility(View.GONE);
                    tvLeftBlue.setText("编辑");
                    }else {
                        ToastUtil.showToast("请选择商品");
                    }
                }
                adapter.addIndex(index);
                break;
            case R.id.tv_left_blue:
                index=0;
                tvLeftBlue.setTextColor(Color.parseColor("#333333"));
                imgBack.setVisibility(View.VISIBLE);
                tvLeftBlue.setVisibility(View.GONE);
                adapter.addIndex(index);
                tvRightBlue.setText("编辑");
                break;
        }
    }

}
