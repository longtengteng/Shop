package com.lnkj.privateshop.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.ease.domain.db.DemoDBManager;
import com.lnkj.privateshop.fragment.goodscar.GoodsFragment;
import com.lnkj.privateshop.fragment.home.HomeFragment;
import com.lnkj.privateshop.fragment.looking.LookingFragment;
import com.lnkj.privateshop.fragment.near.NearFrament;
import com.lnkj.privateshop.fragment.user.MapActivity;
import com.lnkj.privateshop.fragment.user.UserFragment;
import com.lnkj.privateshop.ui.addgoods.AddGoodsActivity;
import com.lnkj.privateshop.ui.ease.EaseConversationListActivity;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.ui.seachgoods.SeachGOodsPublicActivity;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.TagAliasOperatorHelper;
import com.lnkj.privateshop.utils.ToastUtil;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lnkj.privateshop.utils.TagAliasOperatorHelper.ACTION_SET;
import static com.lnkj.privateshop.utils.TagAliasOperatorHelper.sequence;

public class MainActivity extends BaseActivity implements
        RadioGroup.OnCheckedChangeListener, MainContract.View, LocationSource, AMapLocationListener, AMap.OnCameraChangeListener, GeocodeSearch.OnGeocodeSearchListener {

    @Inject
    public MainPresenter mainPresenter = new MainPresenter(MainActivity.this);
    @Bind(R.id.fragment_content)
    FrameLayout mFrameLayout;
    @Bind(R.id.bottom_main)
    RadioGroup mRadioGroup;
    @Bind(R.id.radio_home)
    RadioButton radioHome;
    @Bind(R.id.radio_near)
    RadioButton radioNear;
    @Bind(R.id.radio_looking)
    RadioButton radioLooking;
    @Bind(R.id.radio_goods)
    RadioButton radioGoods;
    @Bind(R.id.radio_me)
    RadioButton radioMe;
    @Bind(R.id.img_add)
    ImageView imgAdd;
    RelativeLayout meRlseach;
    @Bind(R.id.tv_count)
    TextView tv_count;
    @Bind(R.id.rl_seach)
    RelativeLayout rl_seach;
    HomeFragment homeFragment; //首页
    NearFrament nearFrament; //圈子
    LookingFragment lookingFragment; //找货
    TestFragment goodsFragment;
    // GoodsFragment goodsFragment; //进货车
    //    MyFragment  myFragment;// 我的
    UserFragment myUserFragment;
    ImageView imageView;
    Boolean is_bogin;
    private GoodsClick mBroadcastReceiver;
    @Bind(R.id.ll_local)
    LinearLayout ll_local;
    @Bind(R.id.tv_local)
    TextView tv_local;
    @Bind(R.id.map)
    MapView map;

    /*地图相关*/
    private AMap aMap;//地图对象
    private AMapLocationClient mLocationClient;   //发起定位
    private AMapLocationClientOption mLocationOption;
    LocationSource.OnLocationChangedListener mListener;

    GeocodeSearch geocoderSearch;//你编码对象

    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private String address = "";
    private String province = "";
    private String city = "";
    private String country = "";
    private boolean isLocation = true;


    @Override
    protected void onResume() {
        super.onResume();
        is_bogin = PreferencesUtils.getBoolean(this, "is_bogin");
        if (UnreadMsgCount > 0) {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(UnreadMsgCount + "");
        } else {
            tv_count.setVisibility(View.GONE);
        }
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        map.onCreate(savedInstanceState);
    }

    private void processExtraData(Intent intent) {
        type = intent.getStringExtra("type");
        if (!TextUtils.isEmpty(type) && type.equals("addshop")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(goodsFragment);
            fragmentTransaction.hide(nearFrament);
//        fragmentTransaction.hide(myFragment);
            fragmentTransaction.hide(myUserFragment);
            fragmentTransaction.hide(lookingFragment);
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.commit();
            //    goodsFragment.initView();
            radioGoods.setChecked(true);
        } else if (!TextUtils.isEmpty(type) && type.equals("openshop")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(myUserFragment);
            fragmentTransaction.hide(nearFrament);
            fragmentTransaction.hide(goodsFragment);
            fragmentTransaction.hide(lookingFragment);
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.commit();
            radioMe.setChecked(true);
        } else if (!TextUtils.isEmpty(type) && type.equals("goodCar")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(goodsFragment);
            fragmentTransaction.hide(nearFrament);
            fragmentTransaction.hide(myUserFragment);
            fragmentTransaction.hide(lookingFragment);
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.commit();
            radioGoods.setChecked(true);
        } else if (!TextUtils.isEmpty(type) && type.equals("home")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(homeFragment);
            fragmentTransaction.hide(nearFrament);
            fragmentTransaction.hide(myUserFragment);
            fragmentTransaction.hide(lookingFragment);
            fragmentTransaction.hide(goodsFragment);
            fragmentTransaction.commit();
            radioHome.setChecked(true);
        } else if (!TextUtils.isEmpty(type) && type.equals("goods_class")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(lookingFragment);
            fragmentTransaction.hide(nearFrament);
            fragmentTransaction.hide(myUserFragment);
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(goodsFragment);
            fragmentTransaction.commit();
            radioLooking.setChecked(true);
        }
        ll_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), MapActivity.class);
                startActivityForResult(i, 66);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 66) {
            city = data.getStringExtra("city");
            tv_local.setText(city);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processExtraData(intent);

    }

    public static void startAcitity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }


    @Override
    public void initInjector() {
        DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    String type;

    @Override
    public void initUiAndListener() {

        mBroadcastReceiver = new GoodsClick();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.liuniu.bubble.click");
        registerReceiver(mBroadcastReceiver, intentFilter);

        meRlseach = (RelativeLayout) findViewById(R.id.rl_reach);
        imageView = (ImageView) findViewById(R.id.imageView);
        mainPresenter.attachView(this);
        ButterKnife.bind(this);


        mainPresenter.initView();
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddGoodsActivity.startActivity(MainActivity.this);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_bogin) {
                    startActivity(new Intent(MainActivity.this, EaseConversationListActivity.class));
                } else {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
        meRlseach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (is_bogin) {
                startActivity(new Intent(MainActivity.this, SeachGOodsPublicActivity.class));
//                } else {
//                    ToastUtil.showToast("您还没有登录，请去登录");
//                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
            }
        });

        //获取地图对象
        aMap = map.getMap();
        //设置显示定位按钮 并且可以点击
        UiSettings settings = aMap.getUiSettings();
        //设置定位监听
        aMap.setLocationSource(this);
        // 是否显示定位按钮
        if (isLocation) {
            settings.setMyLocationButtonEnabled(true);
        }
        // 是否可触发定位并显示定位层
        aMap.setMyLocationEnabled(true);
        geocoderSearch = new GeocodeSearch(this);
        //设置地图拖动监听
        aMap.setOnCameraChangeListener(this);
        //逆编码监听事件
        geocoderSearch.setOnGeocodeSearchListener(this);
        //定位的小图标
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.point_map));
        myLocationStyle.radiusFillColor(android.R.color.transparent);
        myLocationStyle.strokeColor(android.R.color.transparent);
        aMap.setMyLocationStyle(myLocationStyle);

        aMap.moveCamera(CameraUpdateFactory.zoomTo(17f)); //缩放比例

        //开始定位
        initLocation();
    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式
        //Hight_Accuracy为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId) {
            case R.id.radio_home:
                fragmentTransaction.hide(lookingFragment);
                fragmentTransaction.hide(goodsFragment);
                fragmentTransaction.hide(nearFrament);
