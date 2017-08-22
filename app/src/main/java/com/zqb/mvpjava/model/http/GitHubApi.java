package com.zqb.mvpjava.model.http;


import com.zqb.mvpjava.model.bean.GitHubUserInfoBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    String GIT_HUB_HOST = "https://api.github.com/";

    @GET("users/{author}")
    Flowable<GitHubUserInfoBean> getAuthorInfo(@Path("author") String author);

}
