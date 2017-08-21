package com.zqb.mvpjava.ui.main.activity;

import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.zhy.autolayout.AutoFrameLayout;
import com.zqb.mvpjava.R;
import com.zqb.mvpjava.app.App;
import com.zqb.mvpjava.base.BaseActivity;
import com.zqb.mvpjava.base.contract.main.MainContract;
import com.zqb.mvpjava.model.bean.GitHubAuthorBean;
import com.zqb.mvpjava.model.http.GitHubRetrofit;
import com.zqb.mvpjava.presenter.main.MainPresenter;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment1;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.subscribers.BlockingBaseSubscriber;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    @OnClick({R.id.btn_test})
    public void onViewLongClicked(View view) {
        switch (view.getId()){
            case R.id.btn_test:
                /**
                 *    compile 'com.squareup.okhttp3:okhttp:3.5.0'
                 **/
//                new OkHttpClient().newCall(new Request.Builder().url(
//                        "https://api.github.com/users/yjwwzhang").build())
//                        .enqueue(new Callback() {
//                            @Override
//                            public void onFailure(Call call, IOException e) {
//
//                            }
//
//                            @Override
//                            public void onResponse(Call call, Response response) throws IOException {
////                                Log.d("666666", "onResponse: " + response.body().string());
//                                ToastUtils.showLongSafe("666666 " + "onResponse: " + response.body().string());
//                            }
//                        });

                GitHubRetrofit.getGitHubApi().getAuthorInfo("yjwwzhang")
                        .observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<GitHubAuthorBean>() {
                            @Override
                            public void accept(@NonNull GitHubAuthorBean gitHubAuthorBean) throws Exception {
                                ToastUtils.showLongSafe(gitHubAuthorBean.toString());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                ToastUtils.showLongSafe(throwable.getMessage());
                            }
                        });
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
