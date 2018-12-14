package com.lnkj.privateshop.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.dialog.DialogAlarm;


/**
 * Created by zhangyf on 2017/1/4.
 */

public class CenterActionDialog extends DialogAlarm {

    boolean showTipSmallView;


    TextView tipView ;
    TextView tipSmallView;
    TextView sureView;
    TextView noView ;

    String tipString;
    String tipSmallString;
    String sureString;
    String noString;

    int tipColor;
    int sureColor;

    ActionLisenter lisenter;


    public CenterActionDialog(Context context) {
        super(context, R.style.UIKit_Dialog_Fixed);
    }

    @Override
    public View getView(ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(R.layout.dialog_delete, parent, true);
    }

    @Override
    public void onViewCreated(View view) {

        tipView = (TextView) view.findViewById(R.id.tip);
        tipSmallView = (TextView) view.findViewById(R.id.tip_small);
        sureView = (TextView) view.findViewById(R.id.ok);
        noView = (TextView) view.findViewById(R.id.no);

        if (tipColor > 0){
            tipView.setTextColor(tipColor);
        }
        if (sureColor > 0){
            sureView.setTextColor(sureColor);
        }

        if (null != tipSmallString){
            tipSmallView.setText(tipSmallString);
        }

        if (showTipSmallView){
            tipSmallView.setVisibility(View.VISIBLE);
        }

        if (null != tipString){
            tipView.setText(tipString);
        }
        if (null != sureString){
            sureView.setText(sureString);
        }
        if (null != noString){
            noView.setText(noString);
        }

        noView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (null != lisenter){
                    lisenter.cancelAction();
                }
            }
        });
        sureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (null != lisenter){
                    lisenter.sureAction();
                }
            }
        });
    }

    public void setActionString(String tipString, String sureString , String cancleString){
        this.tipString = tipString;
        this.sureString = sureString;
        this.noString = cancleString;
    }

    public void setActionListener(ActionLisenter listener) {
        this.lisenter = listener;
    }

    public static interface ActionLisenter {
        void sureAction();

        void cancelAction();
    }

    public void setShowTipSmallView(boolean showTipSmallView) {
        this.showTipSmallView = showTipSmallView;
    }

    public void setTipColor(int tipColor) {
        this.tipColor = tipColor;
    }

    public void setSureColor(int sureColor) {
        this.sureColor = sureColor;
    }

    public void setTipSmallString(String tipSmallString) {
        this.tipSmallString = tipSmallString;
    }
}
