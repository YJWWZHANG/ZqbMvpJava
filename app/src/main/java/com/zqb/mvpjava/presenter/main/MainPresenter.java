package com.zqb.mvpjava.presenter.main;

import com.blankj.utilcode.util.ToastUtils;
import com.zqb.mvpjava.base.RxPresenter;
import com.zqb.mvpjava.base.contract.main.MainContract;
import com.orhanobut.logger.Logger;
import com.zqb.mvpjava.model.bean.GitHubAuthorBean;
import com.zqb.mvpjava.model.http.GitHubRetrofit;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.subscribers.BlockingBaseSubscriber;
import io.reactivex.schedulers.Schedulers;

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
