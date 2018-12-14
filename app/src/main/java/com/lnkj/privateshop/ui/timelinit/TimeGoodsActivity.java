package com.lnkj.privateshop.ui.timelinit;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.TimeGoodsListAdapter;
import com.lnkj.privateshop.entity.TimeGoodsBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.utils.CountDownUtil;
import com.lnkj.privateshop.utils.ToastUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimeGoodsActivity extends BaseActivity implements TimeGoodsContract.View {
    TimeGoodsPresenter mPresenter = new TimeGoodsPresenter(this);
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
    TimeGoodsListAdapter adapter;
    @Bind(R.id.tv_time_h)
    TextView tvTime_H;
    @Bind(R.id.tv_time_m)
    TextView tvTime_M;
    @Bind(R.id.tv_time_s)
    TextView tvTime_S;

    @Override
    public int initContentView() {
        return R.layout.activity_time_goods;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("限时特惠");
        ivRight.setVisibility(View.GONE);
        ivRight.setImageResource(R.mipmap.nav_icon_share);
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setLinearLayout();
        adapter = new TimeGoodsListAdapter(this);
        pullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.getToken(token);
        mPresenter.getDataFromServer();
    }

    @Override
    public void initUiAndListener() {
            adapter.setOnItemClickListener(new TimeGoodsListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (lists!=null){
                    Intent intent = new Intent(TimeGoodsActivity.this, GoodsInfoActivity.class);
                    intent.putExtra("goods_id",lists.get(position).getGoods_id());
                    startActivity(intent);

                    }
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
    }

    @Override
    public void initView() {
    }
    List<TimeGoodsBean.DataBean.ListBean> lists ;
    @Override
    public void getOrderData(TimeGoodsBean.DataBean beans) {
        if (beans == null) {
            pullLoadMoreRecyclerView.setVisibility(View.GONE);
            layoutNoDatas.setVisibility(View.VISIBLE);
        } else {
            pullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
            layoutNoDatas.setVisibility(View.GONE);

         long time = Long.parseLong(beans.getInfo().getEnd_time())*1000- System.currentTimeMillis();
            CountDownUtil cdu = new CountDownUtil(time , 1000 * 60 * 60,
                    tvTime_H, 1);
            cdu.start();
            CountDownUtil cdu2 = new CountDownUtil(time , 1000 * 60,
                    tvTime_M, 2);
            cdu2.start();
            CountDownUtil cdu3 = new CountDownUtil(time , 1000,
                    tvTime_S, 3);
            cdu3.start();
            lists = beans.getList();
            adapter.addAllData(lists);
        }

    }

    @OnClick({R.id.img_back, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_right:
                ToastUtil.showToast("分享");
                break;
        }
    }
}