//                fragmentTransaction.hide(myFragment);
                fragmentTransaction.hide(myUserFragment);
                fragmentTransaction.show(homeFragment);
                rl_seach.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_near:
                fragmentTransaction.hide(lookingFragment);
                fragmentTransaction.hide(goodsFragment);
                fragmentTransaction.hide(homeFragment);
//                fragmentTransaction.hide(myFragment);
                fragmentTransaction.hide(myUserFragment);
                fragmentTransaction.show(nearFrament);
                ToastUtil.showToast("开发中，敬请期待！");
                rl_seach.setVisibility(View.GONE);
                break;
            case R.id.radio_looking:
                fragmentTransaction.hide(homeFragment);
                fragmentTransaction.hide(goodsFragment);
                fragmentTransaction.hide(nearFrament);
//                fragmentTransaction.hide(myFragment);
                fragmentTransaction.hide(myUserFragment);
                fragmentTransaction.show(lookingFragment);
                ToastUtil.showToast("开发中，敬请期待！");
                rl_seach.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_goods:

                try {
                    if (is_bogin) {
                        fragmentTransaction.hide(lookingFragment);
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(nearFrament);
                        fragmentTransaction.hide(myUserFragment);
                        fragmentTransaction.show(goodsFragment);
                        ToastUtil.showToast("开发中，敬请期待！");
                        rl_seach.setVisibility(View.GONE);
                    } else {
                        ToastUtil.showToast("您还没有登录，请去登录");
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(nearFrament);
                        fragmentTransaction.hide(myUserFragment);
                        fragmentTransaction.hide(lookingFragment);
                        fragmentTransaction.hide(goodsFragment);
                        radioHome.setChecked(true);
                        rl_seach.setVisibility(View.VISIBLE);

                        return;
                    }
                } catch (Exception e) {
                }
                break;
            case R.id.radio_me:
                try {
                    if (is_bogin) {
                        fragmentTransaction.hide(lookingFragment);
                        fragmentTransaction.hide(goodsFragment);
                        fragmentTransaction.hide(nearFrament);
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.show(myUserFragment);
                        rl_seach.setVisibility(View.GONE);
                    } else {
                        ToastUtil.showToast("您还没有登录，请去登录");
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(nearFrament);
                        fragmentTransaction.hide(myUserFragment);
                        fragmentTransaction.hide(lookingFragment);
                        fragmentTransaction.hide(goodsFragment);
                        radioHome.setChecked(true);
                        rl_seach.setVisibility(View.VISIBLE);

                        return;
                    }
                } catch (Exception e) {
                }
                break;
        }
        fragmentTransaction.commit();
    }

    final int REQUEST_WRITE = 1;//申请权限的请求码

    //写数据
    public void writeToSdCard() {
        PgyUpdateManager.setIsForced(false); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.register(MainActivity.this,
                new UpdateManagerListener() {

                    @Override
                    public void onUpdateAvailable(final String result) {

                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("更新")
                                .setMessage("你有新版本确定更新吗？")
                                .setNegativeButton(
                                        "确定",
                                        new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                startDownloadTask(
                                                        MainActivity.this,
                                                        appBean.getDownloadURL());
                                            }
                                        }).setPositiveButton("取消", null).
                                show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                    }
                });
    }

    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode==REQUEST_WRITE&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
