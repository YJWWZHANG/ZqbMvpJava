package com.zqb.mvpjava.presenter.main;

import com.zqb.mvpjava.base.RxPresenter;
import com.zqb.mvpjava.base.contract.main.MainContract;
import com.orhanobut.logger.Logger;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    @Inject
    public MainPresenter() {
    }


    @Override
    public void presenterTest() {
        Logger.w("presenterTest");
        mView.viewTest();
        EventBus.getDefault().postSticky("2333");
    }
}
