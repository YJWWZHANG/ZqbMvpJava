package com.zqb.mvpjava.base.contract.undefinition;

import com.zqb.mvpjava.base.BasePresenter;
import com.zqb.mvpjava.base.BaseView;

/**
 * Created by IISFREE on 2017/5/26.
 */

public interface UndefinitionContract {

    interface View extends BaseView{
        void viewTest();
    }

    interface Presenter extends BasePresenter<View>{
        void presenterTest();
    }
}
