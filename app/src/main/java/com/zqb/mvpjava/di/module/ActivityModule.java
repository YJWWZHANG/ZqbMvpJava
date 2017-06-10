package com.zqb.mvpjava.di.module;

import android.app.Activity;

import com.zqb.mvpjava.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IISFREE on 2017/5/16.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

}
