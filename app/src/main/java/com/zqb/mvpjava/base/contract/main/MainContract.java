package com.zqb.mvpjava.base.contract.main;

import com.zqb.mvpjava.base.BasePresenter;
import com.zqb.mvpjava.base.BaseView;

/**
 * Created by IISFREE on 2017/5/16.
 */

public interface MainContract {

    interface View extends BaseView{
        void viewTest();
    }

    interface Presenter extends BasePresenter<View>{
        void presenterTest();
    }
}
