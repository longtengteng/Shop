package com.lnkj.privateshop.ui.mybuy.sell.order.refunds;

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
import com.lnkj.privateshop.fragment.user.sell.refunds.SellRefundsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SellReturnActivity extends BaseActivity {
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
    private SellRefundsFragment allFragment;
    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapter ;

    @Override
    public int initContentView() {
        return R.layout.activity_sell_return;
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
        titeList.add("等待我处理");
        titeList.add("等待买家处理");
        titeList.add("退款结束");
        for (int i = 0; i < titeList.size(); i++) {
            allFragment =   new SellRefundsFragment();
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
