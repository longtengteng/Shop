package com.lnkj.privateshop.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.ui.login.findpwd.FindPwdActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginPresenter loginPresenter;
    //    @Bind(R.id.tip_logo_view)
//    TextView tipLogoView;
    @Bind(R.id.edit_account)
    EditText editAccount;
    @Bind(R.id.edit_pwd)
    EditText editPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_find_pwd)
    TextView tvFindPwd;
    @Bind(R.id.tv_register)
    TextView tvRegister;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_left_blue)
    TextView tv_left_blue;
    String type;

    @Override
    public int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        loginPresenter.attachView(this);
        imgBack.setVisibility(View.VISIBLE);
        type = getIntent().getStringExtra("type");
        // tv_left_blue.setVisibility(View.VISIBLE);
        // tv_left_blue.setText("取消");
        // tv_left_blue.setTextColor(Color.parseColor("#ff7722"));
        loginPresenter.initView();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(type)) {
                    finish();
                } else {
                    Intent inent = new Intent(LoginActivity.this, MainActivity.class);
                    inent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(inent);
//                    MainActivity.startAcitity(LoginActivity.this);
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
    public void toMain() {
        Intent inent = new Intent(LoginActivity.this, MainActivity.class);
        inent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(inent);
        finish();
    }

    @Override
    public void setTitle(String s) {
        tvTitle.setText(s);

    }


    public static void statitActivity(Context context) {

        context.startActivity(new Intent(context, LoginActivity.class));
    }


    @OnClick({R.id.btn_login, R.id.tv_find_pwd, R.id.tv_register})
    public void onViewClicked(View view) {
        Intent intent = new Intent(LoginActivity.this, FindPwdActivity.class);
        switch (view.getId()) {
            case R.id.btn_login:
                loginPresenter.login(editAccount.getText().toString(), editPwd.getText().toString());
                break;
            case R.id.tv_find_pwd:
                intent.putExtra("flag", Constants.FINDPWD);
                startActivity(intent);
                break;
            case R.id.tv_register:
                intent.putExtra("flag", Constants.REGISTE);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (TextUtils.isEmpty(type)) {
            finish();
        } else {
            MainActivity.startAcitity(LoginActivity.this);
        }
    }
}
