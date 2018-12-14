package com.lnkj.privateshop.ui.goodscar.speak;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpeakActivity extends BaseActivity {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_speak)
    EditText etSpeak;
    @Bind(R.id.btn_ok)
    Button btnOk;

    @Override
    public int initContentView() {
        return R.layout.activity_speak;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("留言");
    }

    @Override
    public void initUiAndListener() {

    }



    @OnClick({R.id.img_back, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_ok:
                Intent intent = new Intent();
                intent.putExtra("speak",etSpeak.getText().toString());
                setResult(40,intent);
                finish();
                break;
        }
    }
}
