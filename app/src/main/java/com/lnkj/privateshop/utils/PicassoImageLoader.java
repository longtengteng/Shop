package com.lnkj.privateshop.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.R;
import com.lzy.imagepicker.loader.ImageLoader;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class PicassoImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        Glide
                .with(activity)
                .load(path)
                .error(R.mipmap.de_photo)
                .into(imageView);
//        Glide.with(this).load(file).
    }

    @Override
    public void clearMemoryCache() {

    }
}
