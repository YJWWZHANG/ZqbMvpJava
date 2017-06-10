package com.zqb.mvpjava.base;

/**
 * Created by IISFREE on 2017/5/16.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
