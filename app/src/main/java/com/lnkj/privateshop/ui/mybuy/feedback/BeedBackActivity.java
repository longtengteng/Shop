package com.lnkj.privateshop.ui.mybuy.feedback;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BeedBackActivity extends BaseActivity implements  BeedBackContract.View{
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.btn_go)
    Button btnGo;
    BeedBackPresenter mPresenter = new BeedBackPresenter(this);
    @Override
    public int initContentView() {
        return R.layout.activity_beed_back;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("意见反馈");
        mPresenter.getToken(token);
    }

    @Override
    public void initUiAndListener() {

    }


    @OnClick({R.id.img_back, R.id.btn_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_go:
                mPresenter.getPutFromServer(etContent.getText().toString(),etPhone.getText().toString());
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
    public void succree() {
        finish();
    }
}
