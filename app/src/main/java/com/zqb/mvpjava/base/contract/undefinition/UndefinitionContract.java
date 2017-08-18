package com.zqb.mvpjava.base.contract.undefinition;

import com.zqb.mvpjava.base.BasePresenter;
import com.zqb.mvpjava.base.BaseView;

public interface UndefinitionContract {

    interface View extends BaseView{
        void viewTest();
    }

    interface Presenter extends BasePresenter<View>{
        void presenterTest();
    }
}
