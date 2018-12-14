package com.lnkj.privateshop.fragment.looking;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.lnkj.privateshop.api.me.MeApi;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.utils.LLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class LookingPresenter implements LookingContract.Presenter {

    private Subscription subscriptSpan;
    private MeApi meApi;
    LookingContract.View view;
    @Inject
    public LookingPresenter( LookingContract.View view) {
       this.view = view;
        meApi = new MeApi();
    }
    @Override
    public void getDataFromService() {

        Map<String,Object> map = new HashMap<String, Object>();
        subscriptSpan = meApi.getGoodsCategory(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("数据", "111111");
                        LLog.d("数据222222222", data);
                        LLog.d("数据", "111111");
                        try {
                            JSONObject object = new JSONObject(data);
                              int status = object.getInt("status");
                            if (status==1){
                                Gson gson = new Gson();
                                GoodsCategoryBean beans = gson.fromJson(data, GoodsCategoryBean.class);
                                view.liftData(beans);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }
    @Override
    public void attachView(@NonNull LookingContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void clearAttention(int type) {

    }

}
