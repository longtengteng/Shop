package com.lnkj.privateshop.ui.mybuy.userinfo;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.utils.FileUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.lnkj.privateshop.R.id.img_head;

public class EditUserActivity extends BaseActivity implements EditUserContract.View {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(img_head)
    CircleImageView imgHead;
    @Bind(R.id.tv_nickname)
    EditText tvNickname;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_vip)
    TextView tvVip;
//    private int type  = 1;
//  intent.putExtra("Head_pic",databean.getHead_pic());
//                    intent.putExtra("Nickname",databean.getNickname());
//                    intent.putExtra("Register_time",databean.getRegister_time());
//                    intent.putExtra("User_level",databean.getUser_level());
    String Head_pic;
    String Nickname;
    String Register_time;
    String User_level;

    EditUserPresenter mPresenter = new EditUserPresenter(this);
    @Override
    public int initContentView() {
        return R.layout.activity_edit_user;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        tvTitle.setText("个人信息");
        tvRightBlue.setText("完成");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setTextColor(Color.parseColor("#ff7722"));

        Head_pic=getIntent().getStringExtra("Head_pic");
        Nickname=getIntent().getStringExtra("Nickname");
        Register_time=getIntent().getStringExtra("Register_time");
        User_level=getIntent().getStringExtra("User_level");
//        Glide
//                .with(this)
//                .load(Constants.Base_URL +Head_pic)
//                .error(R.mipmap.bg_img)
//                .into(imgHead);

        Glide.with(this)
                .load(Constants.Base_URL +Head_pic)
                .asBitmap()
                .error(R.mipmap.bg_img)
                .into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imgHead.setImageBitmap(resource);
                path =   FileUtil.saveBitmap(resource);
                System.out.println(path);
            }
        });
        //方法中设置asBitmap可以设置回调类型

        tvNickname.setText(Nickname);
        tvTime.setText(Register_time);
        tvVip.setText(User_level);


    }

    @Override
    public void initUiAndListener() {

    }
    private int IMAGE_PICKER =0x00002;
    @OnClick({R.id.img_back, R.id.tv_right_blue,R.id.ll_edit_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_blue:
//                if (type==1){
//                mPresenter.upladData("",tvNickname.getText().toString());
//                }else {
                mPresenter.upladData(path,tvNickname.getText().toString());
                break;

            case R.id.ll_edit_phone:
                ImagePicker imagePicker2 = ImagePicker.getInstance();
                imagePicker2.setMultiMode(false);
                imagePicker2.setCrop(true);
                Intent intent2 = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent2, IMAGE_PICKER);
                break;
        }
    }
    String path;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER){
                ArrayList<ImageItem> lists =     (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                path = lists.get(0).path;
                Glide.with(this).load
                        (path)
                        .into(imgHead);
//                type = 2;

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
        finish();
    }
}
