package com.lnkj.privateshop.ui.mybuy.sell.order.carriage;


import android.content.Intent;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.CarrIageAdapter;
import com.lnkj.privateshop.entity.ExpressTemplateBean;
import com.lnkj.privateshop.entity.Express_TemplatBean;
import com.lnkj.privateshop.ui.mybuy.sell.order.carriage.province.SelectProvinceActivity;
import com.lnkj.privateshop.utils.CashierInputFilter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 运费模版
 */
public class CarriageActivity extends BaseActivity implements CarriageContract.View {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_weight)
    EditText tvWeight;
    @Bind(R.id.tv_weight_to)
    EditText tvWeightTo;
    @Bind(R.id.ll_parent)
    LinearLayout llParent;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.mListView)
    ListView mListView;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    CarrIageAdapter adapter;
    private int pos;
    private CarriagePresenter mPresenter = new CarriagePresenter(this);

    @Override
    public int initContentView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_carriage;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        tvTitle.setText("编辑运费模板");
        tvRightBlue.setText("完成");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setTextColor(Color.parseColor("#ff7722"));
        InputFilter[] filters = {new CashierInputFilter()};
        tvWeight.setFilters(filters);
        tvWeightTo.setFilters(filters);

        mPresenter.getExpressTemplate();
        adapter = new CarrIageAdapter(this);
        mListView.setAdapter(adapter);
        adapter.setAddressClickListener(new CarrIageAdapter.AddressClickListener() {
            @Override
            public void onDeleteCilck(int position) {
                pos = position;
                if (TextUtils.isEmpty(lists.get(position).getExpress_template_id())) {
                    lists.remove(position);
                    adapter.notifyDataSetChanged();
                } else {
                    mPresenter.delete(lists.get(position).getExpress_template_id());
                }


            }

            @Override
            public void onAddAddoress(int position) {
                StringBuffer city = new StringBuffer();
                for (int i = 0; i < lists.size(); i++) {
                    if (city.length() != 0) {
                        city.append(",");
                    }
                    city.append(lists.get(i).getRegion_id());
                }
                StringBuffer cityname = new StringBuffer();
                for (int i = 0; i < lists.size(); i++) {
                    if (cityname.length() != 0) {
                        cityname.append(",");
                    }
                    cityname.append(lists.get(i).getExpress_template_name());
                }
                Intent intent = new Intent(CarriageActivity.this, SelectProvinceActivity.class);
                intent.putExtra("cityid",city.toString());
                intent.putExtra("cityname",cityname.toString());
                startActivityForResult(intent, position);
            }
        });

    }

    @Override
    public void initUiAndListener() {

    }
    @OnClick({R.id.img_back, R.id.tv_add, R.id.tv_address, R.id.tv_right_blue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.tv_add:
                Express_TemplatBean bean = new Express_TemplatBean();
                bean.setFirst_money("");
                bean.setAdd_money("");

                bean.setExpress_template_id("");
                bean.setRegion_id("");
                bean.setExpress_template_name("选择地区");
                bean.setIs_default("0");
                lists.add(bean);


                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_address:


                Intent intent = new Intent(this, SelectProvinceActivity.class);

                startActivityForResult(intent, 2000);
                break;
            case R.id.tv_right_blue:
                String default_first_money = tvWeight.getText().toString();
                String default_add_money = tvWeightTo.getText().toString();
                StringBuffer fm = new StringBuffer();
                StringBuffer add = new StringBuffer();
                StringBuffer exid = new StringBuffer();
                StringBuffer exname = new StringBuffer();

                for (int i = 0; i < lists.size(); i++) {
                        if (fm.length() != 0) {
                            fm.append("|");
                            add.append("|");
                            exid.append("|");
                            exname.append("|");
                        }
                        fm.append(lists.get(i).getFirst_money());
                        add.append(lists.get(i).getAdd_money());
                        exid.append(lists.get(i).getRegion_id());
                        exname.append(lists.get(i).getExpress_template_name());

                }
                mPresenter.addExpressTempLate(default_first_money, default_add_money, fm.toString(),
                        add.toString(), exid.toString(), exname.toString(), exname.toString());
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

    List<Express_TemplatBean> lists =new ArrayList<>();


    @Override
    public void getExpressTemplateSuccree(ExpressTemplateBean beans) {
        if (beans!=null){
            for (int i = 0; i < beans.getData().size(); i++) {
                Express_TemplatBean bean = new Express_TemplatBean();
                bean.setAdd_money(beans.getData().get(i).getAdd_money());
                bean.setAdd_time(beans.getData().get(i).getAdd_time());
                bean.setAdd_weight(beans.getData().get(i).getAdd_weight());
                bean.setExpress_template_id(beans.getData().get(i).getExpress_template_id());
                bean.setExpress_template_name(beans.getData().get(i).getExpress_template_name());
                bean.setFirst_money(beans.getData().get(i).getFirst_money());
                bean.setFirst_weight(beans.getData().get(i).getFirst_weight());
                bean.setIs_default(beans.getData().get(i).getIs_default());
                bean.setRegion_id(beans.getData().get(i).getRegion_id());
                bean.setRegion_name(beans.getData().get(i).getRegion_name());
                bean.setShop_id(beans.getData().get(i).getShop_id());
                if (!TextUtils.isEmpty(bean.getIs_default())){
                    if (bean.getIs_default().equals("1")) {
                        try {
                        tvWeight.setText(bean.getFirst_money().equals("0.00")?"":bean.getFirst_money());
                        tvWeightTo.setText(bean.getAdd_money().equals("0.00")?"":bean.getAdd_money());

                        }catch (Exception e){
                            tvWeight.setText("");
                            tvWeightTo.setText("");
                        }
                        continue ;
                    }
                }
                lists.add(bean);
            }
        }
        adapter.AddData(lists);




    }

    @Override
    public void deletesrccreed() {
        lists.remove(pos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addSuccreed() {
//        mPresenter.getExpressTemplate();
        finish();
    }

    String addressid;
    String addressname;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (resultCode == 30) {
                if (data != null) {
//                    intent.putExtra("addressname",adapter.getAddressName());
//                    intent.putExtra("addressid",adapter.getAddressId());
                    addressname = data.getStringExtra("addressname");
                    addressid = data.getStringExtra("addressid");
                    tvAddress.setText(data.getStringExtra("addressname"));
                }
            }
        }

        for (int i = 0; i < lists.size(); i++) {
            if (requestCode == i) {
                try {
                lists.get(i).setExpress_template_name(data.getStringExtra("addressname"));
                lists.get(i).setRegion_id(data.getStringExtra("addressid"));
                adapter.notifyDataSetChanged();
                }catch (Exception e){}
            }
        }
    }
}
