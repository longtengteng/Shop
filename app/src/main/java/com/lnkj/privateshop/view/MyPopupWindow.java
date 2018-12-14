package com.lnkj.privateshop.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/**
 * 作者：赵林 on 2017/10/25 0025.
 */

public class MyPopupWindow extends PopupWindow {
    @Override
    public void showAsDropDown(View anchor) {
        if(Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }

    public MyPopupWindow(Context context) {
        super(context);
    }

    public MyPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyPopupWindow() {
    }

    public MyPopupWindow(View contentView) {
        super(contentView);
    }

    public MyPopupWindow(int width, int height) {
        super(width, height);
    }

    public MyPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public MyPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }
}
