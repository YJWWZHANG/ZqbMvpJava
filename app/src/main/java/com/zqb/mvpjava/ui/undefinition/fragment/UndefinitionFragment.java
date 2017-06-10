package com.zqb.mvpjava.ui.undefinition.fragment;

import android.view.animation.Animation;

import com.zqb.mvpjava.R;
import com.zqb.mvpjava.base.BaseFragment;
import com.zqb.mvpjava.base.contract.undefinition.UndefinitionContract;
import com.zqb.mvpjava.presenter.undefinition.UndefinitionPresenter;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.orhanobut.logger.Logger;

/**
 * Created by IISFREE on 2017/5/26.
 */

public class UndefinitionFragment extends BaseFragment<UndefinitionPresenter> implements UndefinitionContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_undefinition;
    }

    @Override
    protected void initEventAndData() {
//        mPresenter.presenterTest();
    }

    @Override
    public void viewTest() {
        Logger.w("viewTest");
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.LEFT, enter, 1000);
//        return FlipAnimation.create(FlipAnimation.LEFT, enter, 1000);
//        return PushPullAnimation.create(PushPullAnimation.LEFT, enter, 1000);
//        return MoveAnimation.create(MoveAnimation.LEFT, enter, 1000);
//        return SidesAnimation.create(SidesAnimation.LEFT, enter, 1000);
//        if (enter) {
//            return PushPullAnimation.create(PushPullAnimation.LEFT, enter, 1000);
//        } else {
//            return CubeAnimation.create(CubeAnimation.LEFT, enter, 1000);
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
