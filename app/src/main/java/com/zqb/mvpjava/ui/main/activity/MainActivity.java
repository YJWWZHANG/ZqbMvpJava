package com.zqb.mvpjava.ui.main.activity;

import android.view.View;

import com.orhanobut.logger.Logger;
import com.zhy.autolayout.AutoFrameLayout;
import com.zqb.mvpjava.R;
import com.zqb.mvpjava.app.App;
import com.zqb.mvpjava.base.BaseActivity;
import com.zqb.mvpjava.base.contract.main.MainContract;
import com.zqb.mvpjava.presenter.main.MainPresenter;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment1;

import butterknife.BindView;
import butterknife.OnClick;

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

    @OnClick({R.id.btn_exit})
    public void onViewLongClicked(View view) {
        switch (view.getId()){
            case R.id.btn_exit:
                if (isOne){
                    isOne = false;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content_fragment, mUndefinitionFragment, "a").commit();
                } else {
                    isOne = true;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content_fragment, mUndefinitionFragment1, "b").commit();
                }
                break;
            default:
                break;
        }
    }
}
