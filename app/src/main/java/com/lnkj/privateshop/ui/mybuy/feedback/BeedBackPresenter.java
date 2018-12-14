package com.lnkj.privateshop.ui.mybuy.feedback;

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
 * Created by Administrator on 2017/8/3 0003.
 */

public class BeedBackPresenter implements BeedBackContract.Presenter{
    BeedBackContract.View view;

    private String token;
    public BeedBackPresenter(BeedBackContract.View view) {
        this.view=view;


    }
    @Override
    public void getPutFromServer(String content,String phone) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("mobile",phone+"");
        map.put("comment",content+"");
        System.out.println("token:"+token);
        System.out.println("mobile:"+phone);
        System.out.println("comment:"+content);
        view.showLoading();
        meApi.feedBack(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据2", data);
                        JSONObject object = null;
                        try {
                            object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status==1){
                                view.succree();
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
                    }
                });

    }
    @Override
    public void attachView(@NonNull BeedBackContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }





}
