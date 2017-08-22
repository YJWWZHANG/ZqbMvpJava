package com.zqb.mvpjava.presenter.main;

import com.blankj.utilcode.util.ToastUtils;
import com.zqb.mvpjava.base.RxPresenter;
import com.zqb.mvpjava.base.contract.main.MainContract;
import com.zqb.mvpjava.model.bean.GitHubUserInfoBean;
import com.zqb.mvpjava.model.http.GitHubRequest;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    @Inject
    public MainPresenter() {
    }

    @Override
    public void getGitHubUserInfo(String userName) {
        GitHubRequest.getGitHubApi().getAuthorInfo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GitHubUserInfoBean>() {
                    @Override
                    public void accept(@NonNull GitHubUserInfoBean gitHubUserInfoBean) throws Exception {
                        mView.showGitHubUserInfo(gitHubUserInfoBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        ToastUtils.showLongSafe(throwable.getMessage());
                    }
                });
    }

}
