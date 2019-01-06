package com.lnkj.privateshop.ui.mybuy.openshop;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
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

import com.bumptech.glide.Glide;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ClassGoodsAdapter;
import com.lnkj.privateshop.entity.AddGoodsBean;
import com.lnkj.privateshop.entity.EditShopBean;
import com.lnkj.privateshop.entity.GetEditShopBean;
import com.lnkj.privateshop.ui.mybuy.openshop.money.LookImgActivity;
import com.lnkj.privateshop.ui.mybuy.openshop.money.qualificationActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.UiUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class OpenShopFactory2Activity  extends BaseActivity implements OpenShopContract.View,View.OnClickListener {

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.img_head)
    CircleImageView imgHead;
    @Bind(R.id.et_ship_name)
    EditText etShipName;
    @Bind(R.id.et_people)
    EditText etPeople;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_provin)
    TextView tvProvin;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.tv_class)
    TextView tvClass;
    @Bind(R.id.et_mount)
    EditText etMount;
    @Bind(R.id.et_pack_mount)
    EditText etPackMount;
    @Bind(R.id.mswitch)
    Switch mswitch;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.rl_img)
    RelativeLayout rlImg;
    @Bind(R.id.iv_img)
    ImageView ivImg;
    @Bind(R.id.tv_img)
    TextView tvImg;
    private View rootview;
    private ClassGoodsAdapter classadapter;
    private OpenShopPresenter mPresenter = new OpenShopPresenter(this);
    private String path_head ;
    private PopupWindow mPopWindow;
    String Cat_id;
    @Override
    public int initContentView() {
        return R.layout.activity_open_shop_factory2;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        classadapter = new ClassGoodsAdapter(this);
        rootview=View.inflate(this,R.layout.activity_open_shop_factory2,null);
        tvTitle.setText("免费开店");
        mPresenter.getToken(token);
        mPresenter.getDataFromServer();
        mPresenter.getShopInfo();
        etPhone.setText(PreferencesUtils.getString(this,"username"));
    }

    @Override
    public void initUiAndListener() {

    }
    int position;
    //    显示分类选择
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
                position =i;
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
    public void initView() {

    }

    @Override
    public void openSuccree(String Shop_id) {

    }

    @Override
    public void saveEditShopSuccree() {

    }

    @Override
    public void openFactorySuccerr(String Shop_id) {
        Intent intent = new Intent(this,qualificationActivity.class);
        intent.putExtra("type" ,2);
//        finish();
        intent.putExtra("shop_id",Shop_id);
        startActivity(intent);
    }

    @Override
    public void openRealitySuccerr(String Shop_id) {

    }


    //获取分类成功
    List<AddGoodsBean.DataBean> classlists = new ArrayList<>();
    @Override
    public void getClassSucceed(AddGoodsBean beass) {
        classlists.addAll(beass.getData());
        classadapter.addData(classlists);
    }

    @Override
    public void getShopInfoSucceed(EditShopBean.DataBean beans) {
        String shop_name = beans.getShop_name();
        if (!TextUtils.isEmpty( shop_name)){
            etShipName.setText(shop_name);
        }
        String contacts_name = beans.getContacts_name();
        if (!TextUtils.isEmpty( contacts_name)){
            etPeople.setText(contacts_name);
        }
        String user_mobile = beans.getUser_mobile();
        if (!TextUtils.isEmpty( user_mobile)){
            etPhone.setText(user_mobile);
        }
        String province = beans.getProvince();
        String city = beans.getCity();
        if (!TextUtils.isEmpty( province)){
            tvProvin.setText(province+"-"+city);
        }

        String address = beans.getAddress();
        if (!TextUtils.isEmpty( address)){
            etAddress.setText(address);
        }
        String category_name = beans.getCategory_name();
        if (!TextUtils.isEmpty( category_name)){
            tvClass.setText(category_name);
            Cat_id=beans.getCategory_id();
        }
        String basic_amount = beans.getBasic_amount();
        if (!TextUtils.isEmpty( basic_amount)){
            etPackMount.setText(basic_amount);
        }
        String retail_amount = beans.getRetail_amount();
        if (!TextUtils.isEmpty( retail_amount)){
            etMount.setText(retail_amount);
        }
    }

    @Override
    public void getEditShopSucceed(GetEditShopBean.DataBean getEditShopBean) {

    }

    @OnClick({R.id.img_back, R.id.tv_provin, R.id.tv_class, R.id.btn_submit, R.id.rl_img,R.id.tv_look,R.id.ll_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_provin:
                UiUtils.HideKeyboard(tvProvin);
                selectAddress();
                break;
            case R.id.tv_class:
                showPopupWindowClass();
                break;
            case R.id.btn_submit:
                if ("请点击选择".equals(tvProvin.getText().toString())){
                    province = "";
                    city="";
                }
                mPresenter.openFactoryShop(path_head,path,etShipName.getText().toString(),etPeople.getText().toString(),etPhone.getText().toString(),
                        province,city,etAddress.getText().toString(),Cat_id,etMount.getText().toString(),etPackMount.getText().toString(),mswitch.isChecked());
                break;
            case R.id.rl_img:
                ImagePicker imagePicker2 = ImagePicker.getInstance();
                imagePicker2.setMultiMode(false);
                imagePicker2.setCrop(false);
                Intent intent2 = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent2, IMAGE_PICKER);
                break;

            case R.id.tv_look:
                Intent i = new Intent(this,LookImgActivity.class);
                i.putExtra("type","网店");
                startActivity(i);
                break;
            case R.id.ll_head:
                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setMultiMode(false);
                imagePicker.setCrop(true);
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER_HEAD);
                break;
        }
    }
    private int IMAGE_PICKER_HEAD =0x00001;
    private int IMAGE_PICKER =0x00002;
    String  path;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER_HEAD) {
                ArrayList<ImageItem> lists =     (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                path_head = lists.get(0).path;
                Glide.with(this).load
                        (path_head)
                        .into(imgHead);
            } if (data != null && requestCode == IMAGE_PICKER){
                ArrayList<ImageItem> lists =     (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                path = lists.get(0).path;
                Glide.with(this).load
                        (path)
                        .into(ivImg);
                tvImg.setVisibility(View.GONE);
            }
        }
    }
    private String province="山东省";
    private String city="临沂市";
    private String district="兰山区";
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
                tvProvin.setText(province.trim() + "-" + city.trim()+ "-"+district.trim());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
}
