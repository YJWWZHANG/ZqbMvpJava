package com.zqb.mvpjava.ui.undefinition.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.zqb.mvpjava.R;
import com.labo.kaji.fragmentanimations.CubeAnimation;

/**
 * Created by IISFREE on 2017/5/26.
 */

public class UndefinitionFragment1 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_undefinition1, null);
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
