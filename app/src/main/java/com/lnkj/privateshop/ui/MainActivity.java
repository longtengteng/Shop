package com.lnkj.privateshop.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.ease.domain.db.DemoDBManager;
import com.lnkj.privateshop.fragment.goodscar.GoodsFragment;
import com.lnkj.privateshop.fragment.home.HomeFragment;
import com.lnkj.privateshop.fragment.looking.LookingFragment;
import com.lnkj.privateshop.fragment.near.NearFrament;
import com.lnkj.privateshop.fragment.user.UserFragment;
import com.lnkj.privateshop.ui.addgoods.AddGoodsActivity;
import com.lnkj.privateshop.ui.ease.EaseConversationListActivity;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.ui.seachgoods.SeachGOodsPublicActivity;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.TagAliasOperatorHelper;
import com.lnkj.privateshop.utils.ToastUtil;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lnkj.privateshop.utils.TagAliasOperatorHelper.ACTION_SET;
import static com.lnkj.privateshop.utils.TagAliasOperatorHelper.sequence;

public class MainActivity extends BaseActivity implements
        RadioGroup.OnCheckedChangeListener, MainContract.View {


    @Inject
    public MainPresenter mainPresenter = new MainPresenter(MainActivity.this);
    @Bind(R.id.fragment_content)
    FrameLayout mFrameLayout;
    @Bind(R.id.bottom_main)
    RadioGroup mRadioGroup;
    @Bind(R.id.radio_home)
    RadioButton radioHome;
    @Bind(R.id.radio_near)
    RadioButton radioNear;
    @Bind(R.id.radio_looking)
    RadioButton radioLooking;
    @Bind(R.id.radio_goods)
    RadioButton radioGoods;
    @Bind(R.id.radio_me)
    RadioButton radioMe;
    @Bind(R.id.img_add)
    ImageView imgAdd;
    RelativeLayout meRlseach;
    @Bind(R.id.tv_count)
    TextView tv_count;
    @Bind(R.id.rl_seach)
    RelativeLayout rl_seach;
    HomeFragment homeFragment; //首页
    NearFrament nearFrament; //圈子
    LookingFragment lookingFragment; //找货
    GoodsFragment goodsFragment; //进货车
    //    MyFragment  myFragment;// 我的
    UserFragment myUserFragment;
    ImageView imageView;
    Boolean is_bogin;
    private GoodsClick mBroadcastReceiver;


    @Override
    protected void onResume() {
        super.onResume();
        is_bogin = PreferencesUtils.getBoolean(this, "is_bogin");
        if (UnreadMsgCount > 0) {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(UnreadMsgCount + "");
        } else {
            tv_count.setVisibility(View.GONE);
        }
    }

    private void processExtraData(Intent intent) {
        type = intent.getStringExtra("type");
        if (!TextUtils.isEmpty(type) && type.equals("addshop")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(goodsFragment);
            fragmentTransaction.hide(nearFrament);
//        fragmentTransaction.hide(myFragment);
            fragmentTransaction.hide(myUserFragment);
            fragmentTransaction.hide(lookingFragment);
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.commit();
            goodsFragment.initView();
            radioGoods.setChecked(true);
        } else if (!TextUtils.isEmpty(type) && type.equals("openshop")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(myUserFragment);
            fragmentTransaction.hide(nearFrament);
            fragmentTransaction.hide(goodsFragment);
            fragmentTransaction.hide(lookingFragment);
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.commit();
            radioMe.setChecked(true);
        } else if (!TextUtils.isEmpty(type) && type.equals("goodCar")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(goodsFragment);
            fragmentTransaction.hide(nearFrament);
            fragmentTransaction.hide(myUserFragment);
            fragmentTransaction.hide(lookingFragment);
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.commit();
            radioGoods.setChecked(true);
        } else if (!TextUtils.isEmpty(type) && type.equals("home")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(homeFragment);
            fragmentTransaction.hide(nearFrament);
            fragmentTransaction.hide(myUserFragment);
            fragmentTransaction.hide(lookingFragment);
            fragmentTransaction.hide(goodsFragment);
            fragmentTransaction.commit();
            radioHome.setChecked(true);
        } else if (!TextUtils.isEmpty(type) && type.equals("goods_class")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(lookingFragment);
            fragmentTransaction.hide(nearFrament);
            fragmentTransaction.hide(myUserFragment);
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(goodsFragment);
            fragmentTransaction.commit();
            radioLooking.setChecked(true);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processExtraData(intent);

    }

    public static void startAcitity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }


    @Override
    public void initInjector() {
        DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    String type;

    @Override
    public void initUiAndListener() {

        mBroadcastReceiver = new GoodsClick();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.liuniu.bubble.click");
        registerReceiver(mBroadcastReceiver, intentFilter);

        meRlseach = (RelativeLayout) findViewById(R.id.rl_reach);
        imageView = (ImageView) findViewById(R.id.imageView);
        mainPresenter.attachView(this);
        ButterKnife.bind(this);


        mainPresenter.initView();
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddGoodsActivity.startActivity(MainActivity.this);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_bogin) {
                    startActivity(new Intent(MainActivity.this, EaseConversationListActivity.class));
                } else {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
        meRlseach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (is_bogin) {
                startActivity(new Intent(MainActivity.this, SeachGOodsPublicActivity.class));
//                } else {
//                    ToastUtil.showToast("您还没有登录，请去登录");
//                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId) {
            case R.id.radio_home:
                fragmentTransaction.hide(lookingFragment);
                fragmentTransaction.hide(goodsFragment);
                fragmentTransaction.hide(nearFrament);
//                fragmentTransaction.hide(myFragment);
                fragmentTransaction.hide(myUserFragment);
                fragmentTransaction.show(homeFragment);
                rl_seach.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_near:
                fragmentTransaction.hide(lookingFragment);
                fragmentTransaction.hide(goodsFragment);
                fragmentTransaction.hide(homeFragment);
//                fragmentTransaction.hide(myFragment);
                fragmentTransaction.hide(myUserFragment);
                fragmentTransaction.show(nearFrament);
                rl_seach.setVisibility(View.GONE);
                break;
            case R.id.radio_looking:
                fragmentTransaction.hide(homeFragment);
                fragmentTransaction.hide(goodsFragment);
                fragmentTransaction.hide(nearFrament);
//                fragmentTransaction.hide(myFragment);
                fragmentTransaction.hide(myUserFragment);
                fragmentTransaction.show(lookingFragment);
                rl_seach.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_goods:

                try {
                    if (is_bogin) {
                        fragmentTransaction.hide(lookingFragment);
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.hide(nearFrament);
                        fragmentTransaction.hide(myUserFragment);
                        fragmentTransaction.show(goodsFragment);
                        rl_seach.setVisibility(View.GONE);
                    } else {
                        ToastUtil.showToast("您还没有登录，请去登录");
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(nearFrament);
                        fragmentTransaction.hide(myUserFragment);
                        fragmentTransaction.hide(lookingFragment);
                        fragmentTransaction.hide(goodsFragment);
                        radioHome.setChecked(true);
                        rl_seach.setVisibility(View.VISIBLE);

                        return;
                    }
                } catch (Exception e) {
                }
                break;
            case R.id.radio_me:
                try {
                    if (is_bogin) {
                        fragmentTransaction.hide(lookingFragment);
                        fragmentTransaction.hide(goodsFragment);
                        fragmentTransaction.hide(nearFrament);
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.show(myUserFragment);
                        rl_seach.setVisibility(View.GONE);
                    } else {
                        ToastUtil.showToast("您还没有登录，请去登录");
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(nearFrament);
                        fragmentTransaction.hide(myUserFragment);
                        fragmentTransaction.hide(lookingFragment);
                        fragmentTransaction.hide(goodsFragment);
                        radioHome.setChecked(true);
                        rl_seach.setVisibility(View.VISIBLE);

                        return;
                    }
                } catch (Exception e) {
                }
                break;
        }
        fragmentTransaction.commit();
    }

    final int REQUEST_WRITE = 1;//申请权限的请求码

    //写数据
    public void writeToSdCard() {
        PgyUpdateManager.setIsForced(false); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.register(MainActivity.this,
                new UpdateManagerListener() {

                    @Override
                    public void onUpdateAvailable(final String result) {

                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("更新")
                                .setMessage("你有新版本确定更新吗？")
                                .setNegativeButton(
                                        "确定",
                                        new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                startDownloadTask(
                                                        MainActivity.this,
                                                        appBean.getDownloadURL());
                                            }
                                        }).setPositiveButton("取消", null).
                                show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                    }
                });
    }

    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode==REQUEST_WRITE&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
