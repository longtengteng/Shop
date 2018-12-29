package com.lnkj.privateshop.ui.mybuy.sell.order.goods;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderViewPagerAdapter;
import com.lnkj.privateshop.fragment.user.sell.goods.up.SelGoodsFragment;
import com.lnkj.privateshop.ui.addgoods.AddGoodsActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.goods.downgoods.DownGoodsActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.goods.upgoods.UpGoodsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellGoodsActivity extends BaseActivity implements SellGoodsContract.View {
    SellGoodsPresenter mPresenter = new SellGoodsPresenter(this);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapter;
    private SelGoodsFragment mFragment;
    private SelGoodsFragment mFragment2;

    @Override
    public int initContentView() {
        return R.layout.activity_sell_goods;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("商品管理");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setTextColor(Color.parseColor("#333333"));
        tvRightBlue.setText("编辑");
        mPresenter.getToken(token);
        titeList = new ArrayList<>();
        titeList.add("已上架");
        titeList.add("已下架");
        fragmentList = new ArrayList<>();

        Bundle bundle = new Bundle();
        bundle.putInt("index", 1);
        mFragment = SelGoodsFragment.newInstance();
        mFragment.setArguments(bundle);

        Bundle bundle1 = new Bundle();
        bundle1.putInt("index", 0);
        mFragment2 = SelGoodsFragment.newInstance();
        mFragment2.setArguments(bundle1);


        fragmentList.add(mFragment);
        fragmentList.add(mFragment2);

        adapter = new OrderViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        adapter.bind(fragmentList, titeList);

//        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tvRightBlue.setTextColor(Color.parseColor("#333333"));
//                tvRightBlue.setText("编辑");
//                imgBack.setVisibility(View.VISIBLE);
//                tvLeftBlue.setVisibility(View.GONE);
//                mFragment.addIndex(index);
//                mFragment.adapter.listisfalst();
//                mFragment2.adapter.listisfalst();
//                if (position==2){
//                    tvRightBlue.setVisibility(View.GONE);
//                }else {
//                    tvRightBlue.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
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

    }

    @Override
    public void fullData() {

    }

    @Override
    public void succeed() {
//        tvRightBlue.setText("编辑");
//        if (viewpager.getCurrentItem()!=2){
//            mFragment2.mPresenter.getDataFromService(0);
//            mFragment.mPresenter.getDataFromService(1);
//        }

    }

    @Override
    public void showFragment(int position) {

    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SellGoodsActivity.class);
        context.startActivity(intent);
    }

    private int index = 0;

    @OnClick({R.id.img_back, R.id.tv_right_blue, R.id.tv_left_blue, R.id.btn_up_new})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_blue:
//                if (index==0){
//                    tvRightBlue.setTextColor(Color.parseColor("#FF7722"));
//                    if (viewpager.getCurrentItem()==0){
//                        tvRightBlue.setText("下架");
//                    } else if (viewpager.getCurrentItem()==1){
//                        tvRightBlue.setText("上架");
//                    }else {
//
//                    }
//                    tvLeftBlue.setText("取消");
//                    imgBack.setVisibility(View.GONE);
//                    tvLeftBlue.setVisibility(View.VISIBLE);
//                    index=1;
//                }else {
//                    index=0;
//                    tvRightBlue.setTextColor(Color.parseColor("#333333"));
//                    imgBack.setVisibility(View.VISIBLE);
//                    tvLeftBlue.setVisibility(View.GONE);
//                    if (viewpager.getCurrentItem()==0){
//                        mPresenter.downSold(mFragment.adapter.getGoodsId(),"down");
//                    } else if (viewpager.getCurrentItem()==1){
//                        mPresenter.downSold(mFragment2.adapter.getGoodsId(),"up");
//                    }else {
//
//                    }
//                }
                if (viewpager.getCurrentItem() == 0) {
//                mFragment.addIndex(index);
                    startActivity(new Intent(this, UpGoodsActivity.class));
                } else if (viewpager.getCurrentItem() == 1) {
//                    mFragment2.addIndex(index);
                    startActivity(new Intent(this, DownGoodsActivity.class));
                }
                break;
            case R.id.btn_up_new:
                startActivity(new Intent(this, AddGoodsActivity.class));
                break;
//            case R.id.tv_left_blue:
//                index=0;
//                mFragment.adapter.listisfalst();
//                mFragment2.adapter.listisfalst();
//                tvRightBlue.setTextColor(Color.parseColor("#333333"));
//                tvRightBlue.setText("编辑");
//                imgBack.setVisibility(View.VISIBLE);
//                tvLeftBlue.setVisibility(View.GONE);
//                mFragment.addIndex(index);
//                break;
        }
    }


}
