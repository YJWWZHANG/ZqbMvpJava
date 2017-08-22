package com.zqb.mvpjava.model.http;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.zqb.mvpjava.app.App;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubRequest {

    private GitHubRequest() {}

    private static GitHubApi mGitHubApi = null;

    public static GitHubApi getGitHubApi() {
        if (mGitHubApi == null) {
            synchronized (GitHubRequest.class) {
                if (mGitHubApi == null) {
                    mGitHubApi = new Retrofit.Builder()
                            .client(new OkHttpClient.Builder()
                                    .cache(new Cache(new File(App.getInstance().getExternalCacheDir(), "GitHubCache"), 10 * 1024 * 1024))
                                    .addInterceptor(new Interceptor() {
                                        @Override
                                        public Response intercept(Chain chain) throws IOException {
                                            Request request = chain.request();
                                            if (!NetworkUtils.isAvailableByPing()) {
                                                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                                            }
                                            Response response = chain.proceed(request);
                                            if (NetworkUtils.isAvailableByPing()){
                                                return response.newBuilder().header("Cache-Control", "max-age=" + 60).removeHeader("Pragma").build();
                                            } else {
                                                return response.newBuilder().header("Cache-Control", "only-if-cached, max-stale=" + 60 * 60 * 24 * 14).removeHeader("Pragma").build();
                                            }
                                        }
                                    })
                                    .addInterceptor(new Interceptor() {
                                        @Override
                                        public Response intercept(Chain chain) throws IOException {

                                            // https://developer.github.com/v3/auth/#basic-authentication
                                            // https://developer.github.com/v3/oauth/#non-web-application-flow
                                            String userCredentials = "yjwwzhang:620894880-zqb";

                                            String basicAuth =
                                                    "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.DEFAULT));

                                            Request original = chain.request();

                                            Request.Builder requestBuilder = original.newBuilder()
                                                    .header("Authorization", basicAuth.trim());

                                            Request request = requestBuilder.build();
                                            return chain.proceed(request);
                                        }
                                    })
                                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                    .retryOnConnectionFailure(true)
                                    .connectTimeout(20, TimeUnit.SECONDS)
                                    .readTimeout(20, TimeUnit.SECONDS)
                                    .build())
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
