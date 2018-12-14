package com.lnkj.privateshop.ui.mybuy.address.addaddress;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.smatrAddressBean;
import com.lnkj.privateshop.utils.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddressTwoActivity extends BaseActivity implements AddressTowContract.View{
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
    @Bind(R.id.et_people)
    EditText etPeople;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_region)
    TextView tvRegion;
    @Bind(R.id.tv_street)
    TextView tvStreet;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.checkbox)
    CheckBox checkbox;
    @Bind(R.id.ll_moren)
    LinearLayout ll_moren;
    @Bind(R.id.et_identify)
            EditText et_identify;
    AddressTowPresenter mPresenter = new AddressTowPresenter(this);
    private String addressid;

    @Override
    public int initContentView() {
        return R.layout.activity_address_two;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvRightBlue.setText("保存");
        tvRightBlue.setVisibility(View.VISIBLE);
        tvRightBlue.setTextColor(Color.parseColor("#ff7722"));
        tvTitle.setText("新增收货地址");
        mPresenter.getToken(token);
        addressid = getIntent().getStringExtra("addressid");
    if (!TextUtils.isEmpty(addressid)){
        mPresenter.getDataFromServer(addressid);
        tvTitle.setText("编辑收货地址");
    }
    }

    @Override
    public void initUiAndListener() {
        ll_moren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    checkbox.setChecked(!checkbox.isChecked());

            }
        });
    }
    @Override
    public void setClickable(){
        tvRightBlue.setClickable(false);
    }
    @Override
    public void setClickalbeTrue(){
        tvRightBlue.setClickable(true);
    }
    @OnClick({R.id.img_back, R.id.tv_right_blue, R.id.tv_region, R.id.tv_street,R.id.btn_identify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right_blue:

                if (!TextUtils.isEmpty(addressid)){
             mPresenter.checkData(addressid, etPeople.getText().toString(),etPhone.getText().toString(),tvRegion.getText().toString(),tvStreet.getText().toString(),etAddress.getText().toString(),checkbox.isChecked());
                }else {
             mPresenter.checkData("", etPeople.getText().toString(),etPhone.getText().toString(),tvRegion.getText().toString(),tvStreet.getText().toString(),etAddress.getText().toString(),checkbox.isChecked());
                }
                break;
            case R.id.tv_region:
                UiUtils.HideKeyboard(tvRegion);
                selectAddress();
                break;
            case R.id.tv_street:
                break;
            case R.id.btn_identify:
            mPresenter.identifyAddress(et_identify.getText().toString());
                break;
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
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                tvRegion.setText(province.trim() + "-" + city.trim()+ "-"+district.trim());
            }
        });
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
    public void showFragment(int position) {

    }

    @Override
    public void updatePraiseView(int dif, int position) {

    }

    @Override
    public void PullLoadMoreComplete() {

    }

    @Override
    public void saveSuccess() {
        finish();
    }

    @Override
    public void identifyAddressSuccess(smatrAddressBean adressBean) {
       smatrAddressBean.DataBean dataBean =  adressBean.getData();
        etPeople.setText(dataBean.getName());
        etPhone.setText(dataBean.getMobile());
        etAddress.setText(dataBean.getAddress());
        String region_name1 = "";
        String region_name2 = "";
        String region_name3 = "";
        try {
            region_name1 = dataBean.getThr_address().get(0).getRegion_name();
            region_name2 = dataBean.getThr_address().get(1).getRegion_name();
            region_name3 = dataBean.getThr_address().get(2).getRegion_name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvRegion.setText(region_name1 + "-" + region_name2+ "-"+region_name3);


    }

    @Override
    public void setView(String name, String address, String province, String city, String district, String mobile, String is_default) {
//        mPresenter.checkData(etPeople.getText().toString(),etPhone.getText().toString(),tvRegion.getText().toString(),tvStreet.getText().toString(),etAddress.getText());
                etPeople.setText(name);
        etPhone.setText(mobile);
//        etPeople.setText(mobile);
        etAddress.setText(address);
        tvRegion.setText(province+"-"+city+"-"+district);

        this.province=province;
        this.city=city;
        this.district=district;

        if (is_default.equals("1")){
            checkbox.setChecked(true);
        }else {
            checkbox.setChecked(false);
        }
    }

}
