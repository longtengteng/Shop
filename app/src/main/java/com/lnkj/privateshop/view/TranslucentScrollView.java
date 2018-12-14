package com.lnkj.privateshop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by guanke on 2017/4/11.
 */

public class TranslucentScrollView extends ScrollView  {
    public TranslucentListener translucentListener;



    public interface TranslucentListener {
        /**
         * 透明度的回调   * @param alpha
         */
        public void onTranslucent(float alpha);
    }
    public void setOnTranslucent(TranslucentListener l) {
        translucentListener=l;
    }
    public TranslucentScrollView(Context context) {
        super(context);
    }

    public TranslucentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslucentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float heightPixels = getContext().getResources().getDisplayMetrics().heightPixels;
        float scrollY = getScrollY();//该值 大于0
        float alpha = scrollY/(heightPixels/3);// 0~1 透明度是1~0  //这里选择的screenHeight的1/3 是alpha改变的速率 （根据你的需要你可以自己定义）
        if (translucentListener != null) {
            translucentListener.onTranslucent(alpha);
        }

    }


}
