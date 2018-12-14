package com.lnkj.privateshop.ui.mybuy.openshop.money;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ClassGoodsBean;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工厂点，名称错了
 */

public class RealityActivity extends BaseActivity implements qualificationContract.View {
    qualificationPresenter mPresenter = new qualificationPresenter(this);


    String shop_id;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.iv_sheach)
    ImageView ivSheach;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_http)
    EditText etHttp;
    @Bind(R.id.iv_zhizhao)
    ImageView ivZhizhao;
    @Bind(R.id.tv_look)
    TextView tvLook;
    @Bind(R.id.btn_ok)
    Button btnOk;


    @Override
    public int initContentView() {
        return R.layout.activity_reality;

    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("免费开店");
//        imgBack.setVisibility(View.GONE);
//        tvLeftBlue.setVisibility(View.VISIBLE);
        shop_id = getIntent().getStringExtra("shop_id");
//        tvLeftBlue.setText("关闭");
//        tvLeftBlue.setTextColor(Color.parseColor("#27a2fb"));
        mPresenter.setToken(token);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(RealityActivity.this, MainActivity.class);
//                intent.putExtra("type","openshop");
//                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void initUiAndListener() {

    }

    private int IMAGE_PICKER_ZHIZHAO = 0x0001;

    @OnClick({R.id.img_back, R.id.iv_zhizhao, R.id.tv_look, R.id.btn_ok, R.id.tv_right_blue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_blue:
                finish();
                break;
            case R.id.iv_zhizhao:
                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setMultiMode(false);
                imagePicker.setCrop(false);
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER_ZHIZHAO);
                break;
            case R.id.tv_look:
                Intent i = new Intent(this, LookImgActivity.class);
                i.putExtra("type", "工厂");
                startActivity(i);
                break;
            case R.id.btn_ok:

                mPresenter.putQealityFromServer(shop_id, etName.getText().toString(), etHttp.getText().toString(), path_zhizhao);
                break;
        }
    }

    String path_zhizhao;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER_ZHIZHAO) {
                ArrayList<ImageItem> lists = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                path_zhizhao = lists.get(0).path;
                Glide.with(this).load
                        (path_zhizhao)
                        .into(ivZhizhao);
            }

        }
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void succeed() {

        PreferencesUtils.putInt(this, "is_shop", 1);
        PreferencesUtils.putString(this, "shop_id", shop_id);

        Intent intent = new Intent(this, MoneyActivity.class);
        intent.putExtra("shop_id",shop_id);
        startActivity(intent);
        finish();

//        Intent intent = new Intent(RealityActivity.this, MainActivity.class);
//        intent.putExtra("type", "openshop");
//        startActivity(intent);
    }

    @Override
    public void classSucceed(ClassGoodsBean beass) {

    }


}
