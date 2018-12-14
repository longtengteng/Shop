package com.lnkj.privateshop.ui.mybuy.sttting.chagepwd;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */

public class ChagePwdActivity extends BaseActivity implements ChagePwdContract.View{


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
    @Bind(R.id.edit_account)
    EditText editAccount;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.et_to_pwd)
    EditText etToPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    ChagePwdPresenter mPresenter = new ChagePwdPresenter(this);
    @Override
    public int initContentView() {
        return R.layout.activity_chage_pwd;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("修改密码");
        mPresenter.getToken(token);

    }

    @Override
    public void initUiAndListener() {

    }

    @OnClick({R.id.img_back, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_login:
                mPresenter.upladData(editAccount.getText().toString(),etPwd.getText().toString(),etToPwd.getText().toString());
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
        Intent intent= new Intent(ChagePwdActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
