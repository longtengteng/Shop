package com.lnkj.privateshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class OrderViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> tablist;
    private List<Fragment> fragmentlist;
    public OrderViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void bind( List<Fragment> fragmentlist, List<String> tablist){
        this.fragmentlist=fragmentlist;
        this.tablist=tablist;
        notifyDataSetChanged();

    }
    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {

        return fragmentlist==null?0:fragmentlist.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position);
    }
    public Fragment getFragment(int position) {
        return fragmentlist.get(position);
    }
}
