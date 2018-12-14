
package com.lnkj.privateshop.utils;

import android.util.Log;



public class LLog {
	
	public interface Debug
	{
		public final boolean logable = true;//是否打印LOG
	}
	
	public static void d(String tag, String msg)
	{
		if (Debug.logable)
			Log.d("lnkj", tag+":"+msg);
	}
	
	public static void e(String tag, String msg)
	{
		if (Debug.logable)
			Log.e("lnkj",  tag+":"+msg);
	}
	
	public static void i(String tag, String msg)
	{
		if (Debug.logable)
			Log.i("lnkj",tag+":"+ msg);
	}
	
	public static void v(String tag, String msg)
	{
		if (Debug.logable)
			Log.v("lnkj", tag+":"+msg);
	}
	
	public static void w(String tag, String msg)
	{
		if (Debug.logable)
			Log.w("lnkj", tag+":"+msg);
	}
}
