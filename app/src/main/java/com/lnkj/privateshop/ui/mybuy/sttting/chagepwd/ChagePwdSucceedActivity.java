package com.lnkj.privateshop.ui.mybuy.sttting.chagepwd;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChagePwdSucceedActivity extends BaseActivity {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.tv_validation)
    TextView tvValidation;
    @Bind(R.id.btn_coles)
    Button btnColes;

    @Override
    public int initContentView() {
        return R.layout.activity_chage_pwd_succeed;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("身份验证");
    }

    @Override
    public void initUiAndListener() {

    }



    @OnClick({R.id.img_back, R.id.btn_coles})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_coles:
                finish();
                break;
        }
    }
}
