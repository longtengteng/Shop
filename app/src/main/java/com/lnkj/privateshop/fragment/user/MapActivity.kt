package com.lnkj.privateshop.fragment.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.LocationSource
import com.amap.api.maps.model.*
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.RegeocodeQuery
import com.amap.api.services.geocoder.RegeocodeResult
import com.lnkj.privateshop.BaseActivity
import com.lnkj.privateshop.R
import com.lnkj.privateshop.R.id.map
import com.lnkj.privateshop.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_map2.*


class MapActivity : BaseActivity(), LocationSource, AMapLocationListener, AMap.OnCameraChangeListener, GeocodeSearch.OnGeocodeSearchListener {

    private var aMap: AMap? = null            //地图对象

    //定位需要的声明,初始化的配置
    private var mLocationClient: AMapLocationClient? = null            //发起定位
    private var mLocationOption: AMapLocationClientOption? = null    //参数设置
    private var mListener: LocationSource.OnLocationChangedListener? = null            //监听器
    var markerOption: MarkerOptions? = null
    var marker: Marker? = null
    //你编码对象
    private var geocoderSearch: GeocodeSearch? = null

    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private var isFirstLoc = true

    private var latitude: Double? = 0.0
    private var longitude: Double? = 0.0
    private var address: String? = ""
    private var isLocation = true


    override fun onGeocodeSearched(p0: GeocodeResult?, p1: Int) {
    }

    override fun onRegeocodeSearched(result: RegeocodeResult?, rCode: Int) {
        if (rCode == 1000) {
            if (result != null && result.getRegeocodeAddress() != null && result.getRegeocodeAddress().getFormatAddress() != null) {
                val addressName = result.getRegeocodeAddress().getFormatAddress(); // 逆转地里编码不是每次都可以得到对应地图上的opi
                address = addressName
                tv_address.text = addressName
            }
        }
    }

    override fun onCameraChange(p0: CameraPosition?) {
    }

    override fun onCameraChangeFinish(cameraPosition: CameraPosition?) {
        val latLng = cameraPosition?.target;
        latitude = latLng?.latitude
        longitude = latLng?.longitude
        Log.e("latitude", latitude.toString());
        Log.e("longitude", longitude.toString());
        val query = RegeocodeQuery(LatLonPoint(latitude ?: 0.0, longitude
                ?: 0.0), 200f, GeocodeSearch.AMAP)
        geocoderSearch?.getFromLocationAsyn(query)
    }

    override fun onLocationChanged(amapLocation: AMapLocation?) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType()  //获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                amapLocation.getLatitude()      //获取纬度
                amapLocation.getLongitude();    //获取经度
                amapLocation.getAccuracy();        //获取精度信息
                amapLocation.getCity();            //城市信息
//                address = amapLocation.address
//                latitude = amapLocation.latitude
//                longitude = amapLocation.longitude
//                tv_address.text = amapLocation.address
                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置

                //设置缩放级别
                aMap?.moveCamera(CameraUpdateFactory.zoomTo(17f));
                //将地图移动到定位点
                if (!isLocation) {
                    val latlng = LatLng(latitude ?: 0.0, longitude ?: 0.0)
                    aMap?.moveCamera(CameraUpdateFactory.newLatLng(latlng))
                } else {
                    aMap?.moveCamera(CameraUpdateFactory.changeLatLng(LatLng(amapLocation.latitude, amapLocation.longitude)))
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener?.onLocationChanged(amapLocation);
                }
                mLocationClient?.stopLocation()
                //获取定位信息
                //                    val buffer = StringBuffer()
                //                    buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" + amapLocation.getCity() + "" + amapLocation.getProvince() + "" + amapLocation.getDistrict() + "" + amapLocation.getStreet() + "" + amapLocation.getStreetNum());
                //                    ToastUtils.showShort(buffer.toString())


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());

                ToastUtil.showToast("定位失败")

            }
        }
    }

    override fun activate(listener: LocationSource.OnLocationChangedListener?) {
        mListener = listener;
    }

    override fun deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient?.stopLocation();
            mLocationClient?.onDestroy();
        }
    }

    override fun onBackPressed() {
        val intent = Intent().also {
            it.putExtra("latitude", latitude)
            it.putExtra("longitude", longitude)
            it.putExtra("address", address)
        }
        setResult(0, intent)
        // finish()
        super.onBackPressed()
    }

    override fun initUiAndListener() {
        tv_title.text = "地图定位"
        iv_left.setOnClickListener { onBackPressed() }
        tv_right.visibility = View.VISIBLE
        tv_right.text = "完成"
        tv_right.setOnClickListener {
            val intent = Intent().also {
                it.putExtra("latitude", latitude)
                it.putExtra("longitude", longitude)
                it.putExtra("address", address)
            }
            setResult(0, intent)
            finish()
        }

        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)
        address = intent.getStringExtra("longitude")
        isLocation = intent.getBooleanExtra("isLocation", true)

        tv_address.text = address

        //获取地图对象
        aMap = map.getMap();
        //设置显示定位按钮 并且可以点击
        val settings = aMap?.getUiSettings()
        //设置定位监听
        aMap?.setLocationSource(this);
        // 是否显示定位按钮
        if (isLocation) {
            settings?.setMyLocationButtonEnabled(true);
        }
        // 是否可触发定位并显示定位层
        aMap?.setMyLocationEnabled(true);

        geocoderSearch = GeocodeSearch(this)
        //设置地图拖动监听
        aMap?.setOnCameraChangeListener(this);
        //逆编码监听事件
        geocoderSearch?.setOnGeocodeSearchListener(this);
        //定位的小图标
        val myLocationStyle = MyLocationStyle()
        //        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.point_map));
        myLocationStyle.radiusFillColor(android.R.color.transparent);
        myLocationStyle.strokeColor(android.R.color.transparent);
        aMap?.setMyLocationStyle(myLocationStyle);

        aMap?.moveCamera(CameraUpdateFactory.zoomTo(17f)); //缩放比例

        //开始定位
        initLocation();

    }

    override fun initInjector() {
    }

    override fun initContentView(): Int {
        return R.layout.activity_map2


    }

    private fun initLocation() {
        mLocationClient = AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient?.setLocationListener(this)
        //初始化定位参数
        mLocationOption = AMapLocationClientOption()
        //设置定位模式
        //Hight_Accuracy为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption?.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption?.setNeedAddress(true)
        //设置是否只定位一次,默认为false
        mLocationOption?.setOnceLocation(false)
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption?.setWifiActiveScan(true)
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption?.setMockEnable(false)
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption?.setInterval(2000)
        //给定位客户端对象设置定位参数
        mLocationClient?.setLocationOption(mLocationOption)
        //启动定位

        mLocationClient?.startLocation()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        map.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mLocationClient != null) {
            mLocationClient?.stopLocation();
            mLocationClient?.onDestroy();
        }
        map.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        map.onSaveInstanceState(outState)
    }


}