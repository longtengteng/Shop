package com.lnkj.privateshop.ui.mybuy.sell.order.appraiseadmin;


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
import com.lnkj.privateshop.fragment.user.sell.comment.TotalCommentFragment;
import com.lnkj.privateshop.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentAdminActivity extends BaseActivity implements CommentAdminContract.View{
    CommentAdminPresenter mPresenter = new CommentAdminPresenter(this,this);
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

    @Bind(R.id.tv_mass)
    TextView tvMass;



    @Bind(R.id.tv_service)
    TextView tvService;


    @Bind(R.id.rb_rate_velocity)
    RatingBar rbRateVelocity;
    @Bind(R.id.rb_rate_ervice)
    RatingBar rbRateErvice;
    @Bind(R.id.rc_rate_mass)
    RatingBar rcRateMass;


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
    TotalCommentFragment mFragment;
    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapter ;
    private String shop_id;


    @Override
    public int initContentView() {
        return R.layout.activity_comment_admin;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("店铺评价");
        mPresenter.setToken(token);
        shop_id = PreferencesUtils.getString(this,"shop_id");
        mPresenter.getShomComment(shop_id,1);
    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    public void onEmpty() {

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
        ShowCommentBean.DataBean ShopInfo =  beans.getData();
        if (ShopInfo!=null){
            tvGoodComment.setText(ShopInfo.getFavorableRate()+"%");
            tvCountComment.setText(ShopInfo.getCommentCount()+"条评论");


            tvMass.setText(ShopInfo.getGoods_rank()+"");
            rcRateMass.setRating((Float.parseFloat(ShopInfo.getGoods_rank()+"")));

            tvService.setText(ShopInfo.getService_rank()+"");
            rbRateErvice.setRating(Float.parseFloat(ShopInfo.getService_rank()+""));

            tvVelocity.setText(ShopInfo.getExpress_rank()+"");
            rbRateVelocity.setRating(Float.parseFloat(ShopInfo.getExpress_rank()+""));

            titeList = new ArrayList<>();
            fragmentList = new ArrayList<>();
            titeList.add("等待我回评\n( "+ShopInfo.getReviewCount()+" )");
            titeList.add("全部评价\n( "+ShopInfo.getCommentCount()+" )");
            for (int i = 0; i < titeList.size(); i++) {
                mFragment = TotalCommentFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putInt("index",i);
                bundle.putString("shopID",shop_id);
                mFragment.setArguments(bundle);
                fragmentList.add(mFragment);
            }
            adapter = new OrderViewPagerAdapter(getSupportFragmentManager());
            viewpager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewpager);
            adapter.bind(fragmentList,titeList);
        }
    }

    @Override
    public void addTabData(ShowCommentBean beans) {
        ShowCommentBean.DataBean ShopInfo =  beans.getData();
        titeList.clear();
        titeList.add("等待我回评\n( "+ShopInfo.getReviewCount()+" )");
        titeList.add("全部评价\n( "+ShopInfo.getCommentCount()+" )");
        adapter.notifyDataSetChanged();
    }

    public void getDataFromServer(){
        mPresenter.getShomComment(shop_id,2);
    }
}
