package com.lnkj.privateshop.ui.mybuy.address;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.AddressListBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class AddressPresenter implements AddressContract.Presenter {
    AddressContract.View view;
    private String token;

    public AddressPresenter(AddressContract.View view) {
        this.view = view;

    }

    @Override
    public void getDataFromServer() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        LLog.d("token", token);
        meApi.getAddressList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        view.PullLoadMoreComplete();
                        LLog.d("数据2", data);
                        JSONObject object = null;
                        try {
                            object = new JSONObject(data);
                            int status = object.getInt("status");

                            AddressListBean beass = JSON.parseObject(data, AddressListBean.class);
                            view.setData(beass);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.hideLoading();
                        view.PullLoadMoreComplete();
                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });

    }

    @Override
    public void deldetAddress(final List<AddressListBean.DataBean> lists, final int position) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("address_id", lists.get(position).getAddress_id());
        meApi.getDeleteAddress(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        view.hideLoading();
                        view.PullLoadMoreComplete();
                        LLog.d("数据2", data);
                        JSONObject object = null;
                        try {
                            object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.optString("info");
                            if (status == 1) {
                                view.deldetAddressSuccress(lists, position);
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
                        view.PullLoadMoreComplete();
                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void SetuAddress(List<AddressListBean.DataBean> lists, int position, int flag) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("address_id", lists.get(position).getAddress_id());
        meApi.getAddDefaultAddress(map)
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
                            String info = object.optString("info");
                            if (status == 1) {
//                                view.deldetAddressSuccress(lists, position);
                                view.setuAddressSuccress();
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
                        view.PullLoadMoreComplete();
                        LLog.d("数据错误11", throwable.toString() + "");
                    }
                });
    }


    @Override
    public void attachView(@NonNull AddressContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getToken(String token) {
        this.token = token;
    }

    @Override
    public void initView() {

    }

    @Override
    public void exist() {

    }


}