//            writeToSdCard();
//        }
//    }
    @Override
    public void initView() {
//        PgyUpdateManager.setIsForced(true); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
//        PgyUpdateManager.register(this,"com.mydomain.fileprovider");
        final int REQUEST_WRITE = 1;//申请权限的请求码

//        if(Build.VERSION.SDK_INT>=23){
//            //判断是否有这个权限
//            if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//                //2、申请权限: 参数二：权限的数组；参数三：请求码
//                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE);
//            }else {
//                writeToSdCard();
//            }
//        } else{
//            writeToSdCard();
//        }
        writeToSdCard();
        if (!DemoHelper.getInstance().isLoggedIn()) {
            //登錄換新
            String emchat_username = PreferencesUtils.getString(MainActivity.this, "emchat_username");
            String emchat_password = PreferencesUtils.getString(MainActivity.this, "emchat_password");
            if (!TextUtils.isEmpty(emchat_username) && !TextUtils.isEmpty(emchat_password)) {
                loginEmob(emchat_username, emchat_password);
            }
        }
        //极光推送
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        sequence++;
        tagAliasBean.action = ACTION_SET;
        tagAliasBean.isAliasAction = true;
        tagAliasBean.alias = PreferencesUtils.getString(this, "user_id");
        TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(), sequence, tagAliasBean);


        mRadioGroup.setOnCheckedChangeListener(this);
        homeFragment = new HomeFragment();
        nearFrament = new NearFrament();
        lookingFragment = new LookingFragment();
        goodsFragment = new TestFragment();
        myUserFragment = new UserFragment();

