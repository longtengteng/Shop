package com.lnkj.privateshop.ui.mybuy.footprint;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.FootPrintAdapter;
import com.lnkj.privateshop.entity.FootPrintBean;
import com.lnkj.privateshop.utils.ToastUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的足迹
 */
public class FootPrintActivity extends BaseActivity implements FootPrintContract.View,PullLoadMoreRecyclerView.PullLoadMoreListener{
    FootPrintPresenter mPresenter= new FootPrintPresenter(this);
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
    FootPrintAdapter adapter ;
    int p = 1;
    @Override
    public int initContentView() {
        return R.layout.activity_foot_print;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        mpullLoadMoreRecyclerView= (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) findViewById(R.id.layout_no_datas);
        tvTitle.setText("我的足迹");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setTextColor(Color.parseColor("#333333"));
        tvRightBlue.setText("编辑");
        tvLeftBlue.setText("取消");

        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setLinearLayout();
        mpullLoadMoreRecyclerView.setRefreshing(true);
//        mpullLoadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FootPrintAdapter(this,index);
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.getDataFromServer(p);
    }

    @Override
    public void initUiAndListener() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvRightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index==0){
                    index=1;
                    tvRightBlue.setText("删除");
                    tvRightBlue.setTextColor(Color.parseColor("#ff7722"));
                    imgBack.setVisibility(View.GONE);
                    tvLeftBlue.setVisibility(View.VISIBLE);

                }else {
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < beans.size(); i++) {
                        for (int j = 0; j <  beans.get(i).get_goods_list().size() ; j++) {
                            if ( beans.get(i).get_goods_list().get(j).getInchenk()){
                                if (sb.length()!=0){
                                    sb.append(",");
                                }
                                sb.append( beans.get(i).get_goods_list().get(j).getGoods_id());
                            }

                        }

                    }
            if (!TextUtils.isEmpty(sb.toString())){

                    mPresenter.delUserBrowse(sb.toString());
                    index=0;
                    tvRightBlue.setText("编辑");
                    tvRightBlue.setTextColor(Color.parseColor("#333333"));
                    imgBack.setVisibility(View.VISIBLE);
                    tvLeftBlue.setVisibility(View.GONE);
            }else {
                ToastUtil.showToast("请选择商品");
            }
                }
                adapter.addIndex(index);
            }
        });
        tvLeftBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=1;
                tvRightBlue.setText("编辑");
                tvRightBlue.setTextColor(Color.parseColor("#333333"));
                imgBack.setVisibility(View.VISIBLE);
                tvLeftBlue.setVisibility(View.GONE);
                adapter.addIndex(index);
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

    @Override
    public void showFragment(int position) {

    }

    @Override
    public void showLoginUi() {

    }

    @Override
    public void updatePraiseView(int dif, int position) {

    }
    int index = 0;
    List<FootPrintBean.DataBean> beans = new ArrayList<>();
    @Override
    public void getOrderData(FootPrintBean beass) {
        if (p==1){
            beans.clear();
        }

        beans.addAll(beass.getData());
        adapter.addAllData(beans,index);
       if (beans.size()==0){
           layout_no_datas.setVisibility(View.VISIBLE);
           mpullLoadMoreRecyclerView.setVisibility(View.GONE);
       }else {
           layout_no_datas.setVisibility(View.GONE);
           mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
       }
    }

    @Override
    public void PullLoadMoreComplete() {
        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void delUserBrowseSucceed() {
        p=1;
        mPresenter.getDataFromServer(p);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getDataFromServer(p);
    }
}
