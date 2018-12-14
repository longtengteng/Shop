package com.lnkj.privateshop.ui;

import com.lnkj.privateshop.injector.PerActivity;
import com.lnkj.privateshop.injector.component.ApplicationComponent;
import com.lnkj.privateshop.injector.mudules.ActivityModule;

import dagger.Component;

/**
 * Created by lnkj on 2017/7/9 0009.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class})
public interface MainComponent {
        void inject(MainActivity activity);
}
