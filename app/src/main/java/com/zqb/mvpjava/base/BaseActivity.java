package com.zqb.mvpjava.base;

import com.zqb.mvpjava.di.component.ActivityComponent;
import com.zqb.mvpjava.di.component.DaggerActivityComponent;
import com.zqb.mvpjava.di.module.ActivityModule;

import javax.inject.Inject;

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected abstract void initInject();
}
