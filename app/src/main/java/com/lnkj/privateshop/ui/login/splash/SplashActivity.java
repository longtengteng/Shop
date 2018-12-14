package com.lnkj.privateshop.ui.login.splash;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 欢迎页
 */
public class SplashActivity extends BaseActivity implements  SplashContract.View {
    @Bind(R.id.splash)
    ImageView splash;

    @Inject
    SplashPresenter Presenter;

    @Override
    public int initContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public void initInjector() {

        DaggerSplashComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);

    }

    @Override
    public void initUiAndListener() {
        Presenter.attachView(this);
        ButterKnife.bind(this);
        Presenter.initView();


    }



    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }


    @Override
    public void initView() {
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(2000);
        splash.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Presenter.getStartAppAd();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    @Override
    public void get6StartAppAdSucreed(String url) {
        Glide
                .with(this)
                .load(Constants.Base_URL + url)
                .error(R.mipmap.bg_img)
                .into(splash);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(3000);
        splash.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Presenter.next();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toLogIn() {
        LoginActivity.statitActivity(SplashActivity.this);
                finish();
    }

    @Override
    public void toMain() {
        MainActivity.startAcitity(SplashActivity.this);
                finish();
    }

    @Override
    public void setTitel(String s) {

    }

    @Override
    public void countDown() {

    }


}
