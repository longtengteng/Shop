package com.lnkj.privateshop.ui.mybuy.sell.order.money.withdrawals;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.WithdrawalsCardAdapter;
import com.lnkj.privateshop.entity.BankCardBean;
import com.lnkj.privateshop.ui.mybuy.sell.order.money.bankcard.BankCardActivity;
import com.lnkj.privateshop.utils.CashierInputFilter;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.MyListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithdrawalsActivity extends BaseActivity implements WithdrawalsContract.View {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_price)
    EditText etPrice;
    @Bind(R.id.mListView)
    MyListView mListView;
    @Bind(R.id.btn_ok)
    Button btnOk;
    @Bind(R.id.btn_crad)
    Button btnCrad;
    @Bind(R.id.tv_setting)
            TextView tv_setting;
    WithdrawalsCardAdapter adapter;
    WithdrawalsPresenter mPresenter = new WithdrawalsPresenter(this);
    @Override
    public int initContentView() {
        return R.layout.activity_withdrawals;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);

        tvTitle.setText("提现");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getDataFromServer();
    }

    @Override
    public void initUiAndListener() {
        InputFilter[] filters = {new CashierInputFilter()};
        etPrice.setFilters(filters);
    }
   String id = null;
    private AlertDialog alertDialog;
    @OnClick({R.id.img_back, R.id.btn_ok, R.id.btn_crad,R.id.tv_setting})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_ok:

                if (adapter!=null){
                    id= adapter.getIsChexk();
                }
                if (TextUtils.isEmpty(id)){
                    ToastUtil.showToast("请选择银行卡");
                    return;
                }
                if (TextUtils.isEmpty(etPrice.getText().toString().trim())){
                    ToastUtil.showToast("请输入金额");
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final View view2 = View.inflate(WithdrawalsActivity.this, R.layout.layout_lialog_one, null);
                builder.setView(view2);
                alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setCancelable(false);
                TextView text_title = (TextView) view2.findViewById(R.id.text_title);
                text_title.setText("输入提现密码");
                TextView tv_ok_d = (TextView) view2.findViewById(R.id.tv_ok);
                TextView  tv_quxiao = (TextView) view2.findViewById(R.id.tv_quxiao);
                final EditText mEditText = (EditText) view2.findViewById(R.id.mEditText);
                tv_ok_d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.withDrawals(id,etPrice.getText().toString(),mEditText.getText().toString());
                    }
                });
                //取消
                tv_quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();

                break;
            case R.id.btn_crad:
                intent = new Intent(this, BankCardActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_setting:
                intent = new Intent(this, BankCardActivity.class);
                startActivity(intent);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void withDrawalsSuccreed() {
        finish();
    }

    @Override
    public void alertDialogdiss() {
        if (alertDialog!=null){

        alertDialog.dismiss();
        }
    }

    @Override
    public void setData(BankCardBean beans) {
        adapter = new WithdrawalsCardAdapter(this,beans.getData());
        mListView.setAdapter(adapter);
        if (beans.getData()!=null&&beans.getData().size()!=0){
            tv_setting.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        }else {
            mListView.setVisibility(View.GONE);
            tv_setting.setVisibility(View.VISIBLE);
        }
    }
}
