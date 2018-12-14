package com.lnkj.privateshop.injector.mudules;

import android.app.Activity;

import com.lnkj.privateshop.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sll on 2016/1/6.
 */
@Module public class ActivityModule {

  private final Activity mActivity;

  public ActivityModule(Activity mActivity) {
    this.mActivity = mActivity;
  }

  @Provides @PerActivity
  public Activity provideActivity() {
    return mActivity;
  }
}
