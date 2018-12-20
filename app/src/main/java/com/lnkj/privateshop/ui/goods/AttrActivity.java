package com.lnkj.privateshop.ui.goods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*商品详情的属性attr*/
public class AttrActivity extends BaseActivity {
    @Bind(R.id.rv_attr)
    RecyclerView rvAttr;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.dialog)
    LinearLayout dialog;
    List<GoodsBean.DataBean.GoodsAttrBean> attrBeanList = new ArrayList<>();
    AttrAdapter adapter;


    @Override
    public int initContentView() {
        return R.layout.activity_detail_attr;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        attrBeanList = (List<GoodsBean.DataBean.GoodsAttrBean>) getIntent().getSerializableExtra("attrlist");
        rvAttr.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AttrAdapter(attrBeanList);
        adapter.bindToRecyclerView(rvAttr);
        adapter.setAutoLoadMoreSize(1);
        adapter.disableLoadMoreIfNotFullPage(rvAttr);



        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;;   //高度设置为屏幕的1.0
        p.width = WindowManager.LayoutParams.MATCH_PARENT;    //宽度设置为屏幕的0.8
        p.alpha = 1.0f;      //设置本身透明度
        p.dimAmount = 0.0f;      //设置黑暗度
        getWindow().setAttributes(p);     //设置生效
        getWindow().setGravity(Gravity.BOTTOM);       //设置靠右对齐
    }

    @Override
    public void initUiAndListener() {

    }


    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        finish();
    }
}
