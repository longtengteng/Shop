package com.lnkj.privateshop.view.picture;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.PhotoEntity;
import com.lnkj.privateshop.ui.LookBigImgActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 作者：HMY
 * 时间：2016/5/12
 */
public class NineGridTestLayout extends NineGridLayout {

    protected static final int MAX_W_H_RATIO = 3;
    public Context context;
    public NineGridTestLayout(Context context) {
        super(context);
        this.context = context;
    }

    public NineGridTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {
        Glide
                .with(mContext)
                .load(url)
                .error(R.mipmap.de_photo)
                .into(imageView);

        return true;
    }

    @Override
    protected void displayImage(RatioImageView imageView, String url) {
        Glide
                .with(mContext)
                .load(url)
                .error(R.mipmap.bg_img)
                .into(imageView);
    }

    /***
     * 点击图片
     * @param j
     * @param url
     * @param urlList
     */
    @Override
    protected void onClickImage(int j, String url, List<String> urlList) {
        Intent intent = new Intent(context,
                LookBigImgActivity.class);
        List<PhotoEntity> list = new ArrayList<PhotoEntity>();
        for (int i = 0; i < urlList.size(); i++) {
            PhotoEntity photoEntity = new PhotoEntity();
            if (!TextUtils.isEmpty(urlList.get(i))) {
                photoEntity.setImg_url(urlList.get(i));
                photoEntity.bendi = 1;
            }
            photoEntity.setId(i + "");
            list.add(photoEntity);
        }
        intent.putExtra("imgBeen", (Serializable) list);
        intent.putExtra("position", j);
        context.startActivity(intent);
    }
}
