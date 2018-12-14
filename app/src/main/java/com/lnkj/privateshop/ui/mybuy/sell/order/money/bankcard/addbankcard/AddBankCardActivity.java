package com.lnkj.privateshop.ui.mybuy.sell.order.money.bankcard.addbankcard;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加银行卡
 */
public class AddBankCardActivity extends BaseActivity implements AddBanKcardContract.View {

    AddBanKcardPresenter mPresenter = new AddBanKcardPresenter(this);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.et_bank_name)
    EditText etBankName;
    @Bind(R.id.btn_ok)
    Button btnOk;
    @Bind(R.id.ll_1)
    LinearLayout ll_1;
    @Bind(R.id.ll_ok)
    LinearLayout ll_ok;
    private int index=0;
    @Bind(R.id.et_kaihuh)
    EditText et_kaihuh;
    @Override
    public int initContentView() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        tvTitle.setText("添加银行卡");

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
    public void initView() {

    }

    @Override
    public void setData() {
        index =2;
        ll_1.setVisibility(View.GONE);
        ll_ok.setVisibility(View.VISIBLE);

        setResult(30);
        finish();
    }


    @OnClick({R.id.img_back, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                if (index==0){
                    finish();
                }else {
                    setResult(30);
                    finish();
                }
                break;
            case R.id.btn_ok:
                if (index==0){
                    mPresenter.getDataFromServer(etName.getText().toString(),etNumber.getText().toString(),etBankName.getText().toString(),et_kaihuh.getText().toString().trim());
                }else {
                }
                break;
        }
    }
}
