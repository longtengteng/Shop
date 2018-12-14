package com.lnkj.privateshop.ui.mybuy.returngoods;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.ReturnGoodsBean;
import com.lnkj.privateshop.entity.TeturnPriceBean;
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

public class ReturnGoodsPresenter implements ReturnGoodsContract.Presenter{
    ReturnGoodsContract.View view;
    private String token;
    public ReturnGoodsPresenter(ReturnGoodsContract.View view) {
        this.view=view;

    }
    @Override
    public void getPutFromServer(String type,String goods_id,String order_goods_id,String description,String order_sn, String reason, ArrayList<ImageItem> images) {
    if (TextUtils.isEmpty(description)){
        ToastUtil.showToast("请填写详细退款理由");
        return;
    }else if (reason.equals("请选择退款原因")){
        ToastUtil.showToast("请选择退款原因");
        return;
    }
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("token", token);
        builder.addFormDataPart("goods_id", goods_id);
        builder.addFormDataPart("order_goods_id", order_goods_id);
        builder.addFormDataPart("description", description);
        builder.addFormDataPart("order_sn", order_sn);
        builder.addFormDataPart("refund_type", type);
        builder.addFormDataPart("reason", reason);


//        System.out.println("goods_id:"+ goods_id);
//        System.out.println("order_goods_id:"+ order_goods_id);
//        System.out.println("description:"+ description);
//        System.out.println("order_sn:"+ order_sn);
//        System.out.println("refund_type:"+ type);
//        System.out.println("reason:"+ reason);
//        System.out.println("token:"+ token);



        for (int i = 0; i <images.size() ; i++) {
//            System.out.println("photo["+i+"]:"+ images.get(i).path);
            File file = new File(images.get(i).path);//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("images["+i+"]", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        view.showLoading();
        meApi.ReturnApplyFor(parts)
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
                        LLog.d("数据错误222", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void getReturn() {
        view.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        meApi.refundReason(map)
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
                            ReturnGoodsBean beass = JSON.parseObject(data,ReturnGoodsBean.class);
                            view.getRetrunSuccreed(beass);
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
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void getReturnPrice(String order_sn, String goods_id) {
        view.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("order_sn",order_sn);
        map.put("goods_id",goods_id);
        meApi.getRturnPrice(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        LLog.d("数据_退款金额", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){
                                TeturnPriceBean bean = JSON.parseObject(data,TeturnPriceBean.class);
                                view.getReturnPrice(bean.getData().getAll_amount());
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
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void attachView(@NonNull ReturnGoodsContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }





}
