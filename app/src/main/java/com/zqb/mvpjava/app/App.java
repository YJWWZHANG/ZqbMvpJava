package com.zqb.mvpjava.app;

import android.app.Activity;
import android.app.Application;
import android.os.Process;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.zqb.mvpjava.R;
import com.orhanobut.logger.Logger;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.HashSet;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    private static App mApp;
    private Set<Activity> mAllActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Utils.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
        AutoLayoutConifg.getInstance().useDeviceSize();
    }

    public static App getInstance(){
        return mApp;
    }

    public void AddActivity(Activity activity){
        if (mAllActivities == null){
            mAllActivities = new HashSet<>();
        }
        mAllActivities.add(activity);
    }

    public void removeActivity(Activity activity){
        if (mAllActivities != null){
            mAllActivities.remove(activity);
        }
    }

    public void exitApp(){
        if (mAllActivities != null){
            synchronized (mAllActivities){
                for (Activity activity : mAllActivities){
                    activity.finish();
                }
            }
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }
}
