package com.zqb.mvpjava.presenter.main;

import com.zqb.mvpjava.base.RxPresenter;
import com.zqb.mvpjava.base.contract.main.MainContract;
import com.orhanobut.logger.Logger;


import javax.inject.Inject;

/**
 * Created by IISFREE on 2017/5/16.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    @Inject
    public MainPresenter() {
    }


    @Override
    public void presenterTest() {
        Logger.w("presenterTest");
        mView.viewTest();
    }
}
