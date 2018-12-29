package com.lnkj.privateshop.ui.shop.shopInfo;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.domain.EaseUser;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderViewPagerAdapter;
import com.lnkj.privateshop.chat.ChatActivity;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.entity.ShopArchivesBean;
import com.lnkj.privateshop.entity.ShopBean;
import com.lnkj.privateshop.fragment.shop.shopinfo.Membersragment;
import com.lnkj.privateshop.fragment.shop.shopinfo.MerchandiseFragment;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.ui.ease.EaseConversationListActivity;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.ui.mybuy.feedback.BeedBackActivity;
import com.lnkj.privateshop.ui.shop.ShopCommentActivity;
import com.lnkj.privateshop.ui.shop.shoparchives.ShopArchivesActivity;
import com.lnkj.privateshop.ui.shop.shopclass.ShopClassActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * 店铺详情
 */
public class ShopInfoActivity extends BaseActivity implements ShopInfoContract.View {
    ShopInfoPresenter mPresenter = new ShopInfoPresenter(this, this);
    @Bind(R.id.iv_shop_bj)
    ImageView ivShopBj;
    @Bind(R.id.img_shop_head)
    CircleImageView imgShopHead;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_follow)
    TextView tvFollow;
    @Bind(R.id.tv_address)
    TextView tvAddress;
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
    @Bind(R.id.tv_context)
    TextView tvContext;
    ImageView ivMenu;
    @Bind(R.id.rl_bar)
    RelativeLayout rl_bar;
    @Bind(R.id.rl_bar_to)
    RelativeLayout rl_bar_to;
    @Bind(R.id.img_beak)
    ImageView img_beak;
    @Bind(R.id.img_beak_to)
    ImageView img_beak_to;
    private String shop_id;
    MerchandiseFragment mFragment;
    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapter;
    private ShopBean.DataBean shopinfo;
    private String type;

    @Override
    public int initContentView() {
        return R.layout.activity_shop_info;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        ivMenu = (ImageView) findViewById(R.id.img_menu);
        shop_id = getIntent().getStringExtra("shop_id");
        type = getIntent().getStringExtra("type");

        mPresenter.setToken(token);
        mPresenter.getShomInfo(shop_id);
        mPresenter.getShopArchives(shop_id);

        titeList = new ArrayList<>();
        fragmentList = new ArrayList<>();


        adapter = new OrderViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public void finsh() {
        ToastUtil.showToast("店铺不存在");
    }

    @Override
    public void initUiAndListener() {
        img_beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_beak_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        idAppbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int scrollRangle = appBarLayout.getTotalScrollRange();
                //初始verticalOffset为0，不能参与计算。
                if (verticalOffset == 0) {
                    rl_bar.setAlpha(0.0f);
                    rl_bar_to.setVisibility(View.VISIBLE);
                    rl_bar.setVisibility(View.GONE);
                } else {
                    //保留一位小数
                    float alpha = Math.abs(Math.round(1.0f * verticalOffset / scrollRangle) * 10) / 10;
                    rl_bar.setAlpha(alpha);
                    if (alpha > 0.1) {
                        rl_bar_to.setVisibility(View.GONE);
                        rl_bar.setVisibility(View.VISIBLE);
                    } else {
                        rl_bar_to.setVisibility(View.VISIBLE);
                        rl_bar.setVisibility(View.GONE);
                    }
//                    System.out.println("__________");
//                    System.out.println(Math.abs(Math.round(1.0f * verticalOffset / scrollRangle) * 10) / 10);
                }
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
    public void getShomInfoSucceed(ShopBean beans) {

        shopinfo = beans.getData();


        tvShopName.setText(shopinfo.getShop_name());
        Glide
                .with(this)
                .load(Constants.Base_URL + shopinfo.getShop_logo())
                .error(R.mipmap.bg_img)
                .into(imgShopHead);
        Glide
                .with(this)
                .load(Constants.Base_URL + shopinfo.getShop_real_pic())
                .error(R.mipmap.bg_img)
                .into(ivShopBj);
        if (TextUtils.isEmpty(shopinfo.getAddress())) {
            tvAddress.setText(shopinfo.getProvince() + "·" + shopinfo.getCity());
        } else {
            tvAddress.setText(shopinfo.getProvince() + "·" + shopinfo.getCity() + "·" + shopinfo.getAddress());
        }
        if (shopinfo.getHas_focus() == 0) {
            tvFollow.setText("+关注");
        } else {
            tvFollow.setText("已关注");
            tvFollow.setClickable(false);
        }
        if (TextUtils.isEmpty(shopinfo.getNotice())) {
            tvContext.setVisibility(View.GONE);
        } else {
            tvContext.setVisibility(View.VISIBLE);
        }
        tvContext.setText(shopinfo.getNotice());
        for (int i = 0; i < beans.getData().getCat_info().size(); i++) {
            titeList.add(beans.getData().getCat_info().get(i).getCat_name());
        }
        for (int i = 0; i < titeList.size(); i++) {
            mFragment = MerchandiseFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            bundle.putString("cat_id", beans.getData().getCat_info().get(i).getCat_id());
            mFragment.setArguments(bundle);
            fragmentList.add(mFragment);
        }
        adapter.bind(fragmentList, titeList);
    }


    @Override
    public void SetColloectShopSuccreed() {
        if (shopinfo.getHas_focus() == 0) {
            shopinfo.setHas_focus(1);
            tvFollow.setText("已关注");
        } else {
            tvFollow.setText("+关注");
            shopinfo.setHas_focus(0);
        }
    }

    @Override
    public void getShopArchivesSucceed(ShopArchivesBean beans) {
    }


    @OnClick({R.id.tv_follow,
            R.id.img_goods_car, R.id.img_menu, R.id.img_goods_car_to, R.id.img_menu_to})
    public void onViewClicked(View view) {
        Intent intent;
        Boolean is_bogin = PreferencesUtils.getBoolean(this, "is_bogin");

        switch (view.getId()) {
            case R.id.img_goods_car_to:
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("type", "addshop");
                startActivity(intent);
                break;
            case R.id.img_menu_to:
                if (!is_bogin) {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                    return;
                }
                showPopupWindow(findViewById(R.id.img_menu_to));
                break;
            case R.id.img_menu:
                if (!is_bogin) {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                    return;
                }
                showPopupWindow(ivMenu);
                break;
            case R.id.img_goods_car:
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("type", "addshop");
                startActivity(intent);
                break;

            case R.id.tv_follow:
                if (!is_bogin) {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                    return;
                }
                mPresenter.setCollectShop(shop_id);
                break;
        }
    }

    private PopupWindow mPopWindow;

    private void showPopupWindow(View v) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_item, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(v, Gravity.BOTTOM, 20);
        TextView item_add = (TextView) contentView.findViewById(R.id.item_add);
        TextView tv_message = (TextView) contentView.findViewById(R.id.tv_message);
        TextView tv_feedback = (TextView) contentView.findViewById(R.id.tv_feedback);
        tv_feedback.setVisibility(View.VISIBLE);
        tv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopInfoActivity.this, EaseConversationListActivity.class);
                startActivity(intent);
            }
        });
        item_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopInfoActivity.this, MainActivity.class);
                intent.putExtra("type", "home");
                startActivity(intent);
            }
        });
        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopWindow != null) {
                    mPopWindow.dismiss();
                }
                Intent intent = new Intent(ShopInfoActivity.this, BeedBackActivity.class);
                startActivity(intent);
            }
        });
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (Exception e) {
        }
    }
}