/*
        goodsFragment.setmClickListener(new GoodsFragment.OrderClickListener() {
            @Override
            public void onOrderCilck() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(lookingFragment);
                fragmentTransaction.hide(goodsFragment);
                fragmentTransaction.hide(nearFrament);
                fragmentTransaction.hide(myUserFragment);
                fragmentTransaction.show(homeFragment);
                rl_seach.setVisibility(View.VISIBLE);
                fragmentTransaction.commit();
                radioHome.setChecked(true);
            }
        });*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_content, homeFragment);
        fragmentTransaction.add(R.id.fragment_content, nearFrament);
        fragmentTransaction.add(R.id.fragment_content, lookingFragment);
        fragmentTransaction.add(R.id.fragment_content, goodsFragment);
        fragmentTransaction.add(R.id.fragment_content, myUserFragment);

        fragmentTransaction.hide(nearFrament);
        fragmentTransaction.hide(myUserFragment);
        fragmentTransaction.hide(lookingFragment);
        fragmentTransaction.hide(goodsFragment);
        fragmentTransaction.show(homeFragment);
        fragmentTransaction.commit();
        radioHome.setChecked(true);
        myUserFragment.setOnSwitckClickListener(new UserFragment.OnSwitckClickListener() {
            @Override
            public void onSwitckClick(int index) {
                nearFrament.setVisibility(index);
                if (index == 1) {
                    radioGoods.setVisibility(View.VISIBLE);
                    imgAdd.setVisibility(View.GONE);
                } else if (index == 0) {
                    radioGoods.setVisibility(View.VISIBLE);
                    imgAdd.setVisibility(View.GONE);
                } else {
                    radioGoods.setVisibility(View.VISIBLE);
                    imgAdd.setVisibility(View.GONE);
                }
            }
        });

        int is_shop = PreferencesUtils.getInt(this, "is_shop");
        if (is_shop == 0) {
            radioGoods.setVisibility(View.VISIBLE);
            imgAdd.setVisibility(View.GONE);
        } else {
            int state = PreferencesUtils.getInt(this, "state", Constants.STATE_BUY);
            if (state == Constants.STATE_SELLER) {
                radioGoods.setVisibility(View.VISIBLE);
                imgAdd.setVisibility(View.GONE);
            } else {
                radioGoods.setVisibility(View.VISIBLE);
                imgAdd.setVisibility(View.GONE);
            }
        }
        processExtraData(getIntent());

    }

    @Override
    public void showFragment(int position) {

    }

    @Override
    public void showLoginUi() {

    }

    @Override
    public void updatePraiseView(int dif, int position) {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    long waitTime = 2000;
    long touchTime = 0;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= waitTime) {
            //让Toast的显示时间和等待时间相同
            ToastUtil.showToast("再按一次退出");
            touchTime = currentTime;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        map.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();  //获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                amapLocation.getLatitude();      //获取纬度
                amapLocation.getLongitude();    //获取经度
                amapLocation.getAccuracy();        //获取精度信息
                city = amapLocation.getCity();            //城市信息
//                address = amapLocation.address
//                latitude = amapLocation.latitude
//                longitude = amapLocation.longitude
//                tv_address.text = amapLocation.address
                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                mLocationClient.stopLocation();
                //获取定位信息
                //                    val buffer = StringBuffer()
                //                    buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" + amapLocation.getCity() + "" + amapLocation.getProvince() + "" + amapLocation.getDistrict() + "" + amapLocation.getStreet() + "" + amapLocation.getStreetNum());
                //                    ToastUtils.showShort(buffer.toString())

                tv_local.setText(city);
               // ToastUtil.showToast(amapLocation.getProvince()+city+"oooo");
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());

                ToastUtil.showToast("定位失败");

            }
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        LatLng latLng = cameraPosition.target;
        latitude = latLng.latitude;
        longitude = latLng.longitude;
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null && result.getRegeocodeAddress() != null && result.getRegeocodeAddress().getFormatAddress() != null) {
                String addressName = result.getRegeocodeAddress().getFormatAddress(); // 逆转地里编码不是每次都可以得到对应地图上的opi
                address = addressName;
            }
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    public class GoodsClick extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String goodId = intent.getStringExtra("goodId");
            if (!TextUtils.isEmpty(goodId)) {
                Intent i = new Intent(MainActivity.this, GoodsInfoActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("goods_id", goodId);
                startActivity(i);
            }

        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
                int UnreadMsgCount = 0;
                for (String key : conversations.keySet()) {
                    EMConversation value = conversations.get(key);
                    UnreadMsgCount = UnreadMsgCount + value.getUnreadMsgCount();
                }
                if (is_bogin) {
                    if (UnreadMsgCount > 0) {
                        MainActivity.this.tv_count.setVisibility(View.VISIBLE);
                        tv_count.setText(UnreadMsgCount + "");
                    } else {
                        tv_count.setVisibility(View.GONE);
                    }
                } else {
                    tv_count.setVisibility(View.GONE);
                }
            } catch (Exception e) {
            }

        }
    };

    public void loginEmob(final String userName, String password) {
        DemoDBManager.getInstance().closeDB();
        DemoHelper.getInstance().setCurrentUserName(userName);
        EMClient.getInstance().login(userName, password, new EMCallBack() {
            @Override
            public void onSuccess() {

                String userPic = PreferencesUtils.getString(MainActivity.this, "head_pic");
                String NickName = PreferencesUtils.getString(MainActivity.this, "nickname");
                DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(NickName);
                DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(Constants.Base_IMG_URL + userPic);
                DemoHelper.getInstance().setCurrentUserName(userName); // 环信Id

                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                mHandler.sendEmptyMessage(1);
//                Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
//                for (String key : conversations.keySet()) {
//                    EMConversation value = conversations.get(key);
//                    UnreadMsgCount = UnreadMsgCount+ value.getUnreadMsgCount();
//                }
//                if (UnreadMsgCount > 0) {
//                    tv_count.setVisibility(View.VISIBLE);
//                    tv_count.setText(UnreadMsgCount+ "");
//                }else {
//                    tv_count.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onError(int i, String s) {
//                LLog.d("error", "emob-登录聊天服务器失败！-error-" + i + "--msg--" + s);
//                System.out.println("登录聊天服务器失败" + i + "--msg--" + s);
            }

            @Override
            public void onProgress(int i, String s) {
                LLog.d("error", "emob-登录聊天服务器！-error-" + i + "--msg--" + s);
            }
        });
    }
//    private long mExitTime;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if ((System.currentTimeMillis() - mExitTime) > 2000) {
//                Object mHelperUtils;
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                mExitTime = System.currentTimeMillis();
//            } else {
//                moveTaskToBack(true);// 进入后台运行，不关闭程序
//                Intent i = new Intent(Intent.ACTION_MAIN);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.addCategory(Intent.CATEGORY_HOME);
//                startActivity(i);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
