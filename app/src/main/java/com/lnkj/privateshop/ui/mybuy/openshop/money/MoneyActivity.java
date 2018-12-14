package com.lnkj.privateshop.ui.mybuy.openshop.money;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.payorder.PayOrderActivity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopFactory2Activity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopNetActivity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopRealityActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.tv_jiaona;

/**
 * 保证金
 */
public class MoneyActivity extends BaseActivity implements  MoneyContract.View{
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
    @Bind(R.id.tv_xian)
    TextView tvXian;
    @Bind(tv_jiaona)
    TextView tvJiaona;
    @Bind(R.id.btn_ok)
    Button btnOk;
    @Bind(R.id.btn_restart)
    Button btnRestart;
    MoneyPresenter mPresenter  = new MoneyPresenter(this);
    View prentview;
    @Override
    public int initContentView() {
        prentview=View.inflate(this,R.layout.activity_money,null);
        return R.layout.activity_money;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        mPresenter.getDataFromServer();
        tvTitle.setText("缴纳保证金");
        imgBack.setVisibility(View.GONE);
        tvLeftBlue.setVisibility(View.VISIBLE);
        tvLeftBlue.setText("关闭");
        tvLeftBlue.setTextColor(Color.parseColor("#27a2fb"));
        String shop_id = getIntent().getStringExtra("shop_id");
    }
    @Override
    public void initUiAndListener() {

    }
    @OnClick({R.id.tv_left_blue, R.id.btn_ok, R.id.btn_restart})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_left_blue:
                 intent = new Intent(this, MainActivity.class);
                intent.putExtra("type","openshop");
                startActivity(intent);

                break;
            case R.id.btn_ok:
                intent = new Intent(this,PayOrderActivity.class);
                intent.putExtra("price",shop_bond);
                intent.putExtra("matters","缴纳开店费");
                startActivity(intent);
                break;
            case R.id.btn_restart:
//                if (shop_bond.equals("1")){
                    popunWin = new PopupWindows(this, prentview);

//                }
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
                    Intent intent = new Intent(MoneyActivity.this, OpenShopNetActivity.class);
                    startActivity(intent);
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MoneyActivity.this, OpenShopRealityActivity.class);
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
                    Intent intent = new Intent(MoneyActivity.this, OpenShopFactory2Activity.class);
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

    }

    @Override
    public void hideLoading() {

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("type","openshop");
        startActivity(intent);

    }
    @Override
    public void initView() {

    }

    private String shop_bond;
    private String is_open;
    @Override
    public void getDepositSuccreed(String frozen_bond,String shop_bond, int shop_type,String is_open) {
        this.is_open=is_open;
        this.shop_bond =shop_bond;
        tvXian.setText(frozen_bond);
        tvJiaona.setText(shop_bond);
    }
}
