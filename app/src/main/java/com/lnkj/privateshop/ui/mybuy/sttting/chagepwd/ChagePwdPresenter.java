package com.lnkj.privateshop.ui.mybuy.sttting.chagepwd;

import android.support.annotation.NonNull;

import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class ChagePwdPresenter implements ChagePwdContract.Presenter {
        private ChagePwdContract.View mView;
    private String token;
    public ChagePwdPresenter(ChagePwdContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull ChagePwdContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    @Override
    public void upladData(String pwd, String pwdto, String pwdthree) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("oldPassword",pwd);
        map.put("newPassword",pwdto);
        map.put("confirmPassword",pwdthree);
        meApi.chagePassWord(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                         String info = object.optString("info");
                            if (status==1){
                                mView.toLoging();
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
