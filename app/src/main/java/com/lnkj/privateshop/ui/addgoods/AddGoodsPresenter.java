package com.lnkj.privateshop.ui.addgoods;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.AddGoodsBean;
import com.lnkj.privateshop.entity.ColorBean2;
import com.lnkj.privateshop.entity.GoodsAttriBean;
import com.lnkj.privateshop.entity.GoodsEditBean;
import com.lnkj.privateshop.entity.SizeBean;
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
 * Created by Administrator on 2017/7/31 0031.
 */

public class AddGoodsPresenter implements AddGoodsContract.Presenter {
    private AddGoodsContract.View mView;
    private String token;

    public AddGoodsPresenter(AddGoodsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull AddGoodsContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void login(String phone, String pwd) {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void getColorFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        meApi.getColor(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据color", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                ColorBean2 beass = JSON.parseObject(data, ColorBean2.class);

                                mView.getColorSucceed(beass);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mView.hideLoading();
                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void putNewColor(final String etcolor) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("value", etcolor);
        map.put("spec_id", 1);
        meApi.putNewColor(map)
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
                                JSONObject datas = object.getJSONObject("data");
                                String id = datas.getString("id");
                                String img = datas.getString("color_img");
                                mView.putNewColorSucceed(etcolor, id, img);
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
                        mView.hideLoading();
                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });


    }

