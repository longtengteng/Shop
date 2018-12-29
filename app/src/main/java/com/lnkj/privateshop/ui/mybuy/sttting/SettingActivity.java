package com.lnkj.privateshop.ui.mybuy.sttting;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.ui.mybuy.feedback.BeedBackActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.carriage.CarriageActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.about.AbuotActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.cashpwd.ChageChagPwdActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.chagephone.ChagePhoneActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.chagepwd.ChagePwdActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.editpwd.EditCashPwdActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.paypwd.ChagePayPwdActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.paypwd.edit.EditPayPwdActivity;
import com.lnkj.privateshop.utils.AppCleanUtils;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.PreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.R.id.tv_pwd;
import static com.lnkj.privateshop.utils.HttpUtil.meApi;


public class SettingActivity extends BaseActivity {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.ll_edit_phone)
    LinearLayout llEditPhone;
    @Bind(R.id.ll_edit_pwd)
    LinearLayout llEditPwd;
    @Bind(R.id.ll_edit_cash_pwd)
    LinearLayout llEditCashPwd;
    @Bind(R.id.tv_cache)
    TextView tvCache;
    @Bind(R.id.ll_remove_cache)
    LinearLayout llRemoveCache;
    @Bind(R.id.ll_we)
    LinearLayout llWe;
    @Bind(tv_pwd)
    TextView tvPwd;
    @Bind(R.id.tv_lin)
    TextView tv_lin;
    @Bind(R.id.ll_freight)
    LinearLayout ll_freight;
    @Bind(R.id.tv_lin_1)
    TextView tv_lin_1;
@Bind(R.id.tv_pay_pwd)
TextView tv_pay_pwd;
    @Bind(R.id.ll_pay_pwd)
    LinearLayout ll_pay_pwd;
    @Bind(R.id.tv_pay)
    TextView tv_pay;
    @Override
    public int initContentView() {
        return R.layout.activity_setting;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("设置");
        tvCache.setText(AppCleanUtils.getAppClearSize(this));
        int state = PreferencesUtils.getInt(this, "state", Constants.STATE_BUY);
        if (state == Constants.STATE_BUY) {
            llEditCashPwd.setVisibility(View.GONE);
            tv_lin.setVisibility(View.GONE);
            ll_freight.setVisibility(View.GONE);
            tv_lin_1.setVisibility(View.GONE);
            ll_pay_pwd.setVisibility(View.VISIBLE);
            tv_pay.setVisibility(View.VISIBLE);
        } else {
            ll_pay_pwd.setVisibility(View.GONE);
            tv_pay.setVisibility(View.GONE);
            llEditCashPwd.setVisibility(View.VISIBLE);
            tv_lin.setVisibility(View.VISIBLE);
            ll_freight.setVisibility(View.VISIBLE);
            tv_lin_1.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void initUiAndListener() {

    }

    @OnClick({R.id.img_back, R.id.ll_edit_phone, R.id.ll_edit_pwd, R.id.ll_edit_cash_pwd, R.id.ll_remove_cache,
            R.id.ll_we, R.id.btn_pull, R.id.ll_freight,R.id.ll_beedback,R.id.ll_pay_pwd})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_edit_phone:
                intent = new Intent(this, ChagePhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_edit_pwd:
                intent = new Intent(this, ChagePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_edit_cash_pwd:
                if (!TextUtils.isEmpty(tvPwd.getText().toString())){
                    if (tvPwd.getText().toString().equals("审核状态")){
                        return;
                    }
                }
                if (state==4) {
                    intent = new Intent(this, EditCashPwdActivity.class);
                    startActivity(intent);
                }else if (state==0){

                } else {
                    intent = new Intent(this, ChageChagPwdActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_remove_cache:
                AppCleanUtils.cleanExternalCache(this);
                AppCleanUtils.cleanInternalCache(this);
                tvCache.setText("0.00MB");
                break;
            case R.id.ll_we:
                intent = new Intent(this, AbuotActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pull:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("确定要退出吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PreferencesUtils.putString(SettingActivity.this, "token", "");
                              //  PreferencesUtils.putInt(SettingActivity.this, "is_shop", 0);
                                PreferencesUtils.putBoolean(SettingActivity.this, "is_bogin", false);

                                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("type","setting");
                                startActivity(intent);
                                //此方法为异步方法
                                EMClient.getInstance().logout(true);

                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.ll_freight:
                intent = new Intent(this, CarriageActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_beedback:
                intent = new Intent(this, BeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_pay_pwd:
                if (is_set_pay_password==1){
                    intent = new Intent(this,EditPayPwdActivity.class);

                }else {
                    intent = new Intent(this, ChagePayPwdActivity.class);
                }
                    startActivity(intent);

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromServer();
    }


    int state;
    int is_set_pay_password;
    public void getDataFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        meApi.isSetWithdrawPwd(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("数据", data + "");
                        try {
                            JSONObject object = new JSONObject(data);
                          int  status = object.getInt("status");
                            if (status==1){
                            String info = object.getString("info");
                            JSONObject obd= object.getJSONObject("data");
                             state = obd.getInt("state");
                            if (state==0){
                                tvPwd.setText("审核中");
                            }else if (state==-1){
                                tvPwd.setText("未设置");
                            }else if (state==2){
                                tvPwd.setText("审核未通过");
                            }else if (state==3){
                                tvPwd.setText("已设置");
                            }
                            else if (state==4){
                                tvPwd.setText("已设置");
                            }
                            is_set_pay_password = obd.getInt("is_set_pay_password");
                                if (is_set_pay_password==1){
                                    tv_pay_pwd.setText("");
                                }else {
                                    tv_pay_pwd.setText("未设置");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        LLog.d("数据错误222", throwable.toString() + "");
                    }
                });
    }
}
