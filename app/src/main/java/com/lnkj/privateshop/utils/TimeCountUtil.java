package com.lnkj.privateshop.utils;


import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;


/**
 * 倒计时
 * 
 * @author WRJ
 * 
 */
public class TimeCountUtil extends CountDownTimer {

	private Context mActivity;
	private Button btn;// 按钮

	// 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，
	// 一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
	public TimeCountUtil(Context mActivity, long millisInFuture,
						 long countDownInterval, Button btn) {
		super(millisInFuture, countDownInterval);
		this.mActivity = mActivity;
		this.btn = btn;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		btn.setClickable(false);// 设置不能点击
		btn.setText(millisUntilFinished / 1000 + "秒后重发");// 设置倒计时时间
		// 设置按钮为灰色，这时是不能点击的
		//btn.setBackgroundResource(R.mipmap.button_ash);
		btn.setTextColor(Color.parseColor("#ffffff"));

	}
	@Override
	public void onFinish() {
		btn.setText("获取验证码");
		btn.setClickable(true);// 重新获得点击
		btn.setTextColor(Color.parseColor("#ffffff"));
		//btn.setBackgroundResource(R.mipmap.button_blue);// 还原背景色
	}

}
