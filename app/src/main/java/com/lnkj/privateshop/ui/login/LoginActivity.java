package com.lnkj.privateshop.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.lnkj.privateshop.ui.login.mob.LoginApi;
import com.lnkj.privateshop.ui.login.mob.OnLoginListener;
import com.lnkj.privateshop.ui.login.mob.UserInfo;
import com.lnkj.privateshop.utils.ToastUtil;
import com.mob.MobSDK;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

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

    @Bind(R.id.iv_zfb)
    ImageView iv_zfb;
    @Bind(R.id.iv_qq)
    ImageView iv_qq;
    @Bind(R.id.iv_wx)
    ImageView iv_wx;
    @Bind(R.id.iv_weibo)
    ImageView iv_weibo;

    String type;
    String login_type = "";

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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("login_type", login_type);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        login_type = savedInstanceState.getString("login_type");
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

    private void thirdLogin(String platformName) {

        MobSDK.init(this);
        final Platform platform = ShareSDK.getPlatform(platformName);
        platformName = platform.getName();
        LoginApi api = new LoginApi();
        //设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        api.setOnLoginListener(new OnLoginListener() {
            public boolean onLogin(String platformString, HashMap<String, Object> res) {
                // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
                // 此处全部给回需要注册
                PlatformDb platformDb = platform.getDb();
                loginPresenter.login_three(login_type, "", platformDb.getUserId(), platformDb.getUserName(), platformDb.getUserIcon());
                //      present.login_three(platformDb.getUserId(), login_type, platformDb.getUserName(), platformDb.getUserIcon());
                // LogUtils.e("userInfo", platformDb.getUserName() + platformDb.getUserIcon() + platformDb.getUserGender() + platformDb.getUserId());
                return false;
            }

            public boolean onRegister(UserInfo info) {
                // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
                return true;
            }
        });
        api.login(this);
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

    @Override
    public void login_three() {

    }


    public static void statitActivity(Context context) {

        context.startActivity(new Intent(context, LoginActivity.class));
    }


    @OnClick({R.id.iv_weibo, R.id.iv_wx, R.id.iv_qq, R.id.iv_zfb, R.id.btn_login, R.id.tv_find_pwd, R.id.tv_register})
    public void onViewClicked(View view) {
        Intent intent = new Intent(LoginActivity.this, FindPwdActivity.class);
        switch (view.getId()) {

            case R.id.iv_weibo:
                break;
            case R.id.iv_wx:
                ToastUtil.showToast("正在启动微信...");
                //login_type = "weixin";
                login_type = "2";
                thirdLogin(Wechat.NAME);
                break;
            case R.id.iv_qq:
                login_type = "1";
                //  login_type = "qq";
                ToastUtil.showToast("正在启动QQ...");
                thirdLogin(QQ.NAME);
                break;
            case R.id.iv_zfb:
                break;

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
