package com.lnkj.privateshop.ui.mybuy.myorder.paysuccess;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PaySuccessActivity extends BaseActivity {

    Button button;
    ImageView img_back;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int initContentView() {
        return R.layout.activity_pay_success;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);

        tvTitle.setText("支付成功");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkNext();
            }
        });
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkNext();
            }
        });
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
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
    public void onBackPressed() {
        OkNext();
    }
    public void OkNext(){
        String paytype = PreferencesUtils.getString(PaySuccessActivity.this, "paytype");
        if (!TextUtils.isEmpty(paytype) && paytype.equals("Money")) {
            PreferencesUtils.putInt(PaySuccessActivity.this, "state", Constants.STATE_SELLER);
            PreferencesUtils.putString(PaySuccessActivity.this, "paytype", "no");
            Intent intent = new Intent(PaySuccessActivity.this, MainActivity.class);
            intent.putExtra("type", "openshop");
            startActivity(intent);
        } else {
            Intent intent = new Intent(PaySuccessActivity.this, MainActivity.class);
            intent.putExtra("type", "home");
            startActivity(intent);
        }
    }
}
