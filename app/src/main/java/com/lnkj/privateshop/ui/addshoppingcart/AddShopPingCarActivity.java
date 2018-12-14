package com.lnkj.privateshop.ui.addshoppingcart;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.AddShopPingCarAdapter;
import com.lnkj.privateshop.entity.GoodsBean;
import com.lnkj.privateshop.entity.GoodsSpecBean;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.view.CenterActionDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShopPingCarActivity extends BaseActivity implements AddShopPingCarContract.View {
    AddShopPingPresenter mPresenter = new AddShopPingPresenter(this);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_goods_name)
    TextView tvGoodsName;
    @Bind(R.id.iv_goods_img)
    ImageView ivGoodsImg;
    @Bind(R.id.tv_count_1)
    TextView tvCount1;
    @Bind(R.id.tv_count_2)
    TextView tvCount2;
    @Bind(R.id.tv_price_1)
    TextView tvPrice1;
    @Bind(R.id.tv_price_2)
    TextView tvPrice2;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.tv_add_cart)
    TextView tvAddCart;
    @Bind(R.id.mListView)
    ListView mListView;
    @Bind(R.id.tv_price)
    TextView tv_price;
    Boolean activity;
    private String Goods_id;
    private AddShopPingCarAdapter adapter;
    private GoodsBean.DataBean.GoodsInfoBean bean;

    @Override
    public int initContentView() {
        return R.layout.activity_add_shop_ping_car;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("选择规格和数量");
        Bundle bundle = getIntent().getExtras();
        bean = (GoodsBean.DataBean.GoodsInfoBean) bundle.getSerializable("lists");
        Goods_id = bundle.getString("goods_id");
        activity = bundle.getBoolean("activity", false);

        mPresenter.setToken(token);
        mPresenter.getGoods(Goods_id);
    }

    @Override
    public void initUiAndListener() {
        if (bean != null) {
            Glide
                    .with(this)
                    .load(Constants.Base_URL + bean.getGoods_img())
                    .error(R.mipmap.bg_img)
                    .into(ivGoodsImg);
            tvGoodsName.setText(bean.getGoods_name());
            int basic = Integer.parseInt( bean.getBasic_amount())-1;
            tvCount1.setText(bean.getRetail_amount() + "—" + basic + "件");
            tvCount2.setText(bean.getBasic_amount() + "件以上");
            if (!activity) {
                tvPrice1.setText("¥" + bean.getShop_price());
                tvPrice2.setText("¥" + bean.getPack_price());
            }
        }
    }


    @OnClick({R.id.img_back, R.id.tv_add_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add_cart:
                String buy_number = getbuy_number();
                String spec_key = goods_spec_key();
                mPresenter.AddShopPing(spec_key, Goods_id, buy_number);
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
    public void setTitle(String s) {

    }

    List<GoodsSpecBean.DataBean> lists = new ArrayList<>();
    double pack_price;
    double price;

    @Override
    public void getGoodsSucceed(GoodsSpecBean beans) {
        lists.addAll(beans.getData());
        adapter = new AddShopPingCarAdapter(this, lists);
        mListView.setAdapter(adapter);
        if (activity) {
            tvPrice1.setText("¥" + lists.get(0).getPrice());
            tvPrice2.setText("¥" + lists.get(0).getPack_price());
        }
        adapter.setNumberClickListener(new AddShopPingCarAdapter.NumberClickListener() {
            @Override
            public void onNumberCilck(int number, int position) {
//                lists.get(position).setNummber(number);
                try {
                    if (activity) {
                        pack_price = Double.parseDouble(lists.get(0).getPack_price());
                        price = Double.parseDouble(lists.get(0).getPrice());
                    } else {
                        pack_price = Double.parseDouble(bean.getPack_price());
                        price = Double.parseDouble(bean.getShop_price());
                    }

                } catch (Exception e) {
                }
                try {
                    int pack_count = Integer.parseInt(bean.getBasic_amount());
                    double total_price;
                    int total_count = 0;
                    for (int i = 0; i < lists.size(); i++) {
                        total_count = total_count + lists.get(i).getHas_number();
                    }
                    if (total_count < pack_count) {
                        total_price = price * total_count;
                    } else {
                        total_price = pack_price * total_count;
                    }
                    BigDecimal b2 = new BigDecimal(total_price);
                    double f2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    tv2.setText(total_count + "");
                    tv_price.setText("¥" + f2);

                } catch (Exception e) {
                }
            }
        });
        try {
            int pack_count = Integer.parseInt(bean.getBasic_amount());
            double pack_price = Double.parseDouble(bean.getPack_price());
            double price = Double.parseDouble(bean.getShop_price());
            double total_price;
            int total_count = 0;
            for (int i = 0; i < lists.size(); i++) {
                total_count=total_count+lists.get(i).getHas_number();
            }
            if (total_count<=pack_count){
                total_price=price*total_count;
            }else {
                total_price=pack_price*total_count;
            }
            tv2.setText(total_count+"");
            tv_price.setText("¥"+total_price);
        }catch (Exception e){}
    }

    @Override
    public void AddshopIngSucceed() {
        CenterActionDialog dialog = new CenterActionDialog(this);
        dialog.setActionString("已加入进货车",
                "继续购物",
                "立即结算");
        dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
            @Override
            public void sureAction() {
                //继续购物
                finish();
            }

            @Override
            public void cancelAction() {
                //立即结算
                Intent intent = new Intent(AddShopPingCarActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|FLAG_ACTIVITY_CLEAR_TOP);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", "addshop");
                startActivity(intent);
            }
        });
        dialog.show();
    }

    public String goods_spec_key() {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getHas_number() != 0) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
//                sb.append("\"");
                sb.append(lists.get(i).getSpec_key());
//                sb.append("\"");
            }

        }
        return sb.toString();
    }

//    public int GoodsNum;

    public String getbuy_number() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getHas_number() != 0) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(lists.get(i).getHas_number());
//                GoodsNum = GoodsNum + lists.get(i).getHas_number();
            }
        }

        return sb.toString();
    }

}
