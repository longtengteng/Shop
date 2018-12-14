package com.lnkj.privateshop.ui.mybuy.sttting.cashpwd;

import android.support.annotation.NonNull;
import android.text.TextUtils;

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
 * Created by Administrator on 2017/8/9 0009.
 */

public class ChageChagPwdPresenter implements ChageChagPwdContract.Presenter {
        private ChageChagPwdContract.View mView;
    private String token;
    public ChageChagPwdPresenter(ChageChagPwdContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull ChageChagPwdContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    @Override
    public void upladData(String number, String name, String pic) {
        if (TextUtils.isEmpty(number)){
            ToastUtil.showToast("请填写正确的身份证号");
            return;
        }else if (TextUtils.isEmpty(name)){
            ToastUtil.showToast("请输入姓名");
            return;
        }else if (TextUtils.isEmpty(pic)){
            ToastUtil.showToast("请选择手持身份证照片");
            return;
        }
        mView.showLoading();
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("token", token);
        builder.addFormDataPart("idcard_num", number);
        builder.addFormDataPart("truename", name);
//        System.out.println("token:"+token);
//        System.out.println("idecard_num"+number);
//        System.out.println("truename"+name);
        File file = new File(pic);//filePath 图片地址
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("photo", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
        List<MultipartBody.Part> parts = builder.build().parts();
        meApi.verifyIdCard(parts)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据id", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
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
                        LLog.d("数据错误222", throwable.toString() + "");
                    }
                });


    }

    @Override
    public void setWithdrawPassword(String setWithdrawPassword, String confirm_withdraw_password) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("withdraw_password", setWithdrawPassword);
        map.put("confirm_withdraw_password", confirm_withdraw_password);
        mView.showLoading();
        meApi.setWithdrawPassword(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("数据222", data);
                        mView.hideLoading();
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                mView.setWithdrawPasswordSuccreed();
                            }else {
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

                        LLog.d("数据错误222", throwable.toString() + "");
                    }
                });

    }
}
