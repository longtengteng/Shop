package com.lnkj.privateshop.ui.mybuy.sttting.editpwd;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.utils.TimeCountUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.ll_prent;
import static com.lnkj.privateshop.R.id.ll_to;

public class EditCashPwdActivity extends BaseActivity implements EditCashPwdContract.View {
    EditCashPwdPresenter mPresenter = new EditCashPwdPresenter(this);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.edit_account)
    EditText editAccount;
    @Bind(R.id.edit_number)
    EditText editNumber;
    @Bind(R.id.btn_number)
    Button btnNumber;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(ll_prent)
    LinearLayout llPrent;
    @Bind(R.id.et_yuan_pwd)
    EditText etYuanPwd;
    @Bind(R.id.et_new_pwd)
    EditText etNewPwd;
    @Bind(R.id.et_new_pwd_to)
    EditText etNewPwdTo;
    @Bind(R.id.button)
    Button button;

    @Bind(ll_to)
    LinearLayout llTo;

    @Override
    public int initContentView() {
        return R.layout.activity_edit_cash_pwd;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("修改提现密码");
        mPresenter.getToken(token);
        llTo.setVisibility(View.GONE);
        llPrent.setVisibility(View.VISIBLE);
    }

    @Override
    public void initUiAndListener() {

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
    public void toLoging() {
        finish();
    }

    @Override
    public void upladSuccreed() {
        llPrent.setVisibility(View.GONE);
        llTo.setVisibility(View.VISIBLE);
    }

    TimeCountUtil timeCountUtil;

    @Override
    public void setWithdrawPasswordSuccreed() {
        timeCountUtil = new TimeCountUtil(this, 60000, 1000, btnNumber);
        timeCountUtil.start();

    }

    @OnClick({R.id.img_back, R.id.btn_number, R.id.btn_login, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_number:
                mPresenter.getCode(editAccount.getText().toString());
                break;
            case R.id.btn_login:
                mPresenter.upladData(editAccount.getText().toString(), editNumber.getText().toString());
                break;
            case R.id.button:
                mPresenter.setWithdrawPassword(etYuanPwd.getText().toString(), etNewPwd.getText().toString(), etNewPwdTo.getText().toString());
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
