package com.lnkj.privateshop.ui.mybuy.sell.order.money.paydeposit;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.mybuy.myorder.payorder.PayOrderActivity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopFactory2Activity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopNetActivity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopRealityActivity;
import com.lnkj.privateshop.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.tv_already;
import static com.lnkj.privateshop.R.id.tv_quota;

public class PayDepositActivity extends BaseActivity implements PayDepositContract.View{


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(tv_quota)
    TextView tvQuota;
    @Bind(tv_already)
    TextView tvAlready;
    @Bind(R.id.btn_pay)
    Button btnPay;
    @Bind(R.id.btn_defrosting)
    Button btnDefrosting;
    @Bind(R.id.btn_restart)
    Button btn_restart;
    private PopupWindow mPopWindow;
    View rootview;
    PayDepositPresenter mPresenter = new PayDepositPresenter(this);
    @Override
    public int initContentView() {
        rootview=View.inflate(this,R.layout.activity_pay_deposit,null);
        return R.layout.activity_pay_deposit;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getDataFromServer();
    }
    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("缴纳保证金");
        mPresenter.getToken(token);

    }

    @Override
    public void initUiAndListener() {

    }



    //缴纳
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_pay_layout, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        LinearLayout ll_dis = (LinearLayout) contentView.findViewById(R.id.ll_dis);
        TextView tv_jian = (TextView) contentView.findViewById(R.id.tv_jian);
        TextView tv_jia = (TextView) contentView.findViewById(R.id.tv_jia);
        TextView tv_titel = (TextView) contentView.findViewById(R.id.tv_titel);
        final TextView et_price = (TextView) contentView.findViewById(R.id.et_price);
        TextView tv_zhifu = (TextView) contentView.findViewById(R.id.tv_zhifu);
        tv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etString = et_price.getText().toString();

                if (TextUtils.isEmpty(etString)){
                    etString="0";

                }

                   int old=  Integer.parseInt(etString)+1000;
                    et_price.setText(old+"");
            }
        });
    tv_jian.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String etString = et_price.getText().toString();
            if (TextUtils.isEmpty(etString)){
                etString="0";
            }
            int old=  Integer.parseInt(etString)-1000;
            if (old<0){
                et_price.setText("0");
            }else {
                et_price.setText(old+"");
            }
        }
    });
        if (index==1){
            tv_zhifu.setText("解冻");
            tv_titel.setText("解冻诚信保证金");
        }else {
            tv_titel.setText("缴纳诚信保证金");
            tv_zhifu.setText("支付");
        }
    tv_zhifu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(et_price.getText().toString())||et_price.getText().toString().equals("0")){
                ToastUtil.showToast("请选择保证金额");
                return;
            }
            mPopWindow.dismiss();
            if (index==1){
              mPresenter.defrost(et_price.getText().toString());
            }else {
                Intent intent =  new Intent(PayDepositActivity.this,PayOrderActivity.class);
                intent.putExtra("price",et_price.getText().toString());
                intent.putExtra("matters","缴纳保证金");
                startActivity(intent);
            }

        }
    });
        ll_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
private int index=1;
    @OnClick({R.id.img_back, R.id.btn_pay, R.id.btn_defrosting,R.id.btn_restart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_pay:
                index=2;
                showPopupWindow();
                break;
            case R.id.btn_defrosting:
                index=1;
                showPopupWindow();
                break;
            case R.id.btn_restart:

                popunWin = new PopupWindows(PayDepositActivity.this, rootview);
            break;
        }
    }
    private PopupWindows popunWin;
    public class PopupWindows extends PopupWindow {
        public PopupWindows(Context mContext, View parent) {
            View view = View.inflate(mContext, R.layout.item_popupwindow, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view
                    .findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);


            setBackgroundDrawable(new BitmapDrawable());
//            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();

            Button bt1 = (Button) view
                    .findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view
                    .findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view
                    .findViewById(R.id.item_popupwindows_cancel);
            Button bt4 = (Button) view.findViewById(R.id.item_popupwindows_video);
            TextView tv_dis = (TextView) view.findViewById(R.id.tv_dis);
            tv_dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });

            bt1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //网批发店铺
                    Intent intent = new Intent(PayDepositActivity.this, OpenShopNetActivity.class);
                    startActivity(intent);
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(PayDepositActivity.this, OpenShopRealityActivity.class);
                    startActivity(intent);
                    dismiss();
                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });
            bt4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //工厂
                    Intent intent = new Intent(PayDepositActivity.this, OpenShopFactory2Activity.class);
                    startActivity(intent);
                    dismiss();

                }
            });
        }
    }
    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

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
    public void getOrderData(String frozen_bond, String shop_bond, String shop_type, int is_open) {
        tvQuota.setText(frozen_bond);
        tvAlready.setText(shop_bond);
        if (is_open==1){
            btn_restart.setVisibility(View.VISIBLE);
            btnDefrosting.setVisibility(View.GONE);
        }else {
            btn_restart.setVisibility(View.GONE);
            btnDefrosting.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void defrostSurcreed() {
        mPresenter.getDataFromServer();
    }
}
