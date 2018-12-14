package com.lnkj.privateshop.ui.mybuy.sttting.paypwd;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.TimeCountUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChagePayPwdActivity extends BaseActivity implements ChagePayPwdContract.View{

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tip_logo_view)
    ImageView tipLogoView;
    @Bind(R.id.edit_account)
    EditText editAccount;
    @Bind(R.id.edit_number)
    EditText editNumber;
    @Bind(R.id.btn_number)
    Button btnNumber;
    @Bind(R.id.et_pay_pwd)
    EditText etPayPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.et_pay_pwd_to)
            EditText et_pay_pwd_to;
    ChagePayPwdPresenter mPresenter = new ChagePayPwdPresenter(this);
    @Override
    public int initContentView() {
        return R.layout.activity_chage_pay_pwd;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("设置支付密码");
        mPresenter.getToken(token);
    }

    @Override
    public void initUiAndListener() {

    }



    @OnClick({R.id.img_back, R.id.btn_number, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_number:
                mPresenter.sendCode(editAccount.getText().toString());
                break;
            case R.id.btn_login:
                mPresenter.upladData(editAccount.getText().toString(),editNumber.getText().toString(),etPayPwd.getText().toString(),et_pay_pwd_to.getText().toString());
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
    public void toLoging() {

    }

    @Override
    public void upladSuccreed() {
         PreferencesUtils.putInt(this,"isPay_password",1);
        finish();
    }


    TimeCountUtil timeCountUtil;
    @Override
    public void sendCodeSuccreed() {
        timeCountUtil = new TimeCountUtil(this, 60000, 1000,btnNumber);
        timeCountUtil.start();
    }
}
