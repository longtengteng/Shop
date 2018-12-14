package com.lnkj.privateshop.ui.mybuy.userinfo;

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

public class EditUserPresenter implements EditUserContract.Presenter {
        private EditUserContract.View mView;
    private String token;
    public EditUserPresenter(EditUserContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull EditUserContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    @Override
    public void upladData(String pic, String name) {
        if (TextUtils.isEmpty(pic)&&TextUtils.isEmpty(name)){
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("token", token);
     if (!TextUtils.isEmpty(name)){
         builder.addFormDataPart("nickname", name);
        }

        if (!TextUtils.isEmpty(pic)){
         File file = new File(pic);//filePath 图片地址
         RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
         builder.addFormDataPart("photo", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        mView.showLoading();
        meApi.editUser(parts)
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
                            if (status==1){
                                mView.upladSuccreed();
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
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });







    }
}
