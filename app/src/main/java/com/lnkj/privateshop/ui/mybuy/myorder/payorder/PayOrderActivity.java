package com.lnkj.privateshop.ui.mybuy.myorder.payorder;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.mybuy.myorder.paysuccess.PaySuccessActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.utils.payali.AuthResult;
import com.lnkj.privateshop.utils.payali.PayResult;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PayOrderActivity extends BaseActivity implements PayOrderContract.View {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.tv_matters)
    TextView tvMatters;
    @Bind(R.id.tv_total_goods_amount)
    TextView tvTotalGoodsAmount;
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
    PayOrderPresenter mPresenter = new PayOrderPresenter(this);
    private String price;
    private String matters;

    @Override
    public int initContentView() {
        return R.layout.activity_pay_order;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("支付");
        mPresenter.setToken(token);
        matters = getIntent().getStringExtra("matters");
        price = getIntent().getStringExtra("price");
        tvMatters.setText(matters);
        tvTotalAmount.setText(price);
    }

    @Override
    public void initUiAndListener() {

    }

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
                    if (matters.equals("缴纳开店费")) {
                        PreferencesUtils.putString(PayOrderActivity.this, "paytype", "Money");
                    }
                    mPresenter.payMent(price);
                } else if (cbWechat.isChecked()) {
                    if (matters.equals("缴纳开店费")) {
                        PreferencesUtils.putString(PayOrderActivity.this, "paytype", "Money");
                    }
                    mPresenter.payWxpay(price);
                } else {
                    final CenterActionDialog dialog = new CenterActionDialog(this);
                    dialog.setActionString("确定用余额支付吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            if (matters.equals("缴纳开店费")) {
                                PreferencesUtils.putString(PayOrderActivity.this, "paytype", "Money");
                            }
                            mPresenter.payYue(price);
                        }

                        @Override
                        public void cancelAction() {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initView() {

    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @Override
    public void payWxpaySuccreed(final JSONObject result) {
        PreferencesUtils.putString(PayOrderActivity.this, "PayType", "margin");
        try {
            String appid = result.getString("appid");
            String noncestr = result.getString("noncestr");
            String packagee = result.getString("package");
            String partnerid = result.getString("partnerid");
            String prepayid = result.getString("prepayid");
            String sign = result.getString("sign");
            String timestamp = result.getLong("timestamp") + "";
            PayReq payreq = new PayReq();
            IWXAPI api = WXAPIFactory.createWXAPI(PayOrderActivity.this, appid);
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


    @Override
    public void payMentSuccreed(final String data) {
        try {
            //支付宝
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(PayOrderActivity.this);
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
//                        setResult(30);
//                        finish();
//                        Intent intent = new Intent(PayOrderActivity.this, MainActivity.class);
//                        intent.putExtra("type","openshop");
//                        startActivity(intent);
                        Intent intent = new Intent(PayOrderActivity.this, PaySuccessActivity.class);
                        startActivity(intent);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtil.showToast("支付结果确认中");

                        } else if (TextUtils.equals(resultStatus, "4000")) {
                            ToastUtil.showToast("未安装支付宝");
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

    @Override
    public void payYueSuccreed(String data) {
//        finish();
//        Intent intent = new Intent(PayOrderActivity.this, MainActivity.class);
//        intent.putExtra("type","openshop");
//        startActivity(intent);


        Intent intent = new Intent(PayOrderActivity.this, PaySuccessActivity.class);
        startActivity(intent);
    }
}
