package com.lnkj.privateshop.injector.component;

import android.app.Activity;

import com.lnkj.privateshop.injector.PerActivity;
import com.lnkj.privateshop.injector.mudules.ActivityModule;

import dagger.Component;


/**
 * Created by sll on 2016/3/8.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}
