package com.lnkj.privateshop.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.PhotoEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LookBigImgActivity extends BaseActivity {
    String URLIMG = "";
    @Bind(R.id.vp_view)
    ViewPager vpView;
    @Bind(R.id.activity_look_big_img)
    LinearLayout activityLookBigImg;
    int currentPosition;
    LinkedList<PhotoView> mCaches = new LinkedList<PhotoView>();
    PhotoEntity currentImageItem;
    List<PhotoEntity> imgPathsBeanList;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int initContentView() {
        return R.layout.activity_look_big_img;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvTitle.setText("图片详情");
        imgPathsBeanList = new ArrayList<>();
        imgPathsBeanList = (List<PhotoEntity>) getIntent().getSerializableExtra("imgBeen");
        currentPosition = getIntent().getIntExtra("position", 0);
        vpView.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        vpView.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgPathsBeanList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = null;
                if (mCaches.size() == 0) {
                    view = new PhotoView(LookBigImgActivity.this);
                } else {
                    view = mCaches.removeFirst();
                }
                view.enable();
                view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                String url = imgPathsBeanList.get(position).getImg_url();
                URLIMG = url;
                try {

                    if (imgPathsBeanList.get(position).getImg_url().startsWith("http")) {
                        url = imgPathsBeanList.get(position).getImg_url();
                    }else {
                        url = Constants.Base_IMG_URL+imgPathsBeanList.get(position).getImg_url();

                    }
//                    System.out.println(Constants.Base_IMG_URL+imgPathsBeanList.get(position).getImg_url());
                    Glide.with(LookBigImgActivity.this)
                            .load(url)
                            .placeholder(R.mipmap.bg_img)
                            .error(R.mipmap.bg_img)
                            .into(new GlideDrawableImageViewTarget(view) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
//                            handler.sendEmptyMessage(2);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onLoadStarted(Drawable placeholder) {
                            // 开始加载图片
                            progressDialog.show();
                        }
                    });
                } catch (Exception e) {
                }
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        vpView.setCurrentItem(currentPosition);
    }

    @Override
    public void initUiAndListener() {

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
//                ToastUtils.showShort(getApplicationContext(), "下载完毕");
//                tvRight.setEnabled(true);
//                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                String filePath = (String) msg.obj;
//                if (FileUtil.isFileExist(filePath)) {
//                    Uri uri = Uri.fromFile(new File(SDPATH + filePath));
//                    intent.setData(uri);
//                    sendBroadcast(intent);
//                }
            } else if (msg.what == 2) {

            } else if (msg.what == 3) {
//                ToastUtils.showShort(getApplicationContext(), "图片加载失败");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void onViewClicked() {
        finish();
    }
}
