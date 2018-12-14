package com.lnkj.privateshop.ui.mybuy.sell.order.money.detail;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderViewPagerAdapter;
import com.lnkj.privateshop.fragment.user.sell.lookredund.SellLookRedundFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资金明细
 */
public class DetailActivity extends BaseActivity  {
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
    private SellLookRedundFragment mFragment;
    private OrderViewPagerAdapter adapter ;
private String type;
    @Override
    public int initContentView() {
        return R.layout.activity_detail;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        tvTitle.setText("明细");
        titeList= new ArrayList<>();
        fragmentList=new ArrayList<>();
        titeList.add("全部");
        titeList.add("累计收入");
        titeList.add("累计消费");
        adapter = new OrderViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

        for (int i = 0; i < titeList.size(); i++) {
            mFragment =   new SellLookRedundFragment();
            Bundle hotBundle = new Bundle();
            hotBundle.putInt("index",i);
            mFragment.setArguments(hotBundle);
            fragmentList.add(mFragment);
        }
        adapter.bind(fragmentList,titeList);
        if (!TextUtils.isEmpty(type)){
            if (type.equals("shouru")){
                viewpager.setCurrentItem(1);
            }else if (type.equals("zhichu")){
                viewpager.setCurrentItem(2);
            }
        }
    }

    @Override
    public void initUiAndListener() {

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }



}
