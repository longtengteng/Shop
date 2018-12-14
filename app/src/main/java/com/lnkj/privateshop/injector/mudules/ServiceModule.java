package com.lnkj.privateshop.injector.mudules;

import android.app.Service;

import com.lnkj.privateshop.injector.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sll on 16/03/16.
 */
@Module public class ServiceModule {
  private Service mService;

  public ServiceModule(Service service) {
    mService = service;
  }

  @Provides @PerService
  public Service provideContext() {
    return mService;
  }
}
