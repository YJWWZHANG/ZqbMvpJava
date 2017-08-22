package com.zqb.mvpjava.base.contract.main;

import com.zqb.mvpjava.base.BasePresenter;
import com.zqb.mvpjava.base.BaseView;
import com.zqb.mvpjava.model.bean.GitHubUserInfoBean;

public interface MainContract {

    interface View extends BaseView{
        void showGitHubUserInfo(GitHubUserInfoBean gitHubUserInfoBean);
    }

    interface Presenter extends BasePresenter<View>{
        void getGitHubUserInfo(String userName);
    }
}
