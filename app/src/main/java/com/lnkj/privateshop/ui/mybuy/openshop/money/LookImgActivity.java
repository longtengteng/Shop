package com.lnkj.privateshop.ui.mybuy.openshop.money;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LookImgActivity extends BaseActivity {


    @Bind(R.id.tv_close)
    TextView tvClose;
    @Bind(R.id.mImageView)
    ImageView mImageView;
    @Bind(R.id.mImageView_2)
    ImageView mImageView2;
    @Bind(R.id.mImageView3)
    ImageView mImageView3;

    @Override
    public int initContentView() {
        return R.layout.activity_look_img;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        String type = getIntent().getStringExtra("type");

        if (type.equals("实体")) {
            mImageView2.setVisibility(View.GONE);
            mImageView3.setVisibility(View.GONE);
        }else if (type.equals("网店")){
            mImageView2.setVisibility(View.GONE);
            mImageView3.setVisibility(View.GONE);
            mImageView.setImageResource(R.mipmap.img4);
        }

    }

    @Override
    public void initUiAndListener() {

    }


    @OnClick(R.id.tv_close)
    public void onViewClicked() {
        finish();
    }


}
