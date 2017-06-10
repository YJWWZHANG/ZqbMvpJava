package com.zqb.mvpjava.di.component;

import com.zqb.mvpjava.di.module.FragmentModule;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment;

import dagger.Component;

/**
 * Created by IISFREE on 2017/5/26.
 */

@Component(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(UndefinitionFragment undefinitionFragment);
}
