package com.lnkj.privateshop.ui.goods.spec;

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
import com.lnkj.privateshop.ui.goods.AttrAdapter;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lzy.imagepicker.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.Glide.with;

/*商品详情页面，商品规格选择*/
public class SpecActivity extends BaseActivity {
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

    String img, price, storage;
    List<GoodsBean.DataBean.GoodsSpecBean> specBeanList = new ArrayList<>();
    SpecAdapter adapter;
    int tv_number;

    List<String> spec = new ArrayList<>();

    @Override
    public int initContentView() {
        return R.layout.activity_detail_spec;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        specBeanList = (List<GoodsBean.DataBean.GoodsSpecBean>) getIntent().getSerializableExtra("speclist");
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
            spec.add(" ");
        }
        ToastUtil.showToast(spec + "" + spec.size());
    }

    @Override
    public void initUiAndListener() {
        adapter.setM(new SpecAdapter.CheckClick() {
            @Override
            public void checkSpec(GoodsBean.DataBean.GoodsSpecBean.ItemArrayBean itemArrayBean, int list_position) {
                spec.set(list_position, itemArrayBean.getSpec_item_id());
                ToastUtil.showToast(spec + "");
            }
        });
    }


    @OnClick({R.id.iv_finish, R.id.iv_subtract, R.id.iv_add, R.id.tv_cart, R.id.tv_buynow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
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

                break;
            case R.id.tv_buynow:


                break;
        }
    }
}
