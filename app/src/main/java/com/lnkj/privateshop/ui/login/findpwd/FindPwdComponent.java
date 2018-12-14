package com.lnkj.privateshop.ui.login.findpwd;

import com.lnkj.privateshop.injector.PerActivity;
import com.lnkj.privateshop.injector.component.ApplicationComponent;
import com.lnkj.privateshop.injector.mudules.ActivityModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/7/27 0027.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class})
public interface FindPwdComponent {
    void inject(FindPwdActivity activity);
}
