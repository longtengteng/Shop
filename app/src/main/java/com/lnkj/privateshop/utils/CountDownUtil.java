package com.lnkj.privateshop.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class CountDownUtil extends CountDownTimer {
    private TextView tv;//
    private int type;

    public CountDownUtil(long millisInFuture, long countDownInterval,TextView tv,int type) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
        this.type=type;
    }

    @Override
    public void onTick(long l) {
        if (type==1){
//        tv.setText(DateFormatUtils.getHours(l));//设置时间
        tv.setText(DateFormatUtils.getHour(l));//设置时间
        }else if (type==2){
            tv.setText(DateFormatUtils.getMin(l));//设置时间
        }else {
            tv.setText(DateFormatUtils.getSs(l));//设置时间
        }
    }

    @Override
    public void onFinish() {

    }
}
