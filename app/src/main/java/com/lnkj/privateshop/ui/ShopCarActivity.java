package com.lnkj.privateshop.ui;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.fragment.goodscar.GoodsFragment;

public class ShopCarActivity extends BaseActivity {
    private GoodsFragment goodsFragment;

    @Override
    public int initContentView() {
        return R.layout.activity_shopcar;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initUiAndListener() {
        try {
            goodsFragment = new GoodsFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, goodsFragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
