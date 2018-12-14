package com.lnkj.privateshop.ui.mybuy.myorder.commentaries;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ShopBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lzy.imagepicker.bean.ImageItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
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

public class OrderCommetPresenter implements OrderCommetContract.Presenter{
    OrderCommetContract.View view;
    private String token;
    public OrderCommetPresenter(OrderCommetContract.View view) {
        this.view=view;

    }
    @Override
    public void getDataFromServer(String order_sn,String content,int rank,int express_rank,int service_rank, ArrayList<ImageItem> images) {
        if (TextUtils.isEmpty(content)){
            ToastUtil.showToast("评价内容");
            return;
        }

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("token", token);
        builder.addFormDataPart("order_sn", order_sn);
        builder.addFormDataPart("content", content);
        builder.addFormDataPart("rank", String.valueOf(rank));
        builder.addFormDataPart("express_rank", String.valueOf(express_rank));
        builder.addFormDataPart("service_rank", String.valueOf(service_rank));
        System.out.println();
        for (int i = 0; i <images.size() ; i++) {
            File file = new File(images.get(i).path);//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("photo["+i+"]", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        view.showLoading();

       meApi.publishCommentaries(parts)
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
                            if (status==1){
                                 view.succreed();
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
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });

    }

    @Override
    public void getShopInfo(String shopid) {
        view.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("shop_id",shopid);
        meApi.getShopInfo(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("店铺详情数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status==1){
                                ShopBean beass = JSON.parseObject(data,ShopBean.class);
                                view.getShopInfoSuccreed(beass);
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
    public void attachView(@NonNull OrderCommetContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }




}
