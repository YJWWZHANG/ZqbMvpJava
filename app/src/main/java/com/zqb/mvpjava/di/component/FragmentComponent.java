package com.zqb.mvpjava.di.component;

import com.zqb.mvpjava.di.module.FragmentModule;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment;

import dagger.Component;

@Component(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(UndefinitionFragment undefinitionFragment);
}
