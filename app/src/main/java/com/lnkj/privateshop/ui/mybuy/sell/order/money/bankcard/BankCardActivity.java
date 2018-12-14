package com.lnkj.privateshop.ui.mybuy.sell.order.money.bankcard;


import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.BankCardAdapter;
import com.lnkj.privateshop.entity.BankCardBean;
import com.lnkj.privateshop.ui.mybuy.sell.order.money.bankcard.addbankcard.AddBankCardActivity;
import com.lnkj.privateshop.view.CenterActionDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 银行卡管理
 */
public class BankCardActivity extends BaseActivity implements BanKcardContract.View{

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.layout_no_datas)
    LinearLayout layoutNoDatas;
    @Bind(R.id.mListView)
    ListView mListView;
    BanKcardPresenter mPresenter = new BanKcardPresenter(this);
    private BankCardAdapter adapter;

    @Override
    public int initContentView() {
        return R.layout.activity_bank_card;
    }
    int pos;
    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("银行卡管理");
        tvRightBlue.setText("添加");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setTextColor(Color.parseColor("#ff7722"));
        mPresenter.getToken(token);
        mPresenter.getDataFromServer();



    }

    @Override
    public void initUiAndListener() {

    }
    @OnClick({R.id.img_back, R.id.tv_right_blue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_blue:
                Intent intent = new Intent(this, AddBankCardActivity.class);
                startActivityForResult(intent,20);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == 30) {
            mPresenter.getDataFromServer();
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
    public void deleteBankCardSuccreed() {
        lists.remove(pos);
        adapter.notifyDataSetChanged();
        if (lists!=null&&lists.size()!=0){
            layoutNoDatas.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        }else {
            layoutNoDatas.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }

    }
    List<BankCardBean.DataBean> lists ;
    @Override
    public void setData( BankCardBean beans) {
        lists = beans.getData();
        adapter = new BankCardAdapter(this,lists);
        mListView.setAdapter(adapter);
        if (lists!=null&&lists.size()!=0){
            layoutNoDatas.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        }else {
            layoutNoDatas.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }

        adapter.setAddressClickListener(new BankCardAdapter.onDelClickListener() {
            @Override
            public void onDeleteCilck(final int position) {
                CenterActionDialog dialog = new CenterActionDialog(BankCardActivity.this);
                dialog.setActionString("确定删除此银行卡吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        //此处删除
                        if (lists!=null){
                            pos = position;
                            mPresenter.delteBankCard(lists.get(position).getId());
                        }
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();
            }
        });

    }
}
