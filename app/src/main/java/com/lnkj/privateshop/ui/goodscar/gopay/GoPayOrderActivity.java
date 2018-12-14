package com.lnkj.privateshop.ui.goodscar.gopay;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.mybuy.myorder.paysuccess.PaySuccessActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.paypwd.ChagePayPwdActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.utils.payali.AuthResult;
import com.lnkj.privateshop.utils.payali.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoPayOrderActivity extends BaseActivity implements GoPayOrderContract.View {

    String order_sn;
    String total_goods_num;
    String express;
    String total_goods_amount;
    String total_amount;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_total_goods_num)
    TextView tvTotalGoodsNum;
    @Bind(R.id.tv_total_goods_amount)
    TextView tvTotalGoodsAmount;
    @Bind(R.id.tv_express)
    TextView tvExpress;
    @Bind(R.id.tv_total_amount)
    TextView tvTotalAmount;
    @Bind(R.id.cb_yue)
    CheckBox cbYue;
    @Bind(R.id.ll_yue)
    LinearLayout llYue;
    @Bind(R.id.cb_alipay)
    CheckBox cbAlipay;
    @Bind(R.id.ll_alipay)
    LinearLayout llAlipay;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.cb_wechat)
    CheckBox cbWechat;
    @Bind(R.id.ll_wechat)
    LinearLayout llWechat;
    @Bind(R.id.btn_ok)
    Button btnOk;
    GpPayOrderPresenter mPresenter = new GpPayOrderPresenter(this);

    @Override
    public int initContentView() {
        return R.layout.activity_go_pay_order;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.setToken(token);
        order_sn = getIntent().getStringExtra("order_sn");
        total_goods_num = getIntent().getStringExtra("total_goods_num");
        express = getIntent().getStringExtra("express");
        total_goods_amount = getIntent().getStringExtra("total_goods_amount");
        total_amount = getIntent().getStringExtra("total_amount");
    }

    @Override
    public void initUiAndListener() {
        tvTitle.setText("支付");
        tvExpress.setText("¥" + express);
        tvTotalAmount.setText("¥" + total_amount);
        tvTotalGoodsAmount.setText("¥" + total_goods_amount);
        tvTotalGoodsNum.setText("共" + total_goods_num + "件");

    }

    private AlertDialog alertDialog;
    @OnClick({R.id.ll_yue, R.id.ll_alipay, R.id.ll_wechat, R.id.img_back, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_yue:
                cbAlipay.setChecked(false);
                cbWechat.setChecked(false);
                cbYue.setChecked(true);
                break;
            case R.id.ll_alipay:
                cbAlipay.setChecked(true);
                cbWechat.setChecked(false);
                cbYue.setChecked(false);
                break;
            case R.id.ll_wechat:
                cbAlipay.setChecked(false);
                cbWechat.setChecked(true);
                cbYue.setChecked(false);
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_ok:
                if (cbAlipay.isChecked()) {
                    mPresenter.payMent(order_sn);
                } else if (cbWechat.isChecked()) {
                    mPresenter.payWxpay(order_sn);
                } else {
                 int   isPay_password =  PreferencesUtils.getInt(this,"isPay_password");
                    if (isPay_password==1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        final View view2 = View.inflate(GoPayOrderActivity.this, R.layout.layout_lialog_one, null);
                        builder.setView(view2);
                        alertDialog = builder.create();
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.setCancelable(false);
                        TextView text_title = (TextView) view2.findViewById(R.id.text_title);
                        text_title.setText("输入支付密码");
                        TextView tv_ok_d = (TextView) view2.findViewById(R.id.tv_ok);
                        TextView  tv_quxiao = (TextView) view2.findViewById(R.id.tv_quxiao);
                        final EditText mEditText = (EditText) view2.findViewById(R.id.mEditText);
                        tv_ok_d.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPresenter.payYe(order_sn, mEditText.getText().toString());
                            }
                        });
                        //取消
                        tv_quxiao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }else {
                        Intent intent = new Intent(GoPayOrderActivity.this,ChagePayPwdActivity.class);
                        startActivity(intent);
                    }
                }
                break;
        }
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void initView() {

    }

    @Override
    public void payWxpaySuccreed(JSONObject result) {
        try {
            String appid = result.getString("appid");
            String noncestr = result.getString("noncestr");
            String packagee = result.getString("package");
            String partnerid = result.getString("partnerid");
            String prepayid = result.getString("prepayid");
            String sign = result.getString("sign");
            String timestamp = result.getLong("timestamp") + "";

            PayReq payreq = new PayReq();
            IWXAPI api = WXAPIFactory.createWXAPI(GoPayOrderActivity.this, appid);
            api.registerApp(appid);
            payreq.appId = appid;
            payreq.partnerId = partnerid;
            payreq.prepayId = prepayid;
            payreq.packageValue = packagee;
            payreq.nonceStr = noncestr;
            payreq.timeStamp = timestamp;
            payreq.sign = sign;

            api.sendReq(payreq);
        } catch (JSONException e1) {
            ToastUtil.showToast("支付失败");
            e1.printStackTrace();
        }


    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @Override
    public void payMentSuccreed(final String data) {
        try {
            //支付宝
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(GoPayOrderActivity.this);
                    String result = alipay.pay(data, true);
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();
            progressDialog.dismiss();
        } catch (Exception e) {
        }
    }

    @Override
    public void payYe(String data) {
//        setResult(30);
//        finish();
        //立即结算

        Intent intent = new Intent(GoPayOrderActivity.this, PaySuccessActivity.class);
        startActivity(intent);


    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        ToastUtil.showToast("支付成功");
//                        Intent intent = new Intent(GoPayOrderActivity.this, MainActivity.class);
//                        intent.putExtra("type","goodCar");
//                        startActivity(intent);

                        Intent intent = new Intent(GoPayOrderActivity.this, PaySuccessActivity.class);
                        startActivity(intent);
//                        finish();
                    }  else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtil.showToast("支付结果确认中");

                        }else if (TextUtils.equals(resultStatus, "4000")) {
                            ToastUtil.showToast("支付失败：请安装支付宝");
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            ToastUtil.showToast("支付失败" + resultStatus);
                        }
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        ToastUtil.showToast("授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()));
                    } else {
                        // 其他状态值则为授权失败
                        ToastUtil.showToast("授权失败" + String.format("authCode:%s", authResult.getAuthCode()));
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };
}
