package com.zqb.mvpjava.ui.main.activity;

import android.support.annotation.NonNull;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.vector.update_app.HttpManager;
import com.vector.update_app.UpdateAppManager;
import com.zhy.autolayout.AutoFrameLayout;
import com.zqb.mvpjava.R;
import com.zqb.mvpjava.app.App;
import com.zqb.mvpjava.base.BaseActivity;
import com.zqb.mvpjava.base.contract.main.MainContract;
import com.zqb.mvpjava.model.bean.GitHubUserInfoBean;
import com.zqb.mvpjava.presenter.main.MainPresenter;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment1;

import java.util.Map;

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
        mUndefinitionFragment = new UndefinitionFragment();
        mUndefinitionFragment1 = new UndefinitionFragment1();
    }

    @OnClick({R.id.btn_test})
    public void onViewLongClicked(View view) {
        switch (view.getId()){
            case R.id.btn_test:
                if (isOne){
                    isOne = false;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content_fragment, mUndefinitionFragment, "a").commit();
                } else {
                    isOne = true;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content_fragment, mUndefinitionFragment1, "b").commit();
                }
                mPresenter.getGitHubUserInfo("yjwwzhang");
                new UpdateAppManager
                        .Builder()
                        //当前Activity
                        .setActivity(this)
                        //更新地址
                        .setUpdateUrl("https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json.txt?appKey=ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f&version=0.1.0")
                        //实现httpManager接口的对象
                        .setHttpManager(new HttpManager() {
                            @Override
                            public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull Callback callBack) {

                            }

                            @Override
                            public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull Callback callBack) {

                            }

                            @Override
                            public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull FileCallback callback) {

                            }
                        })
                        .build()
                        .update();

                break;
            default:
                break;
        }
    }

    @Override
    public void showGitHubUserInfo(GitHubUserInfoBean gitHubUserInfoBean) {
        ToastUtils.showLong(gitHubUserInfoBean.toString());
    }
}
