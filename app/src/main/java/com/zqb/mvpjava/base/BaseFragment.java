package com.zqb.mvpjava.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zqb.mvpjava.di.component.DaggerFragmentComponent;
import com.zqb.mvpjava.di.component.FragmentComponent;

import javax.inject.Inject;

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView{

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .build();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    protected abstract void initInject();
}
