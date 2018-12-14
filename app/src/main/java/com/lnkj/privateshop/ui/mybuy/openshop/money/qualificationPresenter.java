package com.lnkj.privateshop.ui.mybuy.openshop.money;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class qualificationPresenter implements qualificationContract.Presenter {
        private qualificationContract.View mView;
    private String token;
    public qualificationPresenter(qualificationContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull qualificationContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    /**
     * 实体店
     * @param shop_id
     * @param name
     * @param http
     * @param path_zhizhao
     * @param path_factory
     * @param chenjia
     */
    @Override
    public void getDataFromServer(String shop_id,String name,String http,String path_zhizhao,String path_factory,String chenjia) {
        if (TextUtils.isEmpty(name)){
            ToastUtil.showToast("请输入公司名称");
        }else if (TextUtils.isEmpty(path_zhizhao)){
            ToastUtil.showToast("选择营业执照");
        }else if (TextUtils.isEmpty(path_factory)){
            ToastUtil.showToast("请选择工厂/店铺外景");
        }else if (TextUtils.isEmpty(chenjia)){
            ToastUtil.showToast("请选择工厂/店铺内景");
        }else {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);//表单类型
            builder.addFormDataPart("token", token);
            builder.addFormDataPart("shop_id", shop_id);
            builder.addFormDataPart("shop_type", String.valueOf(2));
            builder.addFormDataPart("company_name", name);
            builder.addFormDataPart("company_website", http);
            File file = new File(path_zhizhao);//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("license", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名

            File file2 = new File(path_factory);//filePath 图片地址
            RequestBody imageBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
            builder.addFormDataPart("factory_pic", file2.getName(), imageBody2);//"imgfile"+i 后台接收图片流的参数名

            File file3 = new File(chenjia);//filePath 图片地址
            RequestBody imageBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), file3);
            builder.addFormDataPart("plant_pic", file2.getName(), imageBody3);//"imgfile"+i 后台接收图片流的参数名

            List<MultipartBody.Part> parts = builder.build().parts();
            mView.showLoading();
            meApi.putShopZiZhi(parts)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String data) {
                            mView.hideLoading();
                            LLog.d("数据", data);
                            try {
                                JSONObject object = new JSONObject(data);
                                int status = object.getInt("status");
                                String info = object.getString("info");
                                if (status == 1) {
                                    mView.succeed();
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
                            mView.hideLoading();
                            LLog.d("数据错误222", throwable.toString() + "");
                        }
                    });

        }

    }

    /**
     * 工厂店
     * @param shop_id
     * @param name
     * @param http
     * @param path_zhizhao
     */
    @Override
    public void putQealityFromServer(String shop_id, String name, String http, String path_zhizhao) {
        if (TextUtils.isEmpty(name)){
            ToastUtil.showToast("请输入营业执照名称");
        }else if (TextUtils.isEmpty(path_zhizhao)){
            ToastUtil.showToast("选择营业执照照片");
        }else {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);//表单类型
            builder.addFormDataPart("token", token);
            builder.addFormDataPart("shop_type", String.valueOf(1));
            builder.addFormDataPart("company_name", name);
            builder.addFormDataPart("reg_number", http);
            builder.addFormDataPart("shop_id", shop_id);
            File file = new File(path_zhizhao);//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("license", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名

            List<MultipartBody.Part> parts = builder.build().parts();
            mView.showLoading();
            meApi.putShopZiZhi(parts)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String data) {
                            mView.hideLoading();
                            LLog.d("数据", data);
                            try {
                                JSONObject object = new JSONObject(data);
                                int status = object.getInt("status");
                                String info = object.getString("info");
                                if (status == 1) {
                                    mView.succeed();
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
                            mView.hideLoading();
                            LLog.d("数据错误222", throwable.toString() + "");
                        }
                    });

        }

    }


    @Override
    public void setToken(String token) {
        this.token=token;
    }


}
