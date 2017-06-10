package com.zqb.mvpjava.presenter.undefinition;

import com.zqb.mvpjava.base.RxPresenter;
import com.zqb.mvpjava.base.contract.undefinition.UndefinitionContract;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * Created by IISFREE on 2017/5/26.
 */

public class UndefinitionPresenter extends RxPresenter<UndefinitionContract.View> implements UndefinitionContract.Presenter{

    @Inject
    public UndefinitionPresenter() {
    }

    @Override
    public void presenterTest() {
        Logger.w("presenterTest");
        mView.viewTest();
    }
}
