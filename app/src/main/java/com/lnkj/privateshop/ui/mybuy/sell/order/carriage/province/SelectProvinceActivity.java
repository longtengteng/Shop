package com.lnkj.privateshop.ui.mybuy.sell.order.carriage.province;


import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.AddressAdapter;
import com.lnkj.privateshop.entity.AddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectProvinceActivity extends BaseActivity implements SelectProvinceContract.View {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.mListView)
    ListView mListView;
    AddressAdapter adapter;
    SelectProvincePresenter mPresenter = new SelectProvincePresenter(this);
    String cityid;
//    private String addressname;

    @Override
    public int initContentView() {
        return R.layout.activity_select_province;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("选择地区");
        cityid = getIntent().getStringExtra("cityid");
//        addressname = getIntent().getStringExtra("cityname");

        System.out.println("cityid："+cityid);
//        System.out.println("cityname："+ addressname);

        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setText("完成");
        tvRightBlue.setTextColor(Color.parseColor("#ff7722"));
        mPresenter.getToken(token);
        adapter = new AddressAdapter(this);
        mPresenter.getAddress();
        mListView.setAdapter(adapter);
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
                Intent intent = new Intent();
                intent.putExtra("addressname", adapter.getAddressName());
                intent.putExtra("addressid", adapter.getAddressId());
                setResult(30, intent);
                finish();
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

    List<AddressBean.DataBean> data = new ArrayList<>();

    @Override
    public void getAddressSuccree(AddressBean beans) {
        data = beans.getData();
        if (!TextUtils.isEmpty(cityid)) {
            String[] cityidstr = cityid.split(",");
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < cityidstr.length; j++) {
//                    System.out.println(cityidstr[j]);
                    if (data.get(i).getId().equals(cityidstr[j])) {
                        data.remove(i);
                    }
                }
            }
        }
        adapter.addData(data);
    }
}
