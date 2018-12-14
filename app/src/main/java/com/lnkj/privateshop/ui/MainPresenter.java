package com.lnkj.privateshop.ui;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.lnkj.privateshop.AppManager;
import com.lnkj.privateshop.api.login.LoginApi;
import com.lnkj.privateshop.utils.ToastUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by lnkj on 2017/7/9 0009.
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mMainView;
    private Subscription subscriptSpan;
    private LoginApi loginApi;
    private Activity mContext;

    @Inject
    public MainPresenter( Activity mContext) {
        this.loginApi = loginApi;
        this.mContext = mContext;
    }

    @Override
    public void initView() {
        mMainView.initView();
    }

    @Override
    public void exist() {
        if (isCanExit()) {
            AppManager.getAppManager().AppExit(mContext);
        }
    }

    @Override
    public boolean isLogin() {
        return false;
    }

    private long mExitTime = 0;

    private boolean isCanExit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtil.showToast("再按一次退出应用");
            mExitTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }

    @Override
    public void attachView(@NonNull MainContract.View view) {
        mMainView = view;
    }

    @Override
    public void detachView() {
        if (subscriptSpan != null && !subscriptSpan.isUnsubscribed()) {
            subscriptSpan.unsubscribe();
        }
        mMainView = null;
    }
}