//            writeToSdCard();
//        }
//    }
    @Override
    public void initView() {
//        PgyUpdateManager.setIsForced(true); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
//        PgyUpdateManager.register(this,"com.mydomain.fileprovider");
        final int REQUEST_WRITE = 1;//申请权限的请求码

//        if(Build.VERSION.SDK_INT>=23){
//            //判断是否有这个权限
//            if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//                //2、申请权限: 参数二：权限的数组；参数三：请求码
//                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE);
//            }else {
//                writeToSdCard();
//            }
//        } else{
//            writeToSdCard();
//        }
        writeToSdCard();
        if (!DemoHelper.getInstance().isLoggedIn()) {
            //登錄換新
            String emchat_username = PreferencesUtils.getString(MainActivity.this, "emchat_username");
            String emchat_password = PreferencesUtils.getString(MainActivity.this, "emchat_password");
            if (!TextUtils.isEmpty(emchat_username) && !TextUtils.isEmpty(emchat_password)) {
                loginEmob(emchat_username, emchat_password);
            }
        }
        //极光推送
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        sequence++;
        tagAliasBean.action = ACTION_SET;
        tagAliasBean.isAliasAction = true;
        tagAliasBean.alias = PreferencesUtils.getString(this, "user_id");
        TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(), sequence, tagAliasBean);


        mRadioGroup.setOnCheckedChangeListener(this);
        homeFragment = new HomeFragment();
        nearFrament = new NearFrament();
        lookingFragment = new LookingFragment();
        goodsFragment = new GoodsFragment();
        myUserFragment = new UserFragment();

        goodsFragment.setmClickListener(new GoodsFragment.OrderClickListener() {
            @Override
            public void onOrderCilck() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(lookingFragment);
                fragmentTransaction.hide(goodsFragment);
                fragmentTransaction.hide(nearFrament);
                fragmentTransaction.hide(myUserFragment);
                fragmentTransaction.show(homeFragment);
                rl_seach.setVisibility(View.VISIBLE);
                fragmentTransaction.commit();
                radioHome.setChecked(true);
            }
        });


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_content, homeFragment);
        fragmentTransaction.add(R.id.fragment_content, nearFrament);
        fragmentTransaction.add(R.id.fragment_content, lookingFragment);
        fragmentTransaction.add(R.id.fragment_content, goodsFragment);
        fragmentTransaction.add(R.id.fragment_content, myUserFragment);

        fragmentTransaction.hide(nearFrament);
        fragmentTransaction.hide(myUserFragment);
        fragmentTransaction.hide(lookingFragment);
        fragmentTransaction.hide(goodsFragment);
        fragmentTransaction.show(homeFragment);
        fragmentTransaction.commit();
        radioHome.setChecked(true);
        myUserFragment.setOnSwitckClickListener(new UserFragment.OnSwitckClickListener() {
            @Override
            public void onSwitckClick(int index) {
                nearFrament.setVisibility(index);
                if (index == 1) {
                    radioGoods.setVisibility(View.VISIBLE);
                    imgAdd.setVisibility(View.GONE);
                } else if (index == 0) {
                    radioGoods.setVisibility(View.GONE);
                    imgAdd.setVisibility(View.VISIBLE);
                } else {
                    radioGoods.setVisibility(View.VISIBLE);
                    imgAdd.setVisibility(View.GONE);
                }
            }
        });

        int is_shop = PreferencesUtils.getInt(this, "is_shop");
        if (is_shop == 0) {
            radioGoods.setVisibility(View.VISIBLE);
            imgAdd.setVisibility(View.GONE);
        } else {
            int state = PreferencesUtils.getInt(this, "state", Constants.STATE_BUY);
            if (state == Constants.STATE_SELLER) {
                radioGoods.setVisibility(View.GONE);
                imgAdd.setVisibility(View.VISIBLE);
            } else {
                radioGoods.setVisibility(View.VISIBLE);
                imgAdd.setVisibility(View.GONE);
            }
        }
        processExtraData(getIntent());

    }

    @Override
    public void showFragment(int position) {

    }

    @Override
    public void showLoginUi() {

    }

    @Override
    public void updatePraiseView(int dif, int position) {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    long waitTime = 2000;
    long touchTime = 0;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= waitTime) {
            //让Toast的显示时间和等待时间相同
            ToastUtil.showToast("再按一次退出");
            touchTime = currentTime;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    public class GoodsClick extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String goodId = intent.getStringExtra("goodId");
            if (!TextUtils.isEmpty(goodId)) {
                Intent i = new Intent(MainActivity.this, GoodsInfoActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("goods_id", goodId);
                startActivity(i);
            }

        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
                int UnreadMsgCount = 0;
                for (String key : conversations.keySet()) {
                    EMConversation value = conversations.get(key);
                    UnreadMsgCount = UnreadMsgCount + value.getUnreadMsgCount();
                }
                if (is_bogin) {
                    if (UnreadMsgCount > 0) {
                        MainActivity.this.tv_count.setVisibility(View.VISIBLE);
                        tv_count.setText(UnreadMsgCount + "");
                    } else {
                        tv_count.setVisibility(View.GONE);
                    }
                } else {
                    tv_count.setVisibility(View.GONE);
                }
            } catch (Exception e) {
            }

        }
    };

    public void loginEmob(final String userName, String password) {
        DemoDBManager.getInstance().closeDB();
        DemoHelper.getInstance().setCurrentUserName(userName);
        EMClient.getInstance().login(userName, password, new EMCallBack() {
            @Override
            public void onSuccess() {

                String userPic = PreferencesUtils.getString(MainActivity.this, "head_pic");
                String NickName = PreferencesUtils.getString(MainActivity.this, "nickname");
                DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(NickName);
                DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(Constants.Base_IMG_URL + userPic);
                DemoHelper.getInstance().setCurrentUserName(userName); // 环信Id

                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                mHandler.sendEmptyMessage(1);
//                Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
//                for (String key : conversations.keySet()) {
//                    EMConversation value = conversations.get(key);
//                    UnreadMsgCount = UnreadMsgCount+ value.getUnreadMsgCount();
//                }
//                if (UnreadMsgCount > 0) {
//                    tv_count.setVisibility(View.VISIBLE);
//                    tv_count.setText(UnreadMsgCount+ "");
//                }else {
//                    tv_count.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onError(int i, String s) {
//                LLog.d("error", "emob-登录聊天服务器失败！-error-" + i + "--msg--" + s);
//                System.out.println("登录聊天服务器失败" + i + "--msg--" + s);
            }

            @Override
            public void onProgress(int i, String s) {
                LLog.d("error", "emob-登录聊天服务器！-error-" + i + "--msg--" + s);
            }
        });
    }
//    private long mExitTime;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if ((System.currentTimeMillis() - mExitTime) > 2000) {
//                Object mHelperUtils;
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                mExitTime = System.currentTimeMillis();
//            } else {
//                moveTaskToBack(true);// 进入后台运行，不关闭程序
//                Intent i = new Intent(Intent.ACTION_MAIN);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.addCategory(Intent.CATEGORY_HOME);
//                startActivity(i);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
