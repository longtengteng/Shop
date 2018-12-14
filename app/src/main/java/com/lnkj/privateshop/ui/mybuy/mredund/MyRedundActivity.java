package com.lnkj.privateshop.ui.mybuy.mredund;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.mybuy.mredund.look.LookRedundActivity;
import com.lnkj.privateshop.ui.mybuy.mredund.recharge.RechargeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的余额
 */

public class MyRedundActivity extends BaseActivity implements MyRedundContract.View {
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
    @Bind(R.id.tv_mones)
    TextView tvMones;
    @Bind(R.id.tv_main_mones)
    TextView tvMainMones;
    @Bind(R.id.tv_income)
    TextView tvIncome;
    @Bind(R.id.tv_consum)
    TextView tvConsum;
    @Bind(R.id.tv_look)
    TextView tvLook;
    @Bind(R.id.btn_toview)
    Button btnToview;
    View rootview;
    private MyRdeundPresenter mPresenter = new MyRdeundPresenter(this);

    @Override
    public int initContentView() {
        rootview=View.inflate(this,R.layout.activity_my_redund,null);
        return R.layout.activity_my_redund;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("我的余额");
        mPresenter.getToken(token);

    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getDataFromServer();
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
    public void setData(String mones, String mainmones, String income) {
        tvMainMones.setText(mones);
        tvConsum.setText(mainmones);
        tvIncome.setText(income);
    }


    @Override
    public void PullLoadMoreComplete() {

    }



    @OnClick({R.id.img_back, R.id.tv_mones, R.id.tv_look, R.id.btn_toview,R.id.ll_shouru,R.id.ll_xiaofei})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_mones:
                showPopupWindow();
                break;
            case R.id.tv_look:
                intent = new Intent(this, LookRedundActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_toview:
//                ToastUtil.showToast("充值");
                intent = new Intent(this, RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_shouru:
                intent = new Intent(this, LookRedundActivity.class);
                intent.putExtra("type","shouru");
                startActivity(intent);
                break;
            case R.id.ll_xiaofei:
                intent = new Intent(this, LookRedundActivity.class);
                intent.putExtra("type","xiaofei");
                startActivity(intent);
                break;
        }
    }
    private PopupWindow mPopWindow;
    //缴纳
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_pay_layout_tixing, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);

        TextView tv_zhifu = (TextView) contentView.findViewById(R.id.tv_zhifu);
        LinearLayout ll_back = (LinearLayout) contentView.findViewById(R.id.ll_back);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        tv_zhifu.setOnClickListener(new View.OnClickListener() {
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
}
