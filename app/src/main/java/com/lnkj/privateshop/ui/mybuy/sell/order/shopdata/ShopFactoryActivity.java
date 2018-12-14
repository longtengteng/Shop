package com.lnkj.privateshop.ui.mybuy.sell.order.shopdata;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ClassGoodsAdapter;
import com.lnkj.privateshop.entity.AddGoodsBean;
import com.lnkj.privateshop.entity.EditShopBean;
import com.lnkj.privateshop.ui.mybuy.sell.order.carriage.CarriageActivity;
import com.lnkj.privateshop.ui.shop.shopInfo.ShopInfoActivity;
import com.lnkj.privateshop.utils.UiUtils;
import com.lnkj.privateshop.view.CountEdithundredText;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.btn_submit;


public class ShopFactoryActivity extends BaseActivity implements ShopDataEditContract.View, View.OnClickListener {

    String shop_id;
    ShopDataEeitPresenter mPresenter = new ShopDataEeitPresenter(this);
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
    @Bind(R.id.iv_img)
    ImageView ivImg;
    @Bind(R.id.tv_edit_iv)
    TextView tvEditIv;
    @Bind(R.id.tv_ship_name)
    TextView tvShipName;
    @Bind(R.id.tv_shop_type)
    TextView tvShopType;
    @Bind(R.id.im_grade1)
    ImageView imGrade1;
    @Bind(R.id.im_grade2)
    ImageView imGrade2;
    @Bind(R.id.im_grade3)
    ImageView imGrade3;
    @Bind(R.id.im_grade4)
    ImageView imGrade4;
    @Bind(R.id.im_grade5)
    ImageView imGrade5;
    @Bind(R.id.tv_people)
    EditText tvPeople;
    @Bind(R.id.tv_phone)
    EditText tvPhone;
    @Bind(R.id.tv_provin)
    TextView tvProvin;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.tv_class)
    TextView tvClass;
    @Bind(R.id.et_mount)
    EditText etMount;
    @Bind(R.id.tv_pack_mount)
    EditText tvPackMount;
    @Bind(R.id.mswitch)
    Switch mswitch;
    @Bind(R.id.tv_freight)
    TextView tvFreight;
    @Bind(R.id.et_content)
    CountEdithundredText etContent;
    @Bind(R.id.btn_submit)
    Button btnSubmit;

    @Bind(R.id.iv_license)
    ImageView iv_license;
    @Bind(R.id.iv_outside)
    ImageView iv_outside;

    @Bind(R.id.iv_workshop)
    ImageView iv_workshop;

    @Bind(R.id.tv_company_name)
    TextView tv_company_name;
    @Bind(R.id.tv_company_http)
    EditText tv_company_http;
    private ClassGoodsAdapter classadapter;
    private View rootview;
    String Cat_id;

    @Override
    public int initContentView() {
        rootview = View.inflate(this, R.layout.activity_shop_factory, null);
        return R.layout.activity_shop_factory;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("店铺信息");
        tvRightBlue.setText("进入店铺");
        tvRightBlue.setVisibility(View.VISIBLE);
        shop_id = getIntent().getStringExtra("shop_id");
        classadapter = new ClassGoodsAdapter(this);
        mPresenter.getToken(token);
        //店铺信息
        mPresenter.getDataFromServer(shop_id);
        //获取商品分类
        mPresenter.getDataFromServer();
    }

    private int IMAGE_PICKER = 0x00002;

    @OnClick({R.id.img_back, R.id.tv_freight, R.id.tv_right_blue, R.id.tv_edit_iv, R.id.tv_provin, R.id.tv_class, btn_submit,R.id.tv_look_top})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_freight:  //运费模板
                intent = new Intent(this, CarriageActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_right_blue:  //进入店铺
                intent = new Intent(this, ShopInfoActivity.class);
                intent.putExtra("shop_id", shop_id);
                intent.putExtra("type", "selluser");
                startActivity(intent);
                break;
            case R.id.tv_edit_iv: //更改招牌图
                ImagePicker imagePicker2 = ImagePicker.getInstance();
                imagePicker2.setMultiMode(false);
                imagePicker2.setCrop(false);
                Intent intent2 = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent2, IMAGE_PICKER);
                break;
            case R.id.tv_provin:
                UiUtils.HideKeyboard(tvProvin);
                selectAddress();
                break;
            case R.id.tv_class:
                showPopupWindowClass();
                break;
            case btn_submit:
                mPresenter.saveNetShopData(path, tvPeople.getText().toString(), tvPhone.getText().toString(), province, city, etAddress.getText().toString(),
                        Cat_id, tvPackMount.getText().toString(), etMount.getText().toString(), mswitch.isChecked() ? "1" : "0", "", "", "",
                        etContent.getText().toString(),tv_company_http.getText().toString());
                break;
            case R.id.tv_look_top:
                intent = new Intent(this, ShopGradeActivity.class);
                startActivity(intent);
                break;
        }
    }

    String path;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> lists = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                path = lists.get(0).path;
                Glide.with(this).load
                        (path)
                        .into(ivImg);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:

                break;
            case R.id.tv_ok:
                Cat_id = classlists.get(position).getCat_id();
                tvClass.setText(classlists.get(position).getCat_name_mobile());

                break;
            case R.id.ll_blank:

                break;
        }
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
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

    private PopupWindow mPopWindow;
    int position;

    private void showPopupWindowClass() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_class_layout_two, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        TextView tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        LinearLayout llBecak = (LinearLayout) contentView.findViewById(R.id.ll_blank);
        final ListView lv_left = (ListView) contentView.findViewById(R.id.lv_left);
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                classadapter.setCheckedPosition(i);

            }
        });
        tv_cancel.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
        llBecak.setOnClickListener(this);

        lv_left.setAdapter(classadapter);
        //显示PopupWindow
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void getData(EditShopBean.DataBean beass) {
        Glide
                .with(this)
                .load(Constants.Base_URL + beass.getShop_real_pic())
                .error(R.mipmap.bg_img)
                .into(ivImg);
        Glide
                .with(this)
                .load(Constants.Base_URL + beass.getFactory_pic())
                .error(R.mipmap.bg_img)
                .into(iv_outside);
        Glide
                .with(this)
                .load(Constants.Base_URL + beass.getPlant_pic())
                .error(R.mipmap.bg_img)
                .into(iv_workshop);

        Glide
                .with(this)
                .load(Constants.Base_URL + beass.getLicense())
                .error(R.mipmap.bg_img)
                .into(iv_license);
//        TextView tv_company_name;
//        @Bind(R.id.tv_company_http)
//        TextView tv_company_http;
        tv_company_http.setText(beass.getCompany_website());
        tv_company_name.setText(beass.getCompany_name());
        tvShipName.setText(beass.getShop_name());
        tvShopType.setText(beass.getShop_type_name());
        tvPeople.setText(beass.getContacts_name());
        tvProvin.setText(beass.getProvince() + "-" + beass.getCity());
        etAddress.setText(beass.getAddress());
        tvClass.setText(beass.getCategory_name());
        Cat_id = beass.getCategory_id();
        etMount.setText(beass.getRetail_amount());
        tvPackMount.setText(beass.getBasic_amount());
        mswitch.setChecked(beass.getAllow_return().endsWith("0") ? false : true);
        etContent.setText(beass.getDescription());
        tvPhone.setText(beass.getUser_mobile());

        /* 店铺等级*/
        EditShopBean.DataBean.Shop_grade grade = beass.getShop_grade();
        if (grade != null) {
            if (grade.getType().equals("G1")) {
                imGrade1.setImageResource(R.mipmap.icon_heart);
                imGrade2.setImageResource(R.mipmap.icon_heart);
                imGrade3.setImageResource(R.mipmap.icon_heart);
                imGrade4.setImageResource(R.mipmap.icon_heart);
                imGrade5.setImageResource(R.mipmap.icon_heart);
                goneOrVisible(grade.getNum());
            } else if (grade.getType().equals("G2")) {
                imGrade1.setImageResource(R.mipmap.icon_dimon);
                imGrade2.setImageResource(R.mipmap.icon_dimon);
                imGrade3.setImageResource(R.mipmap.icon_dimon);
                imGrade4.setImageResource(R.mipmap.icon_dimon);
                imGrade5.setImageResource(R.mipmap.icon_dimon);
                goneOrVisible(grade.getNum());
            } else if (grade.getType().equals("G3")) {
                imGrade1.setImageResource(R.mipmap.icon_silvercrown);
                imGrade2.setImageResource(R.mipmap.icon_silvercrown);
                imGrade3.setImageResource(R.mipmap.icon_silvercrown);
                imGrade4.setImageResource(R.mipmap.icon_silvercrown);
                imGrade5.setImageResource(R.mipmap.icon_silvercrown);
                goneOrVisible(grade.getNum());
            } else if (grade.getType().equals("G4")) {
                imGrade1.setImageResource(R.mipmap.icon_goldcrown);
                imGrade2.setImageResource(R.mipmap.icon_goldcrown);
                imGrade3.setImageResource(R.mipmap.icon_goldcrown);
                imGrade4.setImageResource(R.mipmap.icon_goldcrown);
                imGrade5.setImageResource(R.mipmap.icon_goldcrown);
                goneOrVisible(grade.getNum());
            }
        }
    }

    //店铺复等级
    public void goneOrVisible(int num) {
        if (num == 1) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.GONE);
            imGrade3.setVisibility(View.GONE);
            imGrade4.setVisibility(View.GONE);
            imGrade5.setVisibility(View.GONE);
        } else if (num == 2) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.VISIBLE);
            imGrade3.setVisibility(View.GONE);
            imGrade4.setVisibility(View.GONE);
            imGrade5.setVisibility(View.GONE);
        } else if (num == 3) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.VISIBLE);
            imGrade3.setVisibility(View.VISIBLE);
            imGrade4.setVisibility(View.GONE);
            imGrade5.setVisibility(View.GONE);
        } else if (num == 4) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.VISIBLE);
            imGrade3.setVisibility(View.VISIBLE);
            imGrade4.setVisibility(View.VISIBLE);
            imGrade5.setVisibility(View.GONE);
        } else if (num == 5) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.VISIBLE);
            imGrade3.setVisibility(View.VISIBLE);
            imGrade4.setVisibility(View.VISIBLE);
            imGrade5.setVisibility(View.VISIBLE);
        }
    }

    private String province = "山东省";
    private String city = "临沂市";
    private String district = "兰山区";

    /**
     * 选取城市区域
     */
    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(this)
                .textSize(14)
                .title(" ")
                .titleBackgroundColor("#f5f5f5")
                .confirTextColor("#248bfe")
                .cancelTextColor("#248bfe")
                .province(province)
                .city(city)
                .district(district)
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                province = citySelected[0];
                //城市
                city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                tvProvin.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });
    }

    //获取分类成功
    List<AddGoodsBean.DataBean> classlists = new ArrayList<>();

    @Override
    public void getClassSucceed(AddGoodsBean beass) {
        classlists.addAll(beass.getData());
        classadapter.addData(classlists);
    }

    @Override
    public void succreed() {
        finish();
    }
}
