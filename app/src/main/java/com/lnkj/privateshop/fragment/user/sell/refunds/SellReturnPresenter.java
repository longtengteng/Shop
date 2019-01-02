package com.lnkj.privateshop.fragment.user.sell.refunds;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.MyApplication;
import com.lnkj.privateshop.entity.SellReutrnBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class SellReturnPresenter implements SellReturnContract.Presenter {
    SellReturnContract.View view;
    private String token;

    public SellReturnPresenter(SellReturnContract.View view) {
        this.view = view;

    }

    @Override
    public void attachView(@NonNull SellReturnContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void getReturnOrder(String shop_state, int p) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("shop_state", shop_state);
        map.put("p", p);
        String shop_id = PreferencesUtils.getString(MyApplication.mContext, "shop_id");
        map.put("shop_id", shop_id);

        System.out.println("shop_state" + shop_state);
        System.out.println("shop_id" + shop_id);
        System.out.println("p" + p);
        System.out.println("token" + token);

        view.showLoading();
        meApi.sellReturnDetails(map)
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
                            if (status == 1) {
                                SellReutrnBean beass = JSON.parseObject(data, SellReutrnBean.class);
                                view.succree(beass);
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
                        view.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void OkRoNoGoods(String order_sn, String refund_type, String order_goods_id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("order_sn", order_sn);
        map.put("refund_type", refund_type);
        map.put("order_goods_id", order_goods_id);
        view.showLoading();
        meApi.sellReturnOkOrNO(map)
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
                            if (status == 1) {
                                view.OkRoNoGOodssuccree();
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
    public void ReceiveGoods(String order_sn, String pwd) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("order_sn", order_sn);
        map.put("password", pwd);
        view.showLoading();
        meApi.ReceiveGoods(map)
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
                            if (status == 1) {
                                view.OnDeleteOrderSucreed();
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
    public void onUrged(String order_sn) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("refund_id", order_sn);
        view.showLoading();
        meApi.onUrged(map)
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
                            if (status == 1) {

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
    public void onDeleteOrder(String order_sn) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("order_sn", order_sn);
        view.showLoading();
        meApi.onDeleteOrder(map)
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
                            if (status == 1) {
                                view.OnDeleteOrderSucreed();
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


}
