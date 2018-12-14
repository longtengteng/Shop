package com.lnkj.privateshop.fragment.user.myorder;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.OrderAllBean;
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
 * Created by Administrator on 2017/8/7 0007.
 */

public class AllPresenter implements AllContract.Presenter {
    AllContract.View mView;
    private String token;
    public AllPresenter(AllContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull AllContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void clearAttention(int type) {

    }

    @Override
    public void getDataFromService(int p,int status) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("p",p);
        switch (status){
        case 0 :
            map.put("order_status","");
        break;
            case 1:
                map.put("order_status","1");
                break;
            case 2:
                map.put("order_status","2");
                break;
            case 3:
                map.put("order_status","3");
                break;
            case 4:
                map.put("is_comment","0");
                break;
        }
        meApi.getOrder(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            OrderAllBean beass = JSON.parseObject(data,OrderAllBean.class);
                            mView.Scrrueed(beass);
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
    public void getToken(String token) {
        this.token = token;
    }
    @Override
    public void onDeleteOrder(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",100);
        changeOrder(map,orderlist,position,100);
    }


    @Override
    public void onRemindDelivery(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
        //提醒发货
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",101);
        changeOrder(map,orderlist,position,101);
    }



    @Override
    public void onOkGoods(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",4);
        changeOrder(map,orderlist,position,4);
    }

    @Override
    public void onCancelorder(List<OrderAllBean.DataBean.OrderListBean> orderlist, int position) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order_status",0);
        changeOrder(map,orderlist,position,0);
    }


    public void changeOrder(Map<String,Object> map, final List<OrderAllBean.DataBean.OrderListBean> orderlist, final int position, final int flag){
        mView.showLoading();
        map.put("token",token);
        map.put("order_sn",orderlist.get(position).getOrder_sn());
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
                            mView.remoOrder(orderlist,position,info);
                            }else if (flag==4){
                            mView.okGoods(orderlist,position,info);
                            }else if (flag==101){
                                mView.remindDelivery(info);
                            }else if (flag==0){
                                mView.remoOrder(orderlist,position,info);
//                                mView.onCancelorderScuurdde(orderlist,position,info);
                            }

                            }
                            if (flag==4){
                                ToastUtil.showToast("确认收货成功");
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

}
