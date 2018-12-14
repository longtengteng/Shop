package com.lnkj.privateshop.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.ui.mybuy.myorder.paysuccess.PaySuccessActivity;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }



    @Override
    public void onResp(BaseResp resp) {
        LLog.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
//                String margin = PreferencesUtils.getString(WXPayEntryActivity.this, "PayType");
//                if (TextUtils.isEmpty(margin) && margin.equals("margin")) {
//                    Intent intent = new Intent(WXPayEntryActivity.this, MainActivity.class);
//                    intent.putExtra("type", "openshop");
//                    startActivity(intent);
//                } else {
//                    intent = new Intent(WXPayEntryActivity.this, PaySuccessActivity.class);
//                }
                Intent  intent = new Intent(WXPayEntryActivity.this, PaySuccessActivity.class);
                startActivity(intent);
                finish();
            } else {
                ToastUtil.showToast("支付失败" + "errCode = " + resp.errCode);
                finish();
            }

        }
    }
}