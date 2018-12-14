package com.lnkj.privateshop.ui.addshoppingcart.altercart;


import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.AlterShopPingCarAdapter;
import com.lnkj.privateshop.entity.AlterGoodsCountBean;
import com.lnkj.privateshop.view.CenterActionDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlterGoodsCarActivity extends BaseActivity implements AlterGoodsCarContract.View{
    AlterGoodsCarPresenter mPresenter = new AlterGoodsCarPresenter(this);
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
    private String Goods_id;
    private AlterShopPingCarAdapter adapter;
    private AlterGoodsCountBean.DataBean.GoodsInfoBean bean;

    @Override
    public int initContentView() {
        return R.layout.activity_alter_goods_car;
    }

    @Override
    public void initInjector() {
        mPresenter.setToken(token);
        ButterKnife.bind(this);
        tvTitle.setText("修改规格和数量");
        Goods_id=getIntent().getStringExtra("goods_id");
        mPresenter.setToken(token);
        mPresenter.getGoods(Goods_id);

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
    public void initView() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setTitle(String s) {

    }
    List<AlterGoodsCountBean.DataBean.GoodsSpecBean> lists = new ArrayList<>();
    @Override
    public void getGoodsSucceed(AlterGoodsCountBean beans) {
        bean = beans.getData().getGoods_info();
        Glide
                .with(this)
                .load(Constants.Base_URL + bean.getGoods_img())
                .error(R.mipmap.bg_img)
                .into(ivGoodsImg);
        tvGoodsName.setText(bean.getGoods_name());
        int basic = Integer.parseInt(bean.getBasic_amount())-1;
        tvCount1.setText(bean.getRetail_amount()+"件—"+basic+"件");
        tvCount2.setText(bean.getBasic_amount()+"件以上");
        tvPrice1.setText("¥"+ bean.getShop_price());
        tvPrice2.setText("¥"+ bean.getPack_price());

        lists.addAll(beans.getData().getGoods_spec());

        adapter = new AlterShopPingCarAdapter(this,lists);
        mListView.setAdapter(adapter);
        adapter.setNumberClickListener(new AlterShopPingCarAdapter.NumberClickListener() {
            @Override
            public void onNumberCilck(int number, int position) {

                try {
                    int pack_count = Integer.parseInt(bean.getBasic_amount());
                    double pack_price = Double.parseDouble(bean.getPack_price());
                    double price = Double.parseDouble(bean.getShop_price());
                    double total_price;
                    int total_count = 0;
                    for (int i = 0; i < lists.size(); i++) {
                        total_count=total_count+lists.get(i).getHas_number();
                    }
                    if (total_count<pack_count){
                        total_price=price*total_count;
                    }else {
                        total_price=pack_price*total_count;
                    }
                    tv2.setText(total_count+"");



                    BigDecimal b2 = new BigDecimal(total_price);
                    double f2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    tv_price.setText("¥"+f2);

                }catch (Exception e){}
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
    @OnClick({R.id.img_back, R.id.tv_add_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add_cart:
                String buy_number = getbuy_number();
                String spec_key =  goods_spec_key();
                System.out.println("buy_number:"+buy_number);
                System.out.println("spec_key:"+spec_key);
                mPresenter.AddShopPing(spec_key,Goods_id,buy_number);
                break;
        }
    }
    @Override
    public void AddshopIngSucceed() {

        CenterActionDialog dialog = new CenterActionDialog(this);
        dialog.setActionString("修改成功",
                "返回进货车",
                "取消");
        dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
            @Override
            public void sureAction() {
                 setResult(30);

                    finish();
            }

            @Override
            public void cancelAction() {

            }
        });
        dialog.show();
    }
    public String goods_spec_key(){

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getHas_number()!=0){
                if (sb.length()!=0){
                    sb.append(",");
                }
//                sb.append("\"");
                sb.append(lists.get(i).getSpec_key());
//                sb.append("\"");
            }

        }
        return sb.toString();
    }

    public String getbuy_number(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getHas_number()!=0){
                if (sb.length()!=0){
                    sb.append(",");
                }
                sb.append(lists.get(i).getHas_number());
            }
        }

        return sb.toString();
    }
}