    @Override
    public void putNewSize(final String size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("value", size);
        map.put("spec_id", 2);
        meApi.putNewColor(map)
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
                                JSONObject datas = object.getJSONObject("data");
                                String id = datas.getString("id");
                                mView.putNewSizeSucceed(size, id);
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
                        mView.hideLoading();
                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void getSizeFromServer() {
        mView.showLoading();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        meApi.getsize(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据size", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                SizeBean beass = JSON.parseObject(data, SizeBean.class);
                                mView.getSizeSucceed(beass);
                            }

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

    @Override
    public void getClassFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        meApi.getGoodsCategory(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据222class", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                AddGoodsBean beass = JSON.parseObject(data, AddGoodsBean.class);
                                mView.getClassSucceed(beass);

                            }

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
    public void getAttriFromServer(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("cat_id", id);
        meApi.getAttri(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据idattri", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                GoodsAttriBean beass = JSON.parseObject(data, GoodsAttriBean.class);
                                mView.getAttriSucceed(beass);
                            }

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
    public void AddGoods(String title, String content, String goodsprice, String goodspickprice, String color, String size,
                         String cat_id, String jsonText, String weight, String p, ArrayList<ImageItem> images, boolean mswitch) {
//        if (TextUtils.isEmpty(title)){
//            ToastUtil.showToast("请输入标题");
//        }else if (TextUtils.isEmpty(content)){
//            ToastUtil.showToast("请输入商品描述");
//        }else if (TextUtils.isEmpty(goodsprice)){
//            ToastUtil.showToast("请输入拿货价格");
//        }else if (TextUtils.isEmpty(goodspickprice)){
//            ToastUtil.showToast("请输入打包价格");
//        }else if (TextUtils.isEmpty(color)){
//            ToastUtil.showToast("请输入颜色");
//        }else if (TextUtils.isEmpty(size)){
//            ToastUtil.showToast("请选择尺码");
//        }else if (TextUtils.isEmpty(cat_id)){
//            ToastUtil.showToast("请选择分类");
////        }else if (TextUtils.isEmpty(jsonText)){
////            ToastUtil.showToast("请选择商品属性");
//        }else if (TextUtils.isEmpty(weight)){
//            ToastUtil.showToast("请选择商品重量");
//        }else
//            if (images.size()<2){
//            ToastUtil.showToast("保证图片不少于2张");
//        }




        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("token", token);
        builder.addFormDataPart("goods_name", title + "");
        builder.addFormDataPart("goods_description", content + "");
        builder.addFormDataPart("shop_price", goodsprice + "");
        builder.addFormDataPart("pack_price", goodspickprice + "");
//        builder.addFormDataPart("attr", jsonText);
        builder.addFormDataPart("cat_id", cat_id + "");
        builder.addFormDataPart("weight", weight + "");
        builder.addFormDataPart("img_is_default", String.valueOf(p) + "");
        builder.addFormDataPart("is_made", mswitch ? "1" : "0");

//        System.out.println("img_is_default："+ String.valueOf(p) + "");

        String ps[] =  p.split(",");
        for (int i = 0; i < ps.length; i++) {
            builder.addFormDataPart("img_is_default[" + i + "]", ps[i]);
        }
        try {
            String[] colors = color.split(",");
            for (int i = 0; i < colors.length; i++) {
                builder.addFormDataPart("color[" + i + "]", colors[i]);
            }
        } catch (Exception e) {
            builder.addFormDataPart("color[0]", "");
        }
        try {
            String[] sizes = size.split(",");
            for (int i = 0; i < sizes.length; i++) {
                builder.addFormDataPart("size[" + i + "]", sizes[i]);
            }
        } catch (Exception e) {
            builder.addFormDataPart("size[0]", "");
        }
        try {
            builder.addFormDataPart("attr", jsonText);
        } catch (Exception e) {
            builder.addFormDataPart("attr", "");
        }

        for (int i = 0; i < images.size(); i++) {
            File file = new File(images.get(i).path);//filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("photo[" + i + "]", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名

        }
        List<MultipartBody.Part> parts = builder.build().parts();
        mView.showLoading();
        meApi.addGoods(parts)
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
                                mView.addGoodsSucceed();
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
    public void editGoods(String title, String content, String goodsprice, String goodspickprice,
                          String color, String size, String cat_id, String jsonText,
                          String weight, String p, ArrayList<ImageItem> images, String goods_id, boolean mswitch,String del_img_id) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("token", token);
        builder.addFormDataPart("goods_id", goods_id + "");
        builder.addFormDataPart("goods_name", title + "");
        builder.addFormDataPart("goods_description", content + "");
        builder.addFormDataPart("shop_price", goodsprice + "");
        builder.addFormDataPart("pack_price", goodspickprice + "");
        builder.addFormDataPart("cat_id", cat_id + "");
        builder.addFormDataPart("weight", weight + "");
        builder.addFormDataPart("is_made", mswitch ? "1" : "0");
        builder.addFormDataPart("del_img_id", del_img_id);
//        System.out.println("del_img_id:"+del_img_id);
//        builder.addFormDataPart("img_is_default", p + "");
        String[] colors = color.split(",");
        String[] sizes = size.split(",");
        String ps[] =  p.split(",");
        for (int i = 0; i < ps.length; i++) {
            builder.addFormDataPart("img_is_default[" + i + "]", ps[i]);
            System.out.println("img_is_default[" + i + "]" + ps[i]);
        }

//        String[] jsonTexts = jsonText.split(",");
        for (int i = 0; i < colors.length; i++) {
            builder.addFormDataPart("color[" + i + "]", colors[i]);
            System.out.println("color[" + i + "]" + colors[i]);
        }
        for (int i = 0; i < sizes.length; i++) {
            builder.addFormDataPart("size[" + i + "]", sizes[i]);
            System.out.println("size[" + i + "]" + sizes[i]);
        }
//        for (int i = 0; i < jsonTexts.length; i++) {
        builder.addFormDataPart("attr", jsonText);
//        System.out.println("attr:" + jsonText);
////        }
//        System.out.println(token);
//        System.out.println(goods_id);
//        System.out.println(title);
//        System.out.println(content);
//        System.out.println(goodsprice);
//        System.out.println(goodspickprice);
//        System.out.println(cat_id + "");
//        System.out.println(weight + "");
//        System.out.println(mswitch + "");
//        System.out.println(String.valueOf(p) + "");

        for (int i = 0; i < images.size(); i++) {
            if (!images.get(i).name.equals("http")) {
                File file = new File(images.get(i).path);//filePath 图片地址
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("photo[" + i + "]", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
                System.out.println("photo[" + i + "]");
            }
            System.out.println("___________");
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        mView.showLoading();
        meApi.addGoods(parts)
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
                                mView.addGoodsSucceed();
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
                        mView.hideLoading();
                        LLog.d("数据错误222", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void getGoods(String goodsid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("goods_id", goodsid);
//        System.out.println("__________________");
//        System.out.println(token);
//        System.out.println(goodsid);
        meApi.getGoodsEdit(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据goods", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                GoodsEditBean beass = JSON.parseObject(data, GoodsEditBean.class);
                                mView.getGoodsSuccreed(beass);
                            }

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
    public void deleteImg(String imgid, final int i) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("img_id", imgid);
        meApi.deleteImg(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                mView.deleteImgSuccreed(i);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });
    }


}
