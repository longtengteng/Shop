package com.lnkj.privateshop.ui.login.splash;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.api.login.LoginApi;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.ease.domain.db.DemoDBManager;
import com.lnkj.privateshop.entity.StartAppAdBean;
import com.lnkj.privateshop.entity.UserBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

/**
 * Created by WRJ on 2016/8/30.
 */
public class SplashPresenter implements SplashContract.Presenter {
    private static final String TAG = "SplashPresenter";
    private SplashContract.View mView;
    private Context mContext;
    private LoginApi loginApi;
    private Subscription subscriptSpan;

    @Inject
    public SplashPresenter(Context context, LoginApi loginApi) {
        this.mContext = context;
        this.loginApi = loginApi;
    }
    @Override
    public void initView() {
        mView.initView();

    }

    @Override
    public void getStartAppAd() {
        Map<String, Object> map = new HashMap<>();
        map.put("tooken","1");
        meApi.startAppAd(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("启动页广告", data);
                        mView.hideLoading();
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            if (status == 1) {
                                StartAppAdBean bean = JSON.parseObject(data, StartAppAdBean.class);
                                String url = bean.getData().get(0);
                                mView.get6StartAppAdSucreed(url);
                            }else {
                                next();
                            }
                        } catch (JSONException e) {
                            next();
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        next();
                        LLog.d("启动页广告", throwable.toString() + "");
                    }
                });

    }

    @Override
    public void next() {
        PreferencesUtils.putString(mContext, "token", "");
    //    PreferencesUtils.putInt(mContext, "is_shop", 0);
        Boolean Is_login = PreferencesUtils.getBoolean(mContext, "is_bogin");
        if (Is_login==null){
            PreferencesUtils.putBoolean(mContext, "is_bogin",false);
            mView.toMain();
            return;
        }
        if (!Is_login) {
            mView.toMain();
            return;
        }
        String phone = PreferencesUtils.getString(mContext, "username");
        final String pwd = PreferencesUtils.getString(mContext, "password");
        Map<String, Object> map = new HashMap<>();
        map.put("username", phone);
        map.put("password", pwd);
        LLog.d(TAG, phone);
        LLog.d(TAG, pwd);
        subscriptSpan = loginApi.login(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String userBean) {
                        LLog.d(TAG, userBean);
                        mView.hideLoading();
                        try {
                            JSONObject object = new JSONObject(userBean);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            LLog.d("登录", userBean);
                            if (status == 1) {
                                UserBean userbean = JSON.parseObject(userBean, UserBean.class);
                                UserBean.DataBean bean = userbean.getData();
                                PreferencesUtils.putString(mContext, "user_name", bean.getUser_name());
                                PreferencesUtils.putString(mContext, "birthday", bean.getBirthday());
                                PreferencesUtils.putString(mContext, "disabled", bean.getDisabled());
                                PreferencesUtils.putString(mContext, "email", bean.getEmail());
                                PreferencesUtils.putString(mContext, "frozen_money", bean.getFrozen_money());
                                PreferencesUtils.putString(mContext, "head_pic", bean.getHead_pic());
                                PreferencesUtils.putString(mContext, "mobile", bean.getMobile());
                                PreferencesUtils.putString(mContext, "nickname", bean.getNickname());
                                PreferencesUtils.putString(mContext, "password", pwd);
                                PreferencesUtils.putString(mContext, "user_id", bean.getUser_id());
                                PreferencesUtils.putString(mContext, "sex", bean.getSex());
                                PreferencesUtils.putString(mContext, "shop_id", bean.getShop_id());
                                PreferencesUtils.putString(mContext, "token", bean.getToken());
                                PreferencesUtils.putInt(mContext, "is_shop", bean.getIs_shop());
                                PreferencesUtils.putBoolean(mContext, "is_bogin", true);
                                PreferencesUtils.putInt(mContext,"isPay_password", bean.getPay_password());
//                                String emchat_username = bean.getEmchat_username();
//                                String emchat_password = bean.getEmchat_password();
                                mView.hideLoading();
                                mView.toMain();
//                                loginEmob(emchat_username, emchat_password);

                            } else {
                                mView.toMain();
                                ToastUtil.showToast(info);
                                LLog.d(TAG, "登录失败");
                            }

                        } catch (JSONException e) {
                            LLog.d(TAG, "登录失败11111");
                            mView.toMain();
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mView.toMain();
                        LLog.d(TAG, "登录失败22222");
//                        mView.toMain();
                        // mView.hideLoading();
                    }
                });
    }

    public void loginEmob(final String userName, String password) {
        DemoDBManager.getInstance().closeDB();

        // reset current user name before login
        DemoHelper.getInstance().setCurrentUserName(userName);
        EMClient.getInstance().login(userName, password, new EMCallBack() {
            @Override
            public void onSuccess() {

                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                LLog.e("success", "emob-login-success-");
                System.out.println("登录聊天服务器成功");
                String userPic = PreferencesUtils.getString(mContext,"head_pic");
                String NickName = PreferencesUtils.getString(mContext,"nickname");
                DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(NickName);
                DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(Constants.Base_IMG_URL+userPic);
                DemoHelper.getInstance().setCurrentUserName(userName); // 环信Id

                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
            }

            @Override
            public void onError(int i, String s) {

                System.out.println("登录聊天服务器失败"+ i + "--msg--" + s);
                LLog.e("error", "emob-login-error-" + i + "--msg--" + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    @Override
    public void attachView(@NonNull SplashContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (subscriptSpan != null && !subscriptSpan.isUnsubscribed()) {
            subscriptSpan.unsubscribe();
        }
        mView = null;

    }
}
