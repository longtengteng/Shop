package com.lnkj.privateshop.ui.shop.shopclass.shopclassddetailed;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderViewPagerAdapter;
import com.lnkj.privateshop.ui.shop.shopclass.shopclassddetailed.fragment.ShopClasssFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShopClassDetActivity extends BaseActivity  {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private String goods_class_id;
    private String shop_id;
    private String goods_class_name;
    private int p=0;
    private String sort="add_time";

//    ShopClassDetPresenter mPresenter = new ShopClassDetPresenter(this,this);
//    private ShopClass_listAdapter classAdapter;
//    private ShopClass_listAdapter classAdaptertwo;


    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private ShopClasssFragment allFragment;
    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapterv ;

    @Override
    public int initContentView() {
        return R.layout.activity_shop_class_det;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        goods_class_id = getIntent().getStringExtra("goods_class_id");
        shop_id = getIntent().getStringExtra("shop_id");
        goods_class_name = getIntent().getStringExtra("goods_class_name");
        tvTitle.setText(goods_class_name);
//        mPresenter.setToken(token);
        titeList= new ArrayList<>();
        fragmentList=new ArrayList<>();
        adapterv = new OrderViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapterv);
        tabLayout.setupWithViewPager(viewpager);
        titeList.add("新品");
        titeList.add("收藏量");
        titeList.add("价格升序");
        titeList.add("价格降序");
        for (int i = 0; i < titeList.size(); i++) {
            allFragment =   new ShopClasssFragment();
            Bundle hotBundle = new Bundle();
            hotBundle.putInt("index",i);
            hotBundle.putString("goods_class_id",goods_class_id);
            hotBundle.putString("shop_id",shop_id);
            allFragment.setArguments(hotBundle);
            fragmentList.add(allFragment);
        }
        adapterv.bind(fragmentList,titeList);




//
    }

    @Override
    public void initUiAndListener() {

    }





    @OnClick({R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

        }
    }


//    String[] GoodsClass ={"新品","收藏量"};
//    String[] GoodsPrice ={"价格升序","价格降序"};

    private PopupWindow mPopWindow;
    //    显示分类选择

//    private void showPopupWindowClass() {
//        final Drawable drawable = getResources().getDrawable(R.mipmap.screen_icon_down);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        //设置contentView
//        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_class_layout_seach_goods, null);
//        mPopWindow = new PopupWindow(contentView,
//                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
//        mPopWindow.setContentView(contentView);
//        mPopWindow.setOutsideTouchable(true);
//        mPopWindow.setFocusable(true);
//        //让pop可以点击外面消失掉
//        mPopWindow.setBackgroundDrawable(new ColorDrawable(0));
//        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                tvClass.setTextColor(Color.parseColor("#000000"));
//                tvClass.setCompoundDrawables(null, null, drawable, null);
//            }
//        });
//        ListView lv_left = (ListView) contentView.findViewById(R.id.lv_left);
//        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i==0){
//                    sort="price_asc";
//                }else if (i==1){
//                    sort="price_desc";
//                }
//                classAdapter.setCheckedPosition(i);
//                p=1;
//                lists.clear();
//                tvClass.setText(GoodsClass[i]);
//                mPresenter.getShopClass(shop_id,sort,goods_class_id ,p);
//                if (mPopWindow != null && mPopWindow.isShowing()) {
//                    mPopWindow.dismiss();
//                    tvClass.setTextColor(Color.parseColor("#000000"));
//                    tvClass.setCompoundDrawables(null, null, drawable, null);
//                }
//            }
//        });
//        classAdapter.addData(GoodsClass);
//        lv_left.setAdapter(classAdapter);
//        //显示PopupWindow
//        mPopWindow.showAsDropDown(tvClass, Gravity.BOTTOM, 0);
//    }
//    private void showPopupWindowTime() {
//        final Drawable drawable = getResources().getDrawable(R.mipmap.screen_icon_down);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        //设置contentView
//        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_class_layout_seach_goods, null);
//        mPopWindow = new PopupWindow(contentView,
//                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
//        mPopWindow.setContentView(contentView);
//        mPopWindow.setOutsideTouchable(true);
//        mPopWindow.setFocusable(true);
//        //让pop可以点击外面消失掉
//        mPopWindow.setBackgroundDrawable(new ColorDrawable(0));
//        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                tvSorting.setTextColor(Color.parseColor("#000000"));
//                tvSorting.setCompoundDrawables(null, null, drawable, null);
//            }
//        });
//        ListView lv_left = (ListView) contentView.findViewById(R.id.lv_left);
//        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i==0){
//                    sort="add_time";
//                }else if (i==1){
//                    sort="collect";
//                }
//                classAdapter.setCheckedPosition(i);
//                p=1;
//                lists.clear();
//                tvSorting.setText(GoodsPrice[i]);
//                mPresenter.getShopClass(shop_id,sort,goods_class_id ,p);
//                if (mPopWindow != null && mPopWindow.isShowing()) {
//                    mPopWindow.dismiss();
//                    tvSorting.setTextColor(Color.parseColor("#000000"));
//                    tvSorting.setCompoundDrawables(null, null, drawable, null);
//                }
//            }
//        });
//        classAdaptertwo.addData(GoodsPrice);
//        lv_left.setAdapter(classAdaptertwo);
//        //显示PopupWindow
//        mPopWindow.showAsDropDown(tvClass, Gravity.BOTTOM, 0);
//    }
}
