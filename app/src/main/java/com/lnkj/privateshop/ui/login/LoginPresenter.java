package com.lnkj.privateshop.ui.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.api.login.LoginApi;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.ease.domain.db.DemoDBManager;
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

/**
 * Created by WRJ on 2016/8/30.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";
    private LoginContract.View mView;
    private Context mContext;
    private LoginApi loginApi;
    private Subscription subscriptSpan;

    @Inject
    public LoginPresenter(Context context, LoginApi loginApi) {
        this.mContext = context;
        this.loginApi = loginApi;

    }

    @Override
    public void initView() {
        mView.initView();
        mView.setTitle("登录");
    }

    @Override
    public void login(final String username, final String pwd) {
        if (TextUtils.isEmpty(username)) {
            ToastUtil.showToast("请输入用户名");
            return;
        } else if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showToast("请输入密码");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", pwd);
//        map.put("username", "admin");
//        map.put("password","123456");
        // mView.showLoading();
        mView.showLoading();
        subscriptSpan = loginApi.login(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String userBean) {

                        LLog.d(TAG, userBean);
                        try {
                            JSONObject object = new JSONObject(userBean);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                UserBean userbean = JSON.parseObject(userBean, UserBean.class);
                                UserBean.DataBean bean = userbean.getData();
                                PreferencesUtils.putString(mContext, "user_name", bean.getUser_name());
                                PreferencesUtils.putString(mContext, "birthday", bean.getBirthday());
                                PreferencesUtils.putString(mContext, "disabled", bean.getDisabled());
                                PreferencesUtils.putString(mContext, "email", bean.getEmail());
                                PreferencesUtils.putString(mContext, "frozen_money", bean.getFrozen_money());
                                PreferencesUtils.putString(mContext, "token", bean.getToken());
                                PreferencesUtils.putString(mContext, "head_pic", bean.getHead_pic());
                                PreferencesUtils.putString(mContext, "mobile", bean.getMobile());
                                PreferencesUtils.putString(mContext, "nickname", bean.getNickname());
                                PreferencesUtils.putString(mContext, "password", pwd + "");
                                PreferencesUtils.putString(mContext, "username", username + "");
                                PreferencesUtils.putString(mContext, "user_id", bean.getUser_id());
                                PreferencesUtils.putString(mContext, "sex", bean.getSex());
                                PreferencesUtils.putString(mContext, "shop_id", bean.getShop_id());
                                PreferencesUtils.putInt(mContext, "is_shop", bean.getIs_shop());
                                PreferencesUtils.putInt(mContext, "isPay_password", bean.getPay_password());
                                PreferencesUtils.putBoolean(mContext, "is_bogin", true);
                                PreferencesUtils.putString(mContext, "emchat_username", bean.getEmchat_username());
                                PreferencesUtils.putString(mContext, "emchat_password", bean.getEmchat_password());
                                String emchat_username = bean.getEmchat_username();
                                String emchat_password = bean.getEmchat_password();
                                mView.hideLoading();
                                mView.toMain();
//                                    loginEmob(emchat_username,emchat_password);
                            } else {
                                mView.hideLoading();
                                ToastUtil.showToast(info);

                            }
//                                mView.toMain();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        // mView.hideLoading();

                    }
                });

    }

    @Override
    public void login_three(String login_type, String parent_id, String open_id, String nickname, String head_pic) {
        Map<String, Object> map = new HashMap<>();
        map.put("login_type", login_type);
        map.put("parent_id", parent_id);
        map.put("open_id", open_id);
        map.put("nickname", nickname);
        map.put("head_pic", head_pic);
        // mView.showLoading();
        mView.showLoading();
        subscriptSpan = loginApi.loginThree(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String userBean) {

                        LLog.d(TAG, userBean);
                        try {
                            JSONObject object = new JSONObject(userBean);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                UserBean userbean = JSON.parseObject(userBean, UserBean.class);
                                UserBean.DataBean bean = userbean.getData();
                                PreferencesUtils.putString(mContext, "user_name", bean.getUser_name());
                                PreferencesUtils.putString(mContext, "birthday", bean.getBirthday());
                                PreferencesUtils.putString(mContext, "disabled", bean.getDisabled());
                                PreferencesUtils.putString(mContext, "email", bean.getEmail());
                                PreferencesUtils.putString(mContext, "frozen_money", bean.getFrozen_money());
                                PreferencesUtils.putString(mContext, "token", bean.getToken());
                                PreferencesUtils.putString(mContext, "head_pic", bean.getHead_pic());
                                PreferencesUtils.putString(mContext, "mobile", bean.getMobile());
                                PreferencesUtils.putString(mContext, "nickname", bean.getNickname());
                                //   PreferencesUtils.putString(mContext, "password", pwd + "");
                                //   PreferencesUtils.putString(mContext, "username", nickname + "");
                                PreferencesUtils.putString(mContext, "user_id", bean.getUser_id());
                                PreferencesUtils.putString(mContext, "sex", bean.getSex());
                                PreferencesUtils.putString(mContext, "shop_id", bean.getShop_id());
                                PreferencesUtils.putInt(mContext, "is_shop", bean.getIs_shop());
                                PreferencesUtils.putInt(mContext, "isPay_password", bean.getPay_password());
                                PreferencesUtils.putBoolean(mContext, "is_bogin", true);
                                PreferencesUtils.putString(mContext, "emchat_username", bean.getEmchat_username());
                                PreferencesUtils.putString(mContext, "emchat_password", bean.getEmchat_password());
                                String emchat_username = bean.getEmchat_username();
                                String emchat_password = bean.getEmchat_password();
                                mView.hideLoading();
                                mView.toMain();
//                                    loginEmob(emchat_username,emchat_password);
                            } else {
                                mView.hideLoading();
                                ToastUtil.showToast(info);

                            }
//                                mView.toMain();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        // mView.hideLoading();

                    }
                });


    }

    public void loginEmob(final String userName, String password) {
//        userName =  userName.substring(0,
//                userName.length() - 1).trim();
//        password = password.substring(1,
//                password.length()).trim();
        DemoDBManager.getInstance().closeDB();

        // reset current user name before login
        DemoHelper.getInstance().setCurrentUserName(userName);

        EMClient.getInstance().login(userName, password, new EMCallBack() {
            @Override
            public void onSuccess() {

                String userPic = PreferencesUtils.getString(mContext, "head_pic");
                String NickName = PreferencesUtils.getString(mContext, "nickname");


                DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(NickName);
                DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(Constants.Base_IMG_URL + userPic);
                DemoHelper.getInstance().setCurrentUserName(userName); // 环信Id

                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                LLog.d("success", "登录聊天服务器成功！-login-success-");
//                System.out.println("登录聊天服务器成功");
            }

            @Override
            public void onError(int i, String s) {
                LLog.d("error", "emob-登录聊天服务器失败！-error-" + i + "--msg--" + s);
//                System.out.println("登录聊天服务器失败" + i + "--msg--" + s);
            }

            @Override
            public void onProgress(int i, String s) {
                LLog.d("error", "emob-登录聊天服务器！-error-" + i + "--msg--" + s);
            }
        });
    }
//    private static final String TAG = "LoginPresenter";

    @Override
    public void attachView(@NonNull LoginContract.View view) {
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
