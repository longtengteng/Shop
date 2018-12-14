package com.lnkj.privateshop.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lnkj.privateshop.R;


/**
 * Created by zhangyf on 2016/9/19.
 */
public class PhotoUploadDialog extends DialogAlarm {
    TextView camero;
    TextView album;
    TextView cancel;
    TextView tv_tite;
    TextView tv_view;

    PhotoUpLisenter lisenter;

    public PhotoUploadDialog(Context context) {
        super(context, R.style.UIKit_Dialog_Fixed);
    }

    @Override
    public View getView(ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(R.layout.dialog_photo_up2, parent, true);
    }

    @Override
    public void onViewCreated(View view) {

        camero = (TextView) view.findViewById(R.id.dialog_camero);
        album = (TextView) view.findViewById(R.id.dialog_album);
        cancel = (TextView) view.findViewById(R.id.dialog_cancel);
        tv_tite = (TextView) view.findViewById(R.id.tv_tite);
        tv_view = (TextView) view.findViewById(R.id.view);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != lisenter) {
                    lisenter.cancel();
                    dismiss();
                }
            }
        });

        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != lisenter) {
                    lisenter.joinPhoto();
                    dismiss();

                }
            }
        });

        camero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != lisenter) {
                    lisenter.tackPhoto();
                    dismiss();
                }
            }
        });
    }

    public void setPhotoUpListener(PhotoUpLisenter listener) {
        this.lisenter = listener;
    }

    public static interface PhotoUpLisenter {
        void tackPhoto();

        void joinPhoto();

        void cancel();
    }
    public void setVisibility(){
        if (tv_tite!=null)
        tv_tite.setVisibility(View.VISIBLE);
        if (tv_view!=null)
            tv_view.setVisibility(View.VISIBLE);
    }
}
