package com.lnkj.privateshop.ui.mybuy.sell.order.shopdata;

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

public class ShopDataEeitPresenter implements ShopDataEditContract.Presenter {
    ShopDataEditContract.View view;
    private String token;

    public ShopDataEeitPresenter(ShopDataEditContract.View view) {
        this.view = view;

    }

    @Override
    public void getDataFromServer(String shop_id) {
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
                            String info = object.getString("info");
                            if (status == 1) {
                                EditShopBean beass = JSON.parseObject(data, EditShopBean.class);
                                view.getData(beass.getData());
                            } else {
                                ToastUtil.showToast(info);
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

    @Override
    public void saveNetShopData(String shop_real_pic, String contacts_name, String user_mobile, String province, String city, String address,
                                String category_id, String basic_amount, String retail_amount, String allow_return, String market_name,
                                String floor_number, String room_number,String description,String company_website) {
        view.showLoading();
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("token", token);
        builder.addFormDataPart("contacts_name", contacts_name+"");
        builder.addFormDataPart("user_mobile", user_mobile+"");
        builder.addFormDataPart("province", province+"");
        builder.addFormDataPart("city", city+"");
        builder.addFormDataPart("address", address+"");
        builder.addFormDataPart("category_id", category_id+"");
        builder.addFormDataPart("basic_amount", basic_amount+"");
        builder.addFormDataPart("retail_amount", retail_amount+"");
        builder.addFormDataPart("allow_return", allow_return+"");
        builder.addFormDataPart("market_name", market_name+"");
        builder.addFormDataPart("floor_number", floor_number+"");
        builder.addFormDataPart("room_number", room_number+"");
        builder.addFormDataPart("description", description+"");
        builder.addFormDataPart("company_website", company_website+"");

        if (!TextUtils.isEmpty(shop_real_pic)) {
            File file = new File(shop_real_pic);//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("shop_real_pic", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        meApi.saveEditShop(parts)
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
                             if (status==1){
                                 view.succreed();
                             }
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


    @Override
    public void attachView(@NonNull ShopDataEditContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }



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


}
