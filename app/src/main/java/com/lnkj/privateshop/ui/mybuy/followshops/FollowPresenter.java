package com.lnkj.privateshop.ui.mybuy.followshops;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.entity.FollowShopListBean;
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

public class FollowPresenter implements FollowContract.Presenter {

    FollowContract.View mView;
    private String token;
    public FollowPresenter(FollowContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void attachView(@NonNull FollowContract.View view) {

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
    public void deldetGoods(String goodsid) {
        LLog.d("数据", goodsid);
//        mView.showLoading();
        Map<String,Object> map = new HashMap<String, Object>();
        mView.showLoading();
        map.put("token",token);
        map.put("ids",goodsid);

        meApi.getDeldteShopList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        mView.PullLoadMoreComplete();
                        LLog.d("数据", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("info");
                            if (status==1){
                                mView.deleteSuccess();
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
                        mView.PullLoadMoreComplete();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });
    }

    @Override
    public void getDataFromServer(final int p) {
        Map<String,Object> map = new HashMap<String, Object>();
        mView.showLoading();
        map.put("token",token);
        map.put("p",p);
        meApi.getFollowShopList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        mView.hideLoading();
                        LLog.d("数据111", data);
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info =object.getString("status");
                            FollowShopListBean beass = JSON.parseObject(data,FollowShopListBean.class);

                            if (beass.getData()==null){
                                mView.noData();
                            }else {
                                if (beass.getData().size()==0&&p==1){
                                    mView.noData();
                                }else {
                                    mView.fullData();
                                }
                            }
                            mView.setGoodsCollectData(beass);
                            mView.hideLoading();
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
