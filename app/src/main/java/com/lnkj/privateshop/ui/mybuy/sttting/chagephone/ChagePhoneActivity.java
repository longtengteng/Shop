package com.lnkj.privateshop.ui.mybuy.sttting.chagephone;

import android.text.TextUtils;
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
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.CenterActionDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.ll_chagpwd;
import static com.lnkj.privateshop.R.id.ll_parent;
import static com.lnkj.privateshop.R.id.tv_validation;


public class ChagePhoneActivity extends BaseActivity implements ChagePhoneContract.View{
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
    @Bind(tv_validation)
    TextView tvValidation;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(ll_chagpwd)
    LinearLayout llChagpwd;
    @Bind(R.id.et_Phoen)
    EditText etPhoen;
    @Bind(R.id.et_Phone_tw)
    EditText etPhoneTw;
    @Bind(ll_parent)
    LinearLayout llParent;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.btn_number)
    Button btn_number;
    private  int index = 1;
    ChagePhonePresenter mPresenter = new ChagePhonePresenter(this);
    @Override
    public int initContentView() {
        return R.layout.activity_chage_phone;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("请输入登录密码");
        llParent.setVisibility(View.GONE);
        mPresenter.getToken(token);
    }

    @Override
    public void initUiAndListener() {

    }
    @OnClick({R.id.img_back, R.id.button,R.id.btn_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.button:
                if (index==1){
                    String pwd = etPwd.getText().toString();
                    if (TextUtils.isEmpty(pwd)) {
                        ToastUtil.showToast("请输入密码");
                    }  else {
                        mPresenter.setWithdrawPassword(pwd);
                    }

                }else {

                     mPresenter.upladData(etPhoen.getText().toString(),etPhoneTw.getText().toString());



                }
                break;
            case R.id.btn_number:
                   mPresenter.sendCode(etPhoen.getText().toString());
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
        CenterActionDialog dialog = new CenterActionDialog(this);
        dialog.setActionString("修改手机号成功",
                "确定",
                "关闭");
        dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
            @Override
            public void sureAction() {
                finish();
            }

            @Override
            public void cancelAction() {
                finish();
            }
        });
        dialog.show();
    }

    @Override
    public void setWithdrawPasswordSuccreed() {
        index =2;
        llChagpwd.setVisibility(View.GONE);
        llParent.setVisibility(View.VISIBLE);
        tvValidation.setVisibility(View.VISIBLE);
        tvTitle.setText("更改手机号");
        button.setText("提交");
        tvValidation.setText("请填写验证信息");
        tvTitle.setText("更改手机号码");
    }
    TimeCountUtil timeCountUtil;
    @Override
    public void sendCodeSuccreed() {
        timeCountUtil = new TimeCountUtil(this, 60000, 1000,btn_number );
        timeCountUtil.start();
    }
}
