package com.lnkj.privateshop.ui.goodscar;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.CarGoodsListsAdapter;
import com.lnkj.privateshop.entity.OrderConBean;
import com.lnkj.privateshop.ui.goodscar.gopay.GoPayOrderActivity;
import com.lnkj.privateshop.ui.goodscar.speak.SpeakActivity;
import com.lnkj.privateshop.ui.mybuy.address.AddressActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.MyListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.tv_price_total;

public class ClearingActivity extends BaseActivity implements ClearingContract.View {
    ClearingPresenter mPresenter = new ClearingPresenter(this);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.mListView)
    MyListView mListView;


    @Bind(R.id.tv_goods_count)
    TextView tvGoodsCount;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_price_freight_to)
    TextView tvPriceFreightTo;
    @Bind(R.id.tv_he)
    TextView tvHe;
    @Bind(tv_price_total)
    TextView tvPriceTotal;
    @Bind(R.id.tv_add_order)
    TextView tvAddOrder;
    CarGoodsListsAdapter adapter;
    private String goods_ids;
    private OrderConBean.DataBean bean;
    private List<OrderConBean.DataBean.GoodsListBean> goodslist;

    @Override
    public int initContentView() {
        return R.layout.activity_clearing;
    }
    @Override
    public void initInjector() {
        mPresenter.setToken(token);
        ButterKnife.bind(this);
        tvTitle.setText("填写订单");
        goods_ids = getIntent().getStringExtra("goods_ids");

        adapter = new CarGoodsListsAdapter(this);
        mListView.setAdapter(adapter);
        mPresenter.getGoodsInfo(goods_ids);
    }
int pos;
    @Override
    public void initUiAndListener() {
        adapter.setAddressClickListener(new CarGoodsListsAdapter.AddressClickListener() {
            @Override
            public void onEditCilck(int position) {
                pos = position;
                Intent  intent = new Intent(ClearingActivity.this, SpeakActivity.class);
                startActivityForResult(intent, 20);
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
    public void getGoodsInfoSucceed(OrderConBean beass) {
        bean = beass.getData();
        try {
        tvGoodsCount.setText("共" + bean.getTotal_goods_num() + "件");
        tvPrice.setText("¥" + bean.getTotal_goods_amount());
        tvPriceTotal.setText("¥" + bean.getTotal_amount());
        tvPriceFreightTo.setText("¥" + bean.getTotal_express());
        OrderConBean.DataBean.AddrInfoBean addinfo = bean.getAddr_info();
        if (addinfo != null) {
             address_id=bean.getAddr_info().getAddress_id();
            if (TextUtils.isEmpty(addinfo.getConsignee())){
                tvName.setText("未设置收货地址");
            }else {

            tvName.setText(addinfo.getConsignee());
            }
            tvAddress.setText(addinfo.getAddress());
            tvPhone.setText(addinfo.getMobile());
        }
        goodslist = bean.getGoods_list();


        if (goodslist != null) {
            adapter.addData(goodslist);
        }

        }catch (Exception e){}

    }

    @Override
    public void CollectGoodsSucceed() {

    }

    @Override
    public void addPayOredeSuccreed(String order_sn) {
        Intent intent = new Intent(this, GoPayOrderActivity.class);
        intent.putExtra("order_sn", order_sn);
        intent.putExtra("total_goods_num", bean.getTotal_goods_num()+"");
        intent.putExtra("express", bean.getTotal_express());
        intent.putExtra("total_goods_amount", bean.getTotal_goods_amount());
        intent.putExtra("total_amount", bean.getTotal_amount());

        finish();
        startActivityForResult(intent, 20);
    }

    @Override
    public void getAddressPriceSucceed() {

    }

    @OnClick({R.id.img_back, R.id.tv_add_order, R.id.ll_adress})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add_order:
                StringBuffer sb = new StringBuffer();
                StringBuffer sb2 = new StringBuffer();

                if (goodslist != null) {
                    for (int i = 0; i < goodslist.size(); i++) {

                        if (sb.length() != 0) {
                            sb.append(",");
                            sb2.append(",");
                        }
                        sb.append(goodslist.get(i).getShop_id());
                        sb2.append(goodslist.get(i).getSpeack());
                    }
                }
                try {
                    mPresenter.addPayOrder(address_id, sb.toString(), sb2.toString());
                } catch (Exception e) {
                    ToastUtil.showToast("请填写收获地址");
                }
                break;
            case R.id.ll_adress:
                intent = new Intent(this, AddressActivity.class);
                intent.putExtra("addorder","address");
                startActivityForResult(intent, 20);
                break;

        }
    }

    String speak="";
    String address_id;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20) {
            if (resultCode == 30) {
                address_id =  data.getStringExtra("id");
                if (address_id!=null){
                    if (!TextUtils.isEmpty(address_id)){
//                tvName.setText(data.getStringExtra("name"));
//                tvAddress.setText(data.getStringExtra("Address"));
//                tvPhone.setText(data.getStringExtra("Mobile"));
                StringBuffer sb = new StringBuffer();
                if (goodslist != null) {
                    for (int i = 0; i < goodslist.size(); i++) {
                        for (int j = 0; j < goodslist.get(i).getGoods().size(); j++) {

                        if (sb.length() != 0) {
                            sb.append(",");
                        }
                        sb.append(goodslist.get(i).getGoods().get(j).getGoods_id());
                        }
                    }
                }
                 mPresenter.getAddressPrice(sb.toString(),address_id);
                    }
                }
            }
            if (resultCode == 40) {
                    if (data != null) {
                        speak = data.getStringExtra("speak");
                        if (!TextUtils.isEmpty(speak)){
                            goodslist.get(pos).setSpeack(speak);
                        }
                        adapter.notifyDataSetChanged();
                    }
            }else if (resultCode!=30){
                mPresenter.getGoodsInfo(goods_ids);
            }
        }
    }
}
