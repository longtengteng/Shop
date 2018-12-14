package com.lnkj.privateshop.ui.mybuy.help;

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

public class HelpDetailPresenter implements HelpDetailContract.Presenter{
    HelpDetailContract.View view;

    private String token;
    public HelpDetailPresenter(HelpDetailContract.View view) {
        this.view=view;


    }
    @Override
    public void getPutFromServer(String id) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("article_id",id);

        meApi.helpDetail(map)
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
                            JSONObject dataobject = object.optJSONObject("data");
                            if (status==1){
                             String title =dataobject.optString("title");
                             String content =dataobject.optString("content");
                             String addtime =dataobject.optString("addtime");
                                view.succree(title,content,addtime);

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
                        view.hideLoading();
                    }
                });

    }
    @Override
    public void attachView(@NonNull HelpDetailContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }





}
