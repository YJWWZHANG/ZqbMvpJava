package com.zqb.mvpjava.base;

/**
 * Created by IISFREE on 2017/5/16.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
    void detachView();
}
