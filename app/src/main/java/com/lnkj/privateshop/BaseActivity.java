package com.lnkj.privateshop;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.lnkj.privateshop.api.home.HomeApi;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.injector.component.ApplicationComponent;
import com.lnkj.privateshop.injector.mudules.ActivityModule;
import com.lnkj.privateshop.utils.PreferencesUtils;

import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;


/**
 * Created by zjh 2016-8-17
 */
public abstract class BaseActivity extends FragmentActivity {

    protected Dialog progressDialog;// 加载框
    protected InputMethodManager manager;

    public BaseActivity baseActivity;
    Subscription mSubscription;
    @Inject
    HomeApi api;
    private String longitude;
    private String latitude;
    public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getApplicationComponent().inject(this);
        initTheme();
        super.onCreate(savedInstanceState);
        baseActivity = BaseActivity.this;
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        token = PreferencesUtils.getString(MyApplication.getInstance(), "token");
        setContentView(initContentView());
        setDialog();
        initInjector();
        initUiAndListener();
        AppManager.getAppManager().addActivity(this);

    }


    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public int UnreadMsgCount = 0;
    public boolean is_bogin2;

    @Override
    protected void onResume() {
        super.onResume();
        UnreadMsgCount = 0;
        is_bogin2 = PreferencesUtils.getBoolean(this, "is_bogin");
        if (is_bogin2) {
            if (DemoHelper.getInstance().isLoggedIn()) {
                Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
                for (String key : conversations.keySet()) {
                    EMConversation value = conversations.get(key);
                    UnreadMsgCount = UnreadMsgCount + value.getUnreadMsgCount();
                }
            }
        }else {
            UnreadMsgCount = 0;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initTheme() {
        int theme;
        try {
            theme = getPackageManager().getActivityInfo(getComponentName(), 0).theme;
        } catch (PackageManager.NameNotFoundException e) {
            return;
        }
        setTheme(theme);
    }

    /**
     * 设置登陆等待框
     */
    private void setDialog() {
        progressDialog = new Dialog(BaseActivity.this, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog_commom);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        TextView msg = (TextView) progressDialog
                .findViewById(R.id.id_tv_loadingmsg);
        msg.setText("拼命加载中...");
    }

    /**
     * 设置view
     */
    public abstract int initContentView();

    /**
     * 注入Injector
     */
    public abstract void initInjector();

    /**
     * init UI && Listener
     */
    public abstract void initUiAndListener();

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 隐藏软键盘
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }


    /**
     * onConfigurationChanged
     * the package:android.content.res.Configuration.
     *
     * @param newConfig, The new device configuration.
     *                   当设备配置信息有改动（比如屏幕方向的改变，实体键盘的推开或合上等）时，
     *                   并且如果此时有activity正在运行，系统会调用这个函数。
     *                   注意：onConfigurationChanged只会监测应用程序在AnroidMainifest.xml中通过
     *                   android:configChanges="xxxx"指定的配置类型的改动；
     *                   而对于其他配置的更改，则系统会onDestroy()当前Activity，然后重启一个新的Activity实例。
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 检测屏幕的方向：纵向或横向
        if (this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            //当前为横屏， 在此处添加额外的处理代码
        } else if (this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT) {
            //当前为竖屏， 在此处添加额外的处理代码
        }
        //检测实体键盘的状态：推出或者合上
        if (newConfig.hardKeyboardHidden
                == Configuration.HARDKEYBOARDHIDDEN_NO) {
            //实体键盘处于推出状态，在此处添加额外的处理代码
        } else if (newConfig.hardKeyboardHidden
                == Configuration.HARDKEYBOARDHIDDEN_YES) {
            //实体键盘处于合上状态，在此处添加额外的处理代码
        }
    }


    public interface DialogDeleteClickListen {
        void okListenner();

        void cancleListener();
    }

    public void hiddenInput(Activity ctx) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();// isOpen若返回true，则表示输入法打开
        if (isOpen) {
            if (ctx.getCurrentFocus() != null)
                ((InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(ctx.getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
