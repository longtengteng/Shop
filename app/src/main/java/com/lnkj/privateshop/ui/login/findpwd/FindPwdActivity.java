package com.lnkj.privateshop.ui.login.findpwd;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.login.register.RegisterActivity;
import com.lnkj.privateshop.utils.TimeCountUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPwdActivity extends BaseActivity implements FindPwdContract.View {

    @Inject
    FindPwdPresenter findPwdPresenter;

    TimeCountUtil timeCountUtil;

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
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


    @Override
    public int initContentView() {
        return R.layout.activity_find_pwd;
    }

    @Override
    public void initInjector() {
        DaggerFindPwdComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    @Override
    public void initUiAndListener() {
        findPwdPresenter.attachView(this);
        ButterKnife.bind(this);
        findPwdPresenter.initView();
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void initView() {
        findPwdPresenter.flag = getIntent().getStringExtra("flag");
//
    }

    @Override
    public void showLoading() {
progressDialog.show();
    }

    @Override
    public void hideLoading() {
progressDialog.dismiss();
    }

    private static final String TAG = "FindPwdActivity";
    @Override
    public void toRegister() {
        String  flag = getIntent().getStringExtra("flag");
        Intent intetn = new Intent(FindPwdActivity.this, RegisterActivity.class);
        intetn.putExtra("flag",flag);
        intetn.putExtra("phone",editAccount.getText().toString());
        startActivity(intetn);
    }



    @Override
    public void setTitel(String s) {
        tvTitle.setText(s);
    }

    @Override
    public void countDown() {
        timeCountUtil = new TimeCountUtil(this, 60000, 1000,btnNumber );
        timeCountUtil.start();
    }



    @OnClick({R.id.img_back, R.id.btn_number, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_number:
                findPwdPresenter.getCode(editAccount.getText().toString());
                break;
            case R.id.btn_login:
                findPwdPresenter.next(editAccount.getText().toString(), editNumber.getText().toString());
                break;
        }
    }
}
