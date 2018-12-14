package com.lnkj.privateshop.ui.mybuy.sttting.about;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AbuotActivity extends BaseActivity {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int initContentView() {
        return R.layout.activity_abuot;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("关于我们");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
