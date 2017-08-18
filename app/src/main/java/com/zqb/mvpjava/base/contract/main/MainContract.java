package com.zqb.mvpjava.base.contract.main;

import com.zqb.mvpjava.base.BasePresenter;
import com.zqb.mvpjava.base.BaseView;

public interface MainContract {

    interface View extends BaseView{
        void viewTest();
    }

    interface Presenter extends BasePresenter<View>{
        void presenterTest();
    }
}
