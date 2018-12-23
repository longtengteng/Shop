package com.lnkj.privateshop.ui.goods.spec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsBean;
import com.lnkj.privateshop.entity.OrderConBean;
import com.lnkj.privateshop.ui.goods.AttrAdapter;
import com.lnkj.privateshop.ui.goods.GoodsInfoPresenter;
import com.lnkj.privateshop.ui.goodscar.ClearingActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lzy.imagepicker.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.Glide.with;

/*商品详情页面，商品规格选择*/
public class SpecActivity extends BaseActivity implements SpecContract.View {
    @Bind(R.id.iv_goods)
    ImageView ivGoods;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_number)
    TextView tvNumber;
    @Bind(R.id.iv_finish)
    ImageView ivFinish;
    @Bind(R.id.rv_spec)
    RecyclerView rvSpec;
    @Bind(R.id.tv_attr)
    TextView tvAttr;
    @Bind(R.id.iv_subtract)
    ImageView ivSubtract;
    @Bind(R.id.tv_item_number)
    TextView tvItemNumber;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.tv_cart)
    TextView tvCart;
    @Bind(R.id.tv_buynow)
    TextView tvBuynow;
    @Bind(R.id.dialog)
    LinearLayout dialog;
    private SpecPresenter presenter = new SpecPresenter(this, this);
    String img, price, storage;
    List<GoodsBean.DataBean.GoodsSpecBean> specBeanList = new ArrayList<>();
    SpecAdapter adapter;
    int tv_number;
    String goods_id;
    List<String> spec = new ArrayList<>();
    List<String> spec_name_list = new ArrayList<>();
    String spec_name2;

    @Override
    public int initContentView() {
        return R.layout.activity_detail_spec;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        presenter.setToken(token);
        specBeanList = (List<GoodsBean.DataBean.GoodsSpecBean>) getIntent().getSerializableExtra("speclist");
        goods_id = getIntent().getStringExtra("goods_id");
        rvSpec.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SpecAdapter(specBeanList);
        adapter.bindToRecyclerView(rvSpec);
        adapter.setAutoLoadMoreSize(1);
        adapter.disableLoadMoreIfNotFullPage(rvSpec);

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        ;   //高度设置为屏幕的1.0
        p.width = WindowManager.LayoutParams.MATCH_PARENT;    //宽度设置为屏幕的0.8
        p.alpha = 1.0f;      //设置本身透明度
        p.dimAmount = 0.0f;      //设置黑暗度
        getWindow().setAttributes(p);     //设置生效
        getWindow().setGravity(Gravity.BOTTOM);       //设置靠右对齐

        tvNumber.setText("库存" + getIntent().getStringExtra("storage") + "件");
        tvPrice.setText("¥" + getIntent().getStringExtra("price"));
        with(this)
                .load(Constants.Base_URL + getIntent().getStringExtra("img"))
                .error(R.mipmap.de_photo)
                .into(ivGoods);

        for (int i = 0; i < specBeanList.size(); i++) {
            spec.add("");
            spec_name_list.add("");
        }
        //   ToastUtil.showToast(spec + "" + spec.size());
    }

    String spec_content3;

    @Override
    public void initUiAndListener() {
        adapter.setM(new SpecAdapter.CheckClick() {
            @Override
            public void checkSpec(GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean itemArrayBean, int list_position) {
                spec.set(list_position, itemArrayBean.getSpec_item_id());
                spec_name_list.set(list_position, itemArrayBean.getSpec_item_name());

                String spec_content = spec + "";
                String spec_content1 = spec_content.substring(1, spec_content.length() - 1);
                String spec_content2;
                if (spec_content1.length() == 3) {
                    spec_content2 = spec_content1.replace(",", "");
                } else {
                    spec_content2 = spec_content1.replace(",", "_");
                }
                spec_content3 = spec_content2.replace(" ", "");

                String spec_name = spec_name_list + "";
                String spec_name1 = spec_name.substring(1, spec_name.length() - 1);

                if (spec_name1.length() == 3) {
                    spec_name2 = spec_name1.replace(",", "");
                } else {
                    spec_name2 = spec_name1.replace(",", " ");
                }


                presenter.getPriceAndStoreBySpce(spec_content3, goods_id);
                //   ToastUtil.showToast(spec + "");
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("spec_content3", spec_content3);
        intent.putExtra("spec_name", spec_name2);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @OnClick({R.id.iv_finish, R.id.iv_subtract, R.id.iv_add, R.id.tv_cart, R.id.tv_buynow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                Intent intent = new Intent();
                intent.putExtra("spec_content3", spec_content3);
                intent.putExtra("spec_name", spec_name2);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.iv_subtract:
                if (tv_number > 1) {
                    tv_number--;
                    tvItemNumber.setText(tv_number + "");
                }
                break;
            case R.id.iv_add:
                tv_number++;
                tvItemNumber.setText(tv_number + "");
                break;
            case R.id.tv_cart:
                presenter.addCart(spec_content3, goods_id, tvItemNumber.getText().toString().trim(), "", "0");
                break;
            case R.id.tv_buynow:
                presenter.cartConfirm(goods_id, tvItemNumber.getText().toString().trim(), spec_content3);
                break;
        }
    }

    @Override
    public void initView() {

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
    public void finsh() {

    }

    @Override
    public void getPriceAndStoreBySpceSucceed(SpecBean specBean) {

        tvNumber.setText("库存" + specBean.getData().getStore_count() + "件");
        tvPrice.setText("¥" + specBean.getData().getPrice());

    }

    @Override
    public void addCart() {
        finish();
    }

    @Override
    public void cartConfirm(BugNowBean bugNowBean) {

    }

    @Override
    public void getGoodsInfoSucceed(OrderConBean orderConBean) {
        Intent intent = new Intent(this, ClearingActivity.class);
        intent.putExtra("orderConBean", orderConBean.getData());
        startActivity(intent);
    }

    @Override
    public void btnClickable(boolean clickable) {
        tvBuynow.setClickable(clickable);
    }
}
