package com.lnkj.privateshop.ui.mybuy.openshop.money;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ClassGoodsBean;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *实体店
 */

public class qualificationActivity extends BaseActivity implements qualificationContract.View{


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
//    @Bind(R.id.tv_right_blue)
//    TextView tvLeftBlue;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_http)
    EditText etHttp;
    @Bind(R.id.iv_zhizhao)
    ImageView ivZhizhao;
    @Bind(R.id.iv_factory)
    ImageView ivFactory;
    @Bind(R.id.iv_chejian)
    ImageView ivChejian;
    @Bind(R.id.btn_ok)
    Button btnOk;
    qualificationPresenter mPresenter = new qualificationPresenter(this);
    private String shop_id;

    @Override
    public int initContentView() {
        return R.layout.activity_qualification;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("免费开店");
        imgBack.setVisibility(View.VISIBLE);
        shop_id = getIntent().getStringExtra("shop_id");
//        tvLeftBlue.setVisibility(View.VISIBLE);
//        tvLeftBlue.setText("关闭");
//        tvLeftBlue.setTextColor(Color.parseColor("#27a2fb"));
        mPresenter.setToken(token);
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
    private int IMAGE_PICKER_ZHIZHAO=0x0001;
    private int IMAGE_PICKER_FACTORY=0x0002;
    private int IMAGE_PICKER_CHENJIA=0x0003;

    @OnClick({R.id.iv_zhizhao, R.id.iv_factory, R.id.iv_chejian, R.id.btn_ok,R.id.tv_look,R.id.tv_right_blue,R.id.img_back})
    public void onViewClicked(View view) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(false);
        imagePicker.setCrop(false);
        Intent intent = new Intent(this, ImageGridActivity.class);
        switch (view.getId()) {
            case R.id.iv_zhizhao:
                startActivityForResult(intent, IMAGE_PICKER_ZHIZHAO);
                break;
            case R.id.iv_factory:
                startActivityForResult(intent, IMAGE_PICKER_FACTORY);
                break;
            case R.id.iv_chejian:
                startActivityForResult(intent, IMAGE_PICKER_CHENJIA);
                break;
            case R.id.btn_ok:

                mPresenter.getDataFromServer(shop_id,etName.getText().toString(),etHttp.getText().toString(),path_zhizhao,path_factory,chenjia);
                break;
            case R.id.tv_look:
                Intent i = new Intent(this,LookImgActivity.class);
                i.putExtra("type","工厂");
                startActivity(i);
                break;
            case R.id.tv_right_blue:
                finish();
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
    String path_zhizhao;
    String path_factory;
    String chenjia;
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
            if (data != null && requestCode == IMAGE_PICKER_FACTORY) {
                ArrayList<ImageItem> lists = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                path_factory = lists.get(0).path;
                Glide.with(this).load
                        (path_factory)
                        .into(ivFactory);

            }
            if (data != null && requestCode == IMAGE_PICKER_CHENJIA) {
                ArrayList<ImageItem> lists = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                chenjia = lists.get(0).path;
                Glide.with(this).load
                        (chenjia)
                        .into(ivChejian);

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
        PreferencesUtils.putInt(this, "is_shop",1);
        PreferencesUtils.putString(this, "shop_id",shop_id);



        Intent intent = new Intent(this, MoneyActivity.class);
        intent.putExtra("shop_id",shop_id);
        startActivity(intent);
        finish();

    }

    @Override
    public void classSucceed(ClassGoodsBean beass) {

    }
}
