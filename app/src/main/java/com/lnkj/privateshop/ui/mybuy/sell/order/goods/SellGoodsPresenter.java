package com.lnkj.privateshop.ui.mybuy.sell.order.goods;

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
 * Created by Administrator on 2017/8/7 0007.
 */

public class SellGoodsPresenter implements SellGoodsContract.Presenter {

    SellGoodsContract.View mView;
    private String token;
    public SellGoodsPresenter(SellGoodsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull SellGoodsContract.View view) {

    }

    @Override
    public void detachView() {

    }
    @Override
    public void initView() {

    }

    @Override
    public void downSold(String goodsid,String flag) {
        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("token",token);
        map.put("goods_id",goodsid);
        if (flag.equals("down")){
        map.put("goods_state",0);
        }else {
            map.put("goods_state",1);
        }
        meApi.getdownGoods(map)
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
                            mView.succeed();
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
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });

    }

    @Override
    public void getToken(String token) {
        this.token=token;
    }


}
