package com.lnkj.privateshop.ui.login.register;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
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
    @Bind(R.id.tip_logo_view)
    ImageView tipLogoView;
    @Bind(R.id.edit_account)
    EditText editAccount;
    @Bind(R.id.edit_pwd)
    EditText editPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.edit_nick)
    EditText edit_nick;
    @Bind(R.id.v_nick)
    View v_nick;

    @Inject
    RegisterPresenter Presenter;


    @Override
    public int initContentView() {
        return R.layout.activity_register;
    }

    @Override
    public void initInjector() {
        DaggerRegisterComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    @Override
    public void initUiAndListener() {
        Presenter.attachView(this);
        ButterKnife.bind(this);
        Presenter.initView();
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void initView() {
        String flag = getIntent().getStringExtra("flag");
        Presenter.flag = flag;
        Presenter.phone = getIntent().getStringExtra("phone");

        if (flag.equals(Constants.FINDPWD)) {
            btnLogin.setText("确定");
            edit_nick.setVisibility(View.GONE);
            v_nick.setVisibility(View.GONE);
        } else {
            btnLogin.setText("注册");
           // edit_nick.setVisibility(View.VISIBLE);
          //  v_nick.setVisibility(View.VISIBLE);

        }
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
    public void toLogIn() {
        LoginActivity.statitActivity(this);
        finish();
    }


    @Override
    public void setTitel(String s) {
        tvTitle.setText(s);
    }

    @Override
    public void countDown() {

    }

//    public static void statitAcitvity(Context context){
//    context.startActivity(new Intent(context,RegisterActivity.class));
//    }


    @OnClick({R.id.img_back, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_login:
             //   Presenter.next(editAccount.getText().toString(), editPwd.getText().toString(), edit_nick.getText().toString());
                Presenter.next(editAccount.getText().toString(), editPwd.getText().toString());
                break;
        }
    }

}
