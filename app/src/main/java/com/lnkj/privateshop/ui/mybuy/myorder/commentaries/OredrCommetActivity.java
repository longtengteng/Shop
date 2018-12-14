package com.lnkj.privateshop.ui.mybuy.myorder.commentaries;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.AlbumReturnAdapter;
import com.lnkj.privateshop.entity.ShopBean;
import com.lnkj.privateshop.view.MyGridView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class OredrCommetActivity extends BaseActivity implements OrderCommetContract.View {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_shop_head)
    CircleImageView imgShopHead;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.rc_rate_mass)
    RatingBar rcRateMass;
    @Bind(R.id.tv_mass)
    TextView tvMass;
    @Bind(R.id.rb_rate_ervice)
    RatingBar rbRateErvice;
    @Bind(R.id.tv_service)
    TextView tvService;
    @Bind(R.id.rb_rate_velocity)
    RatingBar rbRateVelocity;
    @Bind(R.id.tv_velocity)
    TextView tvVelocity;
    @Bind(R.id.rl_parent)
    LinearLayout rlParent;
    @Bind(R.id.et_context)
    EditText etContext;
    @Bind(R.id.mGridView)
    MyGridView mGridView;
    @Bind(R.id.btn_publish)
    Button btnPublish;
    @Bind(R.id.tv_coun)
    TextView tv_coun;
    private String orderId;
    AlbumReturnAdapter mAdapter;
    OrderCommetPresenter mPresenter = new OrderCommetPresenter(this);
    int rank=5;
    int express_rank=5;
    int service_rank=5;
    String shop_id;

    @Override
    public int initContentView() {
        return R.layout.activity_oredr_commet;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("订单评价");
        mPresenter.getToken(token);
        orderId = getIntent().getStringExtra("orderId");
        shop_id =getIntent().getStringExtra("shop_id");
        mPresenter.getShopInfo(shop_id);
        etContext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_coun.setText(etContext.getText().toString().length()+"/500");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private int IMAGE_PICKER =0x00001;
    @Override
    public void initUiAndListener() {
        mAdapter = new AlbumReturnAdapter(this, images);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == getDataSize()) {
                    ImagePicker imagePicker = ImagePicker.getInstance();
                    imagePicker.setSelectLimit(9-images.size());
                    Intent intent = new Intent(OredrCommetActivity.this, ImageGridActivity.class);
                    startActivityForResult(intent, IMAGE_PICKER);
                }
            }
        });

        rcRateMass.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rank=(int)v;
                tvMass.setText(rank+"星");
            }
        });
        rbRateErvice.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                service_rank=(int)v;
                tvService.setText((int)v+"星");
            }
        });
        rbRateVelocity.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                express_rank=(int)v;
              tvVelocity.setText((int)v+"星");
            }
        });
    }

    ArrayList<ImageItem> images =new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                images.addAll((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
                mAdapter.bindList(images);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private int getDataSize() {
        return images == null ? 0 : images.size();
    }
    @OnClick({R.id.img_back, R.id.btn_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_publish:
//                System.out.println("rank:"+rank);
//                System.out.println("service_rank:"+service_rank);
//                System.out.println("express_rank:"+express_rank);
                mPresenter.getDataFromServer(orderId,etContext.getText().toString(),rank,service_rank,express_rank,images);
                break;
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
        btnPublish.setClickable(false);
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
        btnPublish.setClickable(true);
    }

    @Override
    public void succreed() {
            setResult(30);
            finish();
    }

    @Override
    public void getShopInfoSuccreed( ShopBean beass) {
        ShopBean.DataBean   shopinfo = beass.getData();
        Glide
                .with(this)
                .load(Constants.Base_URL + shopinfo.getShop_logo())
                .error(R.mipmap.bg_img)
                .into(imgShopHead);
        tvShopName.setText(shopinfo.getShop_name());
    }
}
