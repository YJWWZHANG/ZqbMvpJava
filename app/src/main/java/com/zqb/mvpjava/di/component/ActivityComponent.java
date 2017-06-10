package com.zqb.mvpjava.di.component;

import android.app.Activity;

import com.zqb.mvpjava.di.module.ActivityModule;
import com.zqb.mvpjava.di.scope.ActivityScope;
import com.zqb.mvpjava.ui.main.activity.MainActivity;

import dagger.Component;

/**
 * Created by IISFREE on 2017/5/16.
 */

@ActivityScope
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

}
