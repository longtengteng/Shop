package com.lnkj.privateshop.ui.mybuy.sell.order;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderViewPagerAdapter;
import com.lnkj.privateshop.entity.OrderAllBean;
import com.lnkj.privateshop.fragment.user.sell.order.OredrFragment;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.ui.ease.EaseConversationListActivity;
import com.lnkj.privateshop.ui.mybuy.feedback.BeedBackActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.seach.SeachOrderActivity;
import com.lnkj.privateshop.view.ProgreesDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SellOrderActivity extends BaseActivity implements SellOrderContract.View{
    SellOrderPresenter mPresenter = new SellOrderPresenter(this);
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
    @Bind(R.id.iv_right)
    ImageView iv_right;
    @Bind(R.id.iv_sheach)
    ImageView iv_sheach;
    private OredrFragment allFragment;
    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapter ;
    private String  type;
    @Override
    public int initContentView() {
        return R.layout.activity_sell_order;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        ProgreesDialog.setDialog(this);
        iv_right.setVisibility(View.VISIBLE);
        iv_sheach.setVisibility(View.VISIBLE);
        iv_right.setImageResource(R.mipmap.order_ico_more);
        tvTitle.setText("我的订单");
        mPresenter.getToken(token);
        titeList= new ArrayList<>();
        fragmentList=new ArrayList<>();
        type=getIntent().getStringExtra("type");
        adapter = new OrderViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        mPresenter.getDataFromServer(1);
    }

    @Override
    public void initUiAndListener() {
        iv_sheach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(SellOrderActivity.this, SeachOrderActivity.class);
                startActivity(intent);
            }
        });
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow();
            }
        });
    }
    private PopupWindow mPopWindow;
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_item, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(iv_right, Gravity.BOTTOM, 20);
        TextView item_add = (TextView) contentView.findViewById(R.id.item_add);
        TextView tv_message = (TextView) contentView.findViewById(R.id.tv_message);
        TextView tv_shear = (TextView) contentView.findViewById(R.id.tv_shear);
        TextView tv_count = (TextView) contentView.findViewById(R.id.tv_count);
        if (UnreadMsgCount > 0) {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(UnreadMsgCount+ "");
        }else {
            tv_count.setVisibility(View.GONE);
        }
        tv_shear.setText("我要反馈");
        tv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellOrderActivity.this, EaseConversationListActivity.class);
                startActivity(intent);
            }
        });
        item_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellOrderActivity.this, MainActivity.class);
                intent.putExtra("type", "home");
                startActivity(intent);
            }
        });
        tv_shear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellOrderActivity.this, BeedBackActivity.class);
                startActivity(intent);
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
    public void showLoading() {
        ProgreesDialog.ProgeesDialogShow();
    }

    @Override
    public void hideLoading() {
        ProgreesDialog.ProgeesDialogDiss();
    }

    @Override
    public void initView() {

    }

    @Override
    public void showFragment(int position) {

    }

    @Override
    public void showLoginUi() {

    }

    @Override
    public void updatePraiseView(int dif, int position) {

    }

    @Override
    public void getOrderData(OrderAllBean beass) {
        int status0 = beass.getData().getOrder_count().getStatus_0();
        int status1 = beass.getData().getOrder_count().getStatus_1();
        int status2 = beass.getData().getOrder_count().getStatus_2();
        int status3 = beass.getData().getOrder_count().getStatus_3();
        int status4 = beass.getData().getOrder_count().getStatus_4();
        titeList.add("全部\n( "+status0+" )");
        titeList.add("待付款\n( "+status1+" )");
        titeList.add("待发货\n( "+status2+" )");
        titeList.add("待收货\n( "+status3+" )");
        titeList.add("待评价\n( "+status4+" )");

        for (int i = 0; i < titeList.size(); i++) {
            allFragment =   new OredrFragment();
            Bundle hotBundle = new Bundle();
            hotBundle.putSerializable("index",i);
            allFragment.setArguments(hotBundle);
            fragmentList.add(allFragment);
        }
        adapter.bind(fragmentList,titeList);
        if (!TextUtils.isEmpty(type)){
            if (type.equals("payment")){
                viewpager.setCurrentItem(1);
            }else if (type.equals("delivery")){
                viewpager.setCurrentItem(2);
            }else if (type.equals("togoods")){
                viewpager.setCurrentItem(3);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPopWindow!=null&&mPopWindow.isShowing()){
            mPopWindow.dismiss();
        }

    }

    @Override
    public void addTabData(OrderAllBean beass) {
        int status0 = beass.getData().getOrder_count().getStatus_0();
        int status1 = beass.getData().getOrder_count().getStatus_1();
        int status2 = beass.getData().getOrder_count().getStatus_2();
        int status3 = beass.getData().getOrder_count().getStatus_3();
        int status4 = beass.getData().getOrder_count().getStatus_4();
        titeList.clear();

        titeList.add("全部\n( "+status0+" )");
        titeList.add("待付款\n( "+status1+" )");
        titeList.add("待发货\n( "+status2+" )");
        titeList.add("待收货\n( "+status3+" )");
        titeList.add("待评价\n( "+status4+" )");
        adapter.notifyDataSetChanged();
    }
    public void getDataFromServer(){
        mPresenter.getDataFromServer(2);
    }
    @OnClick(R.id.img_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==20){
//            if (resultCode==30){
//                mPresenter.getDataFromServer();
//            }
//        }
//    }

}
