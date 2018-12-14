package com.lnkj.privateshop.ui.mybuy.refunds;

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
import com.lnkj.privateshop.fragment.user.refunds.ReturndsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 退款退货
 */

public class RefundsActivity extends BaseActivity {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private ReturndsFragment allFragment;
    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapter ;
    @Override
    public int initContentView() {
        return R.layout.activity_refunds;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("退货退款");
        titeList= new ArrayList<>();
        fragmentList=new ArrayList<>();
        //修改TabLayout底部导航条Indicator的长短
        adapter = new OrderViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public void initUiAndListener() {
        titeList.add("进行中");
        titeList.add("已结束");
        for (int i = 0; i < titeList.size(); i++) {
            allFragment =   new ReturndsFragment();
            Bundle hotBundle = new Bundle();
            hotBundle.putSerializable("index",i);
            allFragment.setArguments(hotBundle);
            fragmentList.add(allFragment);
        }
        adapter.bind(fragmentList,titeList);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
