package com.lnkj.privateshop.ui.seachgoods;


import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.seachgoods.seachshop.SeachShopActivity;
import com.lnkj.privateshop.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SeachGOodsPublicActivity extends BaseActivity {


    @Bind(R.id.img_seach)
    ImageView imgSeach;
    @Bind(R.id.et_seach)
    EditText etSeach;
    @Bind(R.id.tv_seach)
    TextView tvSeach;
    @Bind(R.id.rl_seach)
    RelativeLayout rlSeach;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.ll_shop)
            LinearLayout ll_shop;
//String type="";
    @Override
    public int initContentView() {
        return R.layout.activity_seach_goods_public;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvSeach.setText("取消");
//        type=  getIntent().getStringExtra("type");
    }

    @Override
    public void initUiAndListener() {
//        etSeach.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (etSeach.getText().toString().trim().length()==0){
//                    tvSeach.setText("取消");
//                }else {
//                    tvSeach.setText("搜索");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        etSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    //完成自己的事件
                    //全部的商品
                    if (TextUtils.isEmpty(etSeach.getText().toString().trim())){
                        ToastUtil.showToast("请输入搜索内容");
                    }else {

                    Intent intent = new Intent(SeachGOodsPublicActivity.this,SeachShopActivity.class);
                    intent.putExtra("keyWords",etSeach.getText().toString().trim());
                    startActivity(intent);
                    }
                }
                return false;
            }
        });
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //全部的商品
                Intent intent = new Intent(SeachGOodsPublicActivity.this,SeachGoodsActivity.class);
                intent.putExtra("keyWords",etSeach.getText().toString().trim());
                startActivity(intent);
            }
        });
        ll_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //全部的商品
                Intent intent = new Intent(SeachGOodsPublicActivity.this,SeachShopActivity.class);
                intent.putExtra("keyWords",etSeach.getText().toString().trim());
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.tv_seach)
    public void onViewClicked() {
//        if (tvSeach.getText().toString().equals("取消")){
           finish();
//        }else {
//            if (!TextUtils.isEmpty(type)){
//                Intent i = new Intent();
//                i.putExtra("keyWords",etSeach.getText().toString().trim());
//                if (type.equals("Seach_shop")){
//                    i.putExtra("shop","shop");
//                }
//                setResult(30,i);
//                finish();
//            }else {
//            Intent intent = new Intent(this,SeachGoodsActivity.class);
//            intent.putExtra("keyWords",etSeach.getText().toString().trim());
//            startActivity(intent);
//            }
//        }
    }
}
