package com.lnkj.privateshop.ui.shop;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderViewPagerAdapter;
import com.lnkj.privateshop.entity.ShowCommentBean;
import com.lnkj.privateshop.fragment.shop.ShopCommentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCommentActivity extends BaseActivity implements ShopCommentContract.View {
    ShopCommentPresenter mPresenter = new ShopCommentPresenter(this, this);
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
    @Bind(R.id.tv_good_comment)
    TextView tvGoodComment;
    @Bind(R.id.tv_count_comment)
    TextView tvCountComment;
    @Bind(R.id.rc_rate_mass)
    RatingBar rcRateMass;
    @Bind(R.id.tv_mass)
    TextView tvMass;
    @Bind(R.id.rb_rate_ervice)
    RatingBar rbRateErvice;
    @Bind(R.id.tv_service)
    TextView tvService;
    @Bind(R.id.rb_rate_velocity)
    RatingBar rbRateVelocity;
    @Bind(R.id.tv_velocity)
    TextView tvVelocity;
    @Bind(R.id.rl_parent)
    LinearLayout rlParent;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.id_appbarlayout)
    AppBarLayout idAppbarlayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.scrollview)
    CoordinatorLayout scrollview;
    ShopCommentFragment mFragment;
    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapter;
    private String shop_id;

    @Override
    public int initContentView() {
        return R.layout.activity_shop_comment;

    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("店铺评价");
        mPresenter.setToken(token);
        shop_id = getIntent().getStringExtra("shop_id");

        mPresenter.getShomComment(shop_id);


    }

    @Override
    public void initUiAndListener() {

    }

    @OnClick({R.id.img_back, R.id.rc_rate_mass, R.id.rb_rate_ervice, R.id.rb_rate_velocity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rc_rate_mass:
                break;
            case R.id.rb_rate_ervice:
                break;
            case R.id.rb_rate_velocity:
                break;
        }
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

    @Override
    public void getShomCommentSucceed(ShowCommentBean beans) {
        ShowCommentBean.DataBean ShopInfo = beans.getData();
        if (ShopInfo != null) {
            tvGoodComment.setText(ShopInfo.getFavorableRate() + "%");
            tvCountComment.setText(ShopInfo.getCommentCount() + "条评论");

            tvMass.setText(ShopInfo.getGoods_rank() + "");
            rcRateMass.setRating((Float.parseFloat(ShopInfo.getGoods_rank() + "")));

            tvService.setText(ShopInfo.getService_rank() + "");
            rbRateErvice.setRating(Float.parseFloat(ShopInfo.getService_rank() + ""));

            tvVelocity.setText(ShopInfo.getExpress_rank() + "");
            rbRateVelocity.setRating(Float.parseFloat(ShopInfo.getExpress_rank() + ""));

            titeList = new ArrayList<>();
            fragmentList = new ArrayList<>();
            titeList.add("全部\n( " + ShopInfo.getCommentCount() + " )");
            titeList.add("好评\n( " + ShopInfo.getLikeCount() + " )");
            titeList.add("中评\n( " + ShopInfo.getMedCount() + " )");
            titeList.add("差评\n( " + ShopInfo.getLowCount() + " )");
            titeList.add("晒图\n( " + ShopInfo.getImgCount() + " )");
            for (int i = 0; i < titeList.size(); i++) {
                mFragment = ShopCommentFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                bundle.putString("shopID", shop_id);
                mFragment.setArguments(bundle);
                fragmentList.add(mFragment);
            }
            adapter = new OrderViewPagerAdapter(getSupportFragmentManager());
            viewpager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewpager);
            adapter.bind(fragmentList, titeList);
        }
    }
}
