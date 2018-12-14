package com.lnkj.privateshop.ui.mybuy.sttting.cashpwd;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.mybuy.sttting.chagepwd.ChagePwdSucceedActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChageChagPwdActivity extends BaseActivity implements ChageChagPwdContract.View {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.et_to_pwd)
    EditText etToPwd;
    @Bind(R.id.ll_chagpwd)
    LinearLayout ll_chagpwd;
    @Bind(R.id.et_namber)
    EditText etNamber;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.iv_pic)
    ImageView ivPic;
    @Bind(R.id.ll_parent)
    LinearLayout ll_parent;
    @Bind(R.id.tv_validation)
    TextView tv_validation;
    ChageChagPwdPresenter mPresenter = new ChageChagPwdPresenter(this);
    @Override
    public int initContentView() {
        return R.layout.activity_chage_chag_pwd;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("设置提现密码");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setText("下一步");
        tvRightBlue.setTextColor(Color.parseColor("#33a3f9"));
        mPresenter.getToken(token);
    }

    @Override
    public void initUiAndListener() {

    }


    @OnClick({R.id.img_back, R.id.tv_right_blue,R.id.iv_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_blue:
                if (tvRightBlue.getText().equals("下一步")){
                String pwd = etPwd.getText().toString();
                String pwdto = etToPwd.getText().toString();
                if (pwd.length() != 6) {
                    ToastUtil.showToast("请输入6位提现密码");
                } else if (!pwd.equals(pwdto)) {
                    ToastUtil.showToast("两次输入密码不相同");
                } else {
                    mPresenter.setWithdrawPassword(pwd,pwdto);
                }

                }else {
                    mPresenter.upladData(etNamber.getText().toString(),etName.getText().toString(),path);
                }
                break;
            case R.id.iv_pic:
                ImagePicker imagePicker2 = ImagePicker.getInstance();
                imagePicker2.setMultiMode(false);
                imagePicker2.setCrop(false);
                Intent intent2 = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent2, IMAGE_PICKER);
                break;
        }
    }
    private int IMAGE_PICKER =0x00002;
    String path;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
          if (data != null && requestCode == IMAGE_PICKER){
                ArrayList<ImageItem> lists = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                path = lists.get(0).path;
                Glide.with(this).load
                        (path)
                        .into(ivPic);

            }else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
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
    public void toLoging() {

    }

    @Override
    public void upladSuccreed() {
        Intent intent = new Intent(this, ChagePwdSucceedActivity.class);
        startActivity(intent);
        finish();

//        CenterActionDialog dialog = new CenterActionDialog(this);
//        dialog.setActionString("提交成功，请等待审核",
//                "确定",
//                "关闭");
//        dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
//            @Override
//            public void sureAction() {
//                finish();
//
//            }
//
//            @Override
//            public void cancelAction() {
//                finish();
//            }
//        });
//        dialog.show();
    }

    @Override
    public void setWithdrawPasswordSuccreed() {
        ll_chagpwd.setVisibility(View.GONE);
        ll_parent.setVisibility(View.VISIBLE);

        tvTitle.setText("身份验证");
        tvRightBlue.setText("提交");
        tv_validation.setText("请填写验证信息");
    }
}
