package com.lnkj.privateshop.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lnkj.privateshop.R;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class ViewCrarriag extends LinearLayout {
    public ViewCrarriag(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ViewCrarriag(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewCrarriag(Context context) {
        super(context);
        init(context);
    }

    EditText et1;
    EditText et2;
    LinearLayout ll_1;
    ImageView iv_reduce;

    private void init(Context context) {
        View.inflate(context, R.layout.view_crarriag, this);
        ll_1 = (LinearLayout) findViewById(R.id.ll_1);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        iv_reduce = (ImageView) findViewById(R.id.iv_reduce);
        iv_reduce.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewClickListener!=null){
                    mViewClickListener.onRecuceCilck();
                }
            }
        });
        ll_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewClickListener!=null){
                mViewClickListener.onChickCilck();

            }
            }
        });

    }

    public String getEt1Text() {

        return et1.getText().toString();
    }

    public String getEt2Text() {

        return et2.getText().toString();
    }
    public void setEt1Text(String string) {

      et1.setText(string);
    }

    public void setEt2Text(String ser) {

       et2.setText(ser);
    }
    public ViewClickListener mViewClickListener;
    public void setViewClickListener( ViewClickListener mViewClickListener) {
        this.mViewClickListener = mViewClickListener;
    }

    public interface ViewClickListener {
        void onChickCilck(); //点击

        void onRecuceCilck(); //减少


    }
}
