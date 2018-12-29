package com.lnkj.privateshop.ui.mybuy.openshop;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.AddGoodsBean;
import com.lnkj.privateshop.entity.EditShopBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class OpenShopPresenter implements OpenShopContract.Presenter {
    OpenShopContract.View view;
    private String token;

    public OpenShopPresenter(OpenShopContract.View view) {
        this.view = view;

    }

    /**
     * 获取商品分类
     */
    @Override
    public void getDataFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        meApi.getGoodsCategory(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {

                        LLog.d("数据222", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                AddGoodsBean beass = JSON.parseObject(data, AddGoodsBean.class);
                                view.getClassSucceed(beass);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();

                        LLog.d("数据错误222", throwable.toString() + "");
                    }
                });

    }

    /**
     * 获取店铺信息
     */
    @Override
    public void getShopInfo() {
        view.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        meApi.getEditShopInfo(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                EditShopBean beass = JSON.parseObject(data, EditShopBean.class);
                                view.getShopInfoSucceed(beass.getData());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    //开网店
    @Override
    public void openShop(String shop_type, String district, String path_head, String path, String shop_name, String people, String phone,
                         String province, String city, String address, String lat, String lng) {
        if (TextUtils.isEmpty(path_head)) {
            ToastUtil.showToast("选择店铺头像");
        } else if (TextUtils.isEmpty(path)) {
            ToastUtil.showToast("选择店铺招牌");
        } else {
            view.showLoading();
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);//表单类型
            builder.addFormDataPart("token", token);
            builder.addFormDataPart("shop_type", shop_type + "");
            builder.addFormDataPart("shop_name", shop_name + "");
            builder.addFormDataPart("contacts_name", people + "");
            builder.addFormDataPart("user_mobile", phone + "");
            builder.addFormDataPart("province", province + "");
            builder.addFormDataPart("city", city + "");
            builder.addFormDataPart("country", district);
            builder.addFormDataPart("lat", lat);
            builder.addFormDataPart("lng", lng);
            builder.addFormDataPart("address", address + "");
            File file = new File(path);//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("shop_real_pic", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名

            File file2 = new File(path_head);//filePath 图片地址
            RequestBody imageBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
            builder.addFormDataPart("shop_logo", file2.getName(), imageBody2);//"imgfile"+i 后台接收图片流的参数名

            List<MultipartBody.Part> parts = builder.build().parts();
            meApi.openShop(parts)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String data) {
                            view.hideLoading();
                            LLog.d("数据id", data);
                            try {
                                JSONObject object = new JSONObject(data);
                                int status = object.getInt("status");
                                String info = object.getString("info");
                                if (status == 1) {
                                    JSONObject datas = object.getJSONObject("data");
                                    String shop_id = datas.getString("shop_id");
                                    String shop_type = datas.getString("shop_type");
                                    view.openSuccree(shop_id);
                                }
                                ToastUtil.showToast(info);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                            view.hideLoading();
                            LLog.d("数据错误222", throwable.toString() + "");
                        }
                    });
        }
    }

    //工厂店铺
    @Override
    public void openFactoryShop(String path_head, String path, String shop_name, String people, String phone, String province,
                                String city, String address, String Cat_id, String mount, String pack_mount, boolean is_chane) {

        if (TextUtils.isEmpty(path_head)) {
            ToastUtil.showToast("选择店铺头像");
        } else if (TextUtils.isEmpty(path)) {
            ToastUtil.showToast("选择店铺招牌");
        } else {

            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);//表单类型
            builder.addFormDataPart("token", token);
            builder.addFormDataPart("shop_type", String.valueOf(2) + "");
            builder.addFormDataPart("shop_name", shop_name + "");
            builder.addFormDataPart("contacts_name", people + "");
            builder.addFormDataPart("user_mobile", phone + "");
            builder.addFormDataPart("province", province + "");
            builder.addFormDataPart("city", city + "");
            builder.addFormDataPart("address", address + "");
            builder.addFormDataPart("category_id", Cat_id + "");
            builder.addFormDataPart("retail_amount", mount + "");
            builder.addFormDataPart("basic_amount", pack_mount + "");
            builder.addFormDataPart("allow_return", String.valueOf(is_chane ? 1 : 0) + "");
            File file = new File(path);//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("shop_real_pic", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名

            File file2 = new File(path_head);//filePath 图片地址
            RequestBody imageBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
            builder.addFormDataPart("shop_logo", file2.getName(), imageBody2);//"imgfile"+i 后台接收图片流的参数名

            List<MultipartBody.Part> parts = builder.build().parts();
            view.showLoading();
            meApi.openShop(parts)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String data) {
                            view.hideLoading();
                            LLog.d("数据id", data);
                            try {
                                JSONObject object = new JSONObject(data);
                                int status = object.getInt("status");
                                String info = object.getString("info");
                                if (status == 1) {
                                    JSONObject datas = object.getJSONObject("data");
                                    String shop_id = datas.getString("shop_id");
                                    String shop_type = datas.getString("shop_type");
                                    view.openFactorySuccerr(shop_id);
                                }
                                ToastUtil.showToast(info);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                            view.hideLoading();
                            LLog.d("数据错误222", throwable.toString() + "");
                        }
                    });

        }


    }

    //开实体店
    @Override
    public void openRealityShop(String path_head, String path, String shop_name, String people, String phone, String province,
                                String city, String address, String Cat_id, String mount, String pack_mount, boolean is_chane,
                                String floor, String munnber) {
        if (TextUtils.isEmpty(path_head)) {
            ToastUtil.showToast("选择店铺头像");
        } else if (TextUtils.isEmpty(path)) {
            ToastUtil.showToast("选择店铺招牌");
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);//表单类型
            builder.addFormDataPart("token", token);
            builder.addFormDataPart("shop_type", String.valueOf(1) + "");
            builder.addFormDataPart("shop_name", shop_name + "");
            builder.addFormDataPart("contacts_name", people + "");
            builder.addFormDataPart("user_mobile", phone + "");
            builder.addFormDataPart("province", province + "");
            builder.addFormDataPart("city", city + "");
            builder.addFormDataPart("market_name", address + "");
            builder.addFormDataPart("category_id", Cat_id + "");
            builder.addFormDataPart("retail_amount", mount + "");
            builder.addFormDataPart("floor_number", floor + "");
            builder.addFormDataPart("room_number", munnber + "");
            builder.addFormDataPart("basic_amount", pack_mount + "");
            builder.addFormDataPart("allow_return", String.valueOf(is_chane ? 1 : 0) + "");
            File file = new File(path);//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("shop_real_pic", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
            File file2 = new File(path_head);//filePath 图片地址
            RequestBody imageBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
            builder.addFormDataPart("shop_logo", file2.getName(), imageBody2);//"imgfile"+i 后台接收图片流的参数名
            List<MultipartBody.Part> parts = builder.build().parts();
            view.showLoading();
            meApi.openShop(parts)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String data) {
                            view.hideLoading();
                            LLog.d("数据id", data);
                            try {
                                JSONObject object = new JSONObject(data);
                                int status = object.getInt("status");
                                String info = object.getString("info");
                                if (status == 1) {
                                    JSONObject datas = object.getJSONObject("data");
                                    String shop_id = datas.getString("shop_id");
                                    String shop_type = datas.getString("shop_type");
                                    view.openRealitySuccerr(shop_id);
                                }
                                ToastUtil.showToast(info);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                            view.hideLoading();
                            LLog.d("数据错误222", throwable.toString() + "");
                        }
                    });
        }
    }


    @Override
    public void attachView(@NonNull OpenShopContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void initView() {

    }

    @Override
    public void exist() {

    }


}
