package com.lnkj.privateshop.injector.component;

import android.app.Service;

import com.lnkj.privateshop.injector.PerService;
import com.lnkj.privateshop.injector.mudules.ServiceModule;

import dagger.Component;

/**
 * Created by sll on 16/03/16.
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = { ServiceModule.class })
public interface ServiceComponent {

  Service getServiceContext();
}
