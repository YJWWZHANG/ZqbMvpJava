package com.zqb.mvpjava.ui.main.activity;

import com.zqb.mvpjava.R;
import com.zqb.mvpjava.app.App;
import com.zqb.mvpjava.base.BaseActivity;
import com.zqb.mvpjava.base.contract.main.MainContract;
import com.zqb.mvpjava.presenter.main.MainPresenter;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment1;
import com.orhanobut.logger.Logger;
import com.zhy.autolayout.AutoFrameLayout;

import butterknife.BindView;
import butterknife.OnLongClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.fl_content_fragment)
    AutoFrameLayout mFlContentFragment;
    private UndefinitionFragment mUndefinitionFragment;
    private UndefinitionFragment1 mUndefinitionFragment1;
    private boolean isOne = true;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        App.getInstance().exitApp();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.presenterTest();
        mUndefinitionFragment = new UndefinitionFragment();
        mUndefinitionFragment1 = new UndefinitionFragment1();
    }

    @Override
    public void viewTest() {
        Logger.w("viewTest");
    }

    @OnLongClick(R.id.btn_exit)
    public boolean onViewLongClicked() {

        if (isOne){
            isOne = false;
//            mFlContentFragment.removeAllViews();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_content_fragment, mUndefinitionFragment, "a").commit();
        } else {
            isOne = true;
//            mFlContentFragment.removeAllViews();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_content_fragment, mUndefinitionFragment1, "b").commit();

        }
        return true;
    }
}
