package com.zqb.mvpjava.model.http;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubRetrofit {

    private GitHubRetrofit(){}

    private static HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient mClient = new OkHttpClient.Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();

    private static GitHubApi mGitHubApi = null;
    public static GitHubApi getGitHubApi() {
        if (mGitHubApi == null) {
            synchronized (GitHubRetrofit.class) {
                if (mGitHubApi == null) {
                    mGitHubApi = new Retrofit.Builder()
                            .client(mClient)
                            .baseUrl(GitHubApi.GIT_HUB_HOST)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(GitHubApi.class);
                }
            }
        }
        return mGitHubApi;
    }
}
