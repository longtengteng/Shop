package com.lnkj.privateshop.ui.mybuy.sell.order.money;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.SellUserBean;
import com.lnkj.privateshop.ui.mybuy.mredund.recharge.RechargeActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.money.bankcard.BankCardActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.money.detail.DetailActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.money.paydeposit.PayDepositActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.money.withdrawals.WithdrawalsActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资金管理
 */

public class MoneyActivity extends BaseActivity implements MoneyContract.View {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_mones)
    TextView tvMones;
    @Bind(R.id.tv_balance)
    TextView tvBalance;
    @Bind(R.id.tv_clearing)
    TextView tvClearing;
    @Bind(R.id.tv_income)
    TextView tvIncome;
    @Bind(R.id.tv_consum)
    TextView tvConsum;
    @Bind(R.id.tv_btn_look)
    TextView tvBtnLook;
    @Bind(R.id.btn_cash)
    Button btnCash;
    @Bind(R.id.btn_recharge)
    Button btnRecharge;
    MoneyPresenter mPresenter = new MoneyPresenter(this);
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.btn_bank_card)
    Button btnBankCard;

    @Override
    public int initContentView() {
        return R.layout.activity_money2;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        tvTitle.setText("资金管理");
      //  tvRightBlue.setText("诚信保证");
      //  tvRightBlue.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getDataFromServer();
    }

    @Override
    public void initUiAndListener() {

    }


    @OnClick({R.id.img_back, R.id.tv_btn_look, R.id.btn_cash, R.id.btn_recharge, R.id.btn_bank_card, R.id.tv_mones,R.id.tv_right_blue,R.id.ll_shouru,R.id.ll_xiaofei})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_btn_look:
                intent = new Intent(this, DetailActivity.class);
                startActivityForResult(intent, 20);
                break;
            case R.id.btn_cash:
                intent = new Intent(this, WithdrawalsActivity.class);
                startActivityForResult(intent, 20);

                break;
            case R.id.btn_recharge://充值
                intent = new Intent(this, RechargeActivity.class);
                startActivityForResult(intent, 20);
                break;
            case R.id.btn_bank_card:
                intent = new Intent(this, BankCardActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_mones:
                intent = new Intent(this, PayDepositActivity.class);
                intent.putExtra("shop_id", PreferencesUtils.getString(this, "shop_id"));
                startActivity(intent);
                break;
            case R.id.tv_right_blue:
                intent = new Intent(this, PayDepositActivity.class);
                intent.putExtra("shop_id", PreferencesUtils.getString(this, "shop_id"));
                startActivity(intent);
                break;
            case R.id.ll_shouru:
                intent = new Intent(this, DetailActivity.class);
                intent.putExtra("type","shouru");
                startActivityForResult(intent, 20);
                break;
            case R.id.ll_xiaofei:
                intent = new Intent(this, DetailActivity.class);
                intent.putExtra("type","zhichu");
                startActivityForResult(intent, 20);
                break;
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
    public void setData(String consume, String income, String settlement, String user_money) {
        tvConsum.setText(consume);
        tvIncome.setText(income);
        tvClearing.setText(settlement);
        tvBalance.setText(user_money);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getDeposit();
    }

    @Override
    public void getDepositSuccreed(SellUserBean bean) {
        int is_pay_bond = bean.getData().getIs_pay_bond();
        if (is_pay_bond == 0) {
            tvMones.setVisibility(View.GONE);
        } else {
            tvMones.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == 30) {
//            mPresenter.getDataFromServer();
        }
    }



}
