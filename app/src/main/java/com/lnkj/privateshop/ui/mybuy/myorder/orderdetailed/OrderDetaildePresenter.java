package com.lnkj.privateshop.ui.mybuy.myorder.orderdetailed;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.OrderDetailderBean;
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
 * Created by Administrator on 2017/8/7 0007.
 */

public class OrderDetaildePresenter implements OrderDetaildeContract.Presenter {

    OrderDetaildeContract.View mView;
    private String token;
    public OrderDetaildePresenter(OrderDetaildeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull OrderDetaildeContract.View view) {

    }

    @Override
    public void detachView() {

    }
    @Override
    public void initView() {

    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }

    @Override
    public void getOrderFromServer(String orderId) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("order_sn",orderId);
        meApi.getOrderDetailde(map)
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
                            if (status==1){
                                OrderDetailderBean beass = JSON.parseObject(data,OrderDetailderBean.class);
                                mView.getOrderSucceed(beass);
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
                        mView.hideLoading();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void onOkGoods(String orderId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",4);
        changeOrder(map,orderId,4);
    }

    @Override
    public void onRemindDelivery(String orderId) {
        //提醒发货
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",101);
        changeOrder(map,orderId,101);
    }

    @Override
    public void onDeleteOrder(String orderId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",100);
        changeOrder(map,orderId,100);
    }

    @Override
    public void onCancelorder(String orderId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",0);
        changeOrder(map,orderId,0);
    }

    public void changeOrder(Map<String,Object> map, String orderid ,final int flag){
        map.put("token",token);
        map.put("order_sn",orderid);
        System.out.println("token:"+token);
        System.out.println("order_sn:"+orderid);

        meApi.changeOrder(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){

                            if (flag==100){
                                mView.remoOrder();
                            }else if(flag==101){

                            }else {
                             mView.okGoods();
                            }

                            }
//                            if (flag==100){
//                                mView.remoOrder(orderlist,position,info);
//                            }else if (flag==4){
//                                mView.okGoods();
//                            }else if (flag==101){
//                                mView.remindDelivery(info);
//                            }
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
