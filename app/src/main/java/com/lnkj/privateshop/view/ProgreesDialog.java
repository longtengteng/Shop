package com.lnkj.privateshop.view;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.lnkj.privateshop.R;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class ProgreesDialog {
    public static Dialog progressDialog;// 加载框

    public static void setDialog(Context mContext) {
        progressDialog = new Dialog(mContext, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog_commom);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        TextView msg = (TextView) progressDialog
                .findViewById(R.id.id_tv_loadingmsg);
        msg.setText("拼命加载中...");
    }
    public static void  ProgeesDialogShow(){
        if (progressDialog!=null){
            progressDialog.show();
        }
    }
    public static void ProgeesDialogDiss(){
        if (progressDialog!=null){
        progressDialog.dismiss();

        }
    }
}
