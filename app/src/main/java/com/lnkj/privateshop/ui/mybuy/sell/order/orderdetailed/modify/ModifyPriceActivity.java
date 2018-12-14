package com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed.modify;


import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.EditOrderAdapter;
import com.lnkj.privateshop.entity.EditOrderPriceBean;
import com.lnkj.privateshop.utils.CashierInputFilter;
import com.lnkj.privateshop.view.MyListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Double.parseDouble;

/**
 * 修改价格
 */
public class ModifyPriceActivity extends BaseActivity implements ModifyPriceContract.View {
    ModifyPricePresenter mPresenter = new ModifyPricePresenter(this);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.mListView)
    MyListView mListView;
    @Bind(R.id.mCheckBox)
    CheckBox mCheckBox;
    @Bind(R.id.et_freight)
    EditText etFreight;
    @Bind(R.id.tv_freight)
    TextView tvFreight;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.tv_reality_price)
    TextView tvRealityPrice;
    @Bind(R.id.tv_number)
    TextView tvNumber;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_people)
    TextView tvPeople;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_btn_ok)
    TextView tvBtnOk;
    private String orderId;
    private EditOrderPriceBean.DataBean data;
    private String newexpressPrice = "0";
    EditOrderAdapter adapter;
    private List<EditOrderPriceBean.DataBean.OrderGoodsBean> goodsbean = new ArrayList<>();

    @Override
    public int initContentView() {
        return R.layout.activity_modify_price;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        tvTitle.setText("修改价格");
        orderId = getIntent().getStringExtra("orderId");
        mPresenter.getOrderFromServer(orderId);
        InputFilter[] filters = {new CashierInputFilter()};
        etFreight.setFilters(filters);
        adapter = new EditOrderAdapter(this);
        mListView.setAdapter(adapter);
    }
    Double goods_price;
    @Override
    public void initUiAndListener() {

        etFreight.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mCheckBox.isChecked()) {
                    newexpressPrice = "0.00";
                } else {
                    if (TextUtils.isEmpty(etFreight.getText().toString())) {

                        newexpressPrice=data.getExpress_amount();
                    } else {
                        try {
                            newexpressPrice =etFreight.getText().toString();
                        } catch (Exception e) {
                        }
                    }
                }

                BigDecimal b = new BigDecimal(parseDouble(newexpressPrice));
                double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                tvFreight.setText("¥" + f1);

                Double d = 0.00;
                for (int j = 0; j < goodsbean.size(); j++) {
                    d = d + parseDouble(goodsbean.get(j).getAfter_money());
                }
                BigDecimal b2 = new BigDecimal((d + parseDouble(newexpressPrice)));

                tvRealityPrice.setText("¥" + b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    newexpressPrice = "0.00";
                } else {
                    if (TextUtils.isEmpty(etFreight.getText().toString())) {
                        newexpressPrice=data.getExpress_amount();
                    } else {
                        try {
                            newexpressPrice = etFreight.getText().toString();
                        } catch (Exception e) {
                        }
                    }
                }
                BigDecimal b3 = new BigDecimal(parseDouble(newexpressPrice));
                tvFreight.setText("¥" + b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                Double d = 0.00;
                for (int i = 0; i < goodsbean.size(); i++) {
                    d = d + parseDouble(goodsbean.get(i).getAfter_money());
                }
                BigDecimal b2 = new BigDecimal((d + parseDouble(newexpressPrice)));
                tvRealityPrice.setText("¥" + b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            }
        });
// 价格改变的监听
        adapter.setOnItemClickListener(new EditOrderAdapter.OnItemClickListener() {
            @Override
            public void EditClick() {
//                //保存改变的价格
//                goodsbean.get(position).setChangePrice(price);
//
//        //计算新价格
//                if (goodsbean.get(position).getIndex().equals("1")){
//                    goods_price=   Double.parseDouble(goodsbean.get(position).getAfter_money())+ Double.parseDouble(price);
//
//                }else {
//                    goods_price=   Double.parseDouble(goodsbean.get(position).getAfter_money())- Double.parseDouble(price);
//                    if (goods_price<0){
//                        goods_price=0.00d;
//                    }
//                }
//                //保存新商品价格
//                BigDecimal b2 = new BigDecimal((goods_price));
//                goodsbean.get(position).setAfter_money( b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"");
//                adapter.notifyDataSetChanged();
//                //保存新总价（包括运费）

                Double d = 0.00;
                int e=0;
                System.out.println("111111111111111========:"+e++);
                for (int i = 0; i < goodsbean.size(); i++) {
                    d = d + parseDouble(goodsbean.get(i).getAfter_money());

                    System.out.println(">>>>>>>>>:"+i);
                    System.out.println(goodsbean.get(i).getAfter_money());
                }
                BigDecimal b3 = new BigDecimal((d + Double.parseDouble(newexpressPrice)));
                tvRealityPrice.setText("¥" + b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
        });
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
    public void getOrderSucceed(EditOrderPriceBean beass) {
        data = beass.getData();
        goodsbean = data.getOrder_goods();
        if (data == null) {
            return;
        }
        adapter.addData(goodsbean);

        tvFreight.setText("¥" + data.getExpress_amount());
        tvTotalPrice.setText("¥" + data.getReal_pay_amount());
        tvRealityPrice.setText("¥" + data.getReal_pay_amount());

        tvNumber.setText(data.getOrder_sn());
        tvTime.setText(data.getAdd_time());
        tvPeople.setText(data.getConsignee());
        tvAddress.setText(data.getAddress());
        newexpressPrice = data.getExpress_amount();
    }

    @Override
    public void setData(String consume, String income, String settlement, String user_money) {

    }

    @Override
    public void modificationPriceSuccreed() {
        finish();
    }

    @OnClick({R.id.img_back, R.id.tv_btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_btn_ok:
                List<EditOrderPriceBean.DataBean.OrderGoodsBean> goodsbean = data.getOrder_goods();
                StringBuffer sbadd = new StringBuffer();
                StringBuffer sbCheck = new StringBuffer();
                StringBuffer sbid = new StringBuffer();
                for (int i = 0; i < goodsbean.size(); i++) {
                    if (sbadd.length() != 0) {
                        sbadd.append(",");
                        sbCheck.append(",");
                        sbid.append(",");
                    }
                    sbCheck.append(goodsbean.get(i).getChangePrice());
                    sbadd.append(goodsbean.get(i).getIndex());
                    sbid.append(goodsbean.get(i).getOrder_goods_id());
                }
                mPresenter.modificationPrice(data.getOrder_sn(), newexpressPrice + "", sbid.toString(), sbadd.toString(), sbCheck.toString());
                break;
        }
    }
}
