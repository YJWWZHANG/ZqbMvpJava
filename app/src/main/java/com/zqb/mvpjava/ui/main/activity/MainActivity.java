package com.zqb.mvpjava.ui.main.activity;

import android.support.annotation.NonNull;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.orhanobut.logger.Logger;
import com.vector.update_app.HttpManager;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.utils.AppUpdateUtils;
import com.zhy.autolayout.AutoFrameLayout;
import com.zqb.mvpjava.R;
import com.zqb.mvpjava.app.App;
import com.zqb.mvpjava.base.BaseActivity;
import com.zqb.mvpjava.base.contract.main.MainContract;
import com.zqb.mvpjava.model.bean.GitHubUserInfoBean;
import com.zqb.mvpjava.presenter.main.MainPresenter;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment;
import com.zqb.mvpjava.ui.undefinition.fragment.UndefinitionFragment1;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
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
        Map<String, String> params = new HashMap<>();
        params.put("username", "50010003");
        params.put("password", "123456");
//        OkGo.<String>post("http://simmy.f3322.net:9027/api/login").params(params).execute(new com.lzy.okgo.callback.StringCallback() {
//            @Override
//            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
//                ToastUtils.showLong(response.body());
//                Logger.w(response.body());
//            }
//
//            @Override
//            public void onError(com.lzy.okgo.model.Response<String> response) {
//                super.onError(response);
//            }
//        });

        checkVersionUpdate();
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
                break;
            default:
                break;
        }
    }

    @Override
    public void showGitHubUserInfo(GitHubUserInfoBean gitHubUserInfoBean) {
        ToastUtils.showLong(gitHubUserInfoBean.toString());
    }

    private void checkVersionUpdate() {
        String path = getExternalCacheDir().getAbsolutePath();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appKey", "ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f");
        params.put("appVersion", AppUpdateUtils.getVersionName(this));
        params.put("key1", "value2");
        params.put("key2", "value3");
        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(this)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new HttpManager() {
                    @Override
                    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
                        OkGo.<String>get(url).params(params).execute(new com.lzy.okgo.callback.StringCallback() {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                callBack.onResponse(response.body());
                            }

                            @Override
                            public void onError(com.lzy.okgo.model.Response<String> response) {
                                super.onError(response);
                                callBack.onError("异常");
                            }
                        });

                    }

                    @Override
                    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
                        OkGo.<String>post(url).params(params).execute(new com.lzy.okgo.callback.StringCallback() {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                callBack.onResponse(response.body());
                            }

                            @Override
                            public void onError(com.lzy.okgo.model.Response<String> response) {
                                super.onError(response);
                                callBack.onError("异常");
                            }
                        });

                    }

                    @Override
                    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull final FileCallback callback) {
                        OkGo.<File>get(url).execute(new com.lzy.okgo.callback.FileCallback(path, fileName) {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<File> response) {
                                callback.onResponse(response.body());
                            }

                            @Override
                            public void onStart(com.lzy.okgo.request.base.Request<File, ? extends com.lzy.okgo.request.base.Request> request) {
                                super.onStart(request);
                                callback.onBefore();
                            }

                            @Override
                            public void onError(com.lzy.okgo.model.Response<File> response) {
                                super.onError(response);
                                callback.onError("异常");
                            }

                            @Override
                            public void downloadProgress(Progress progress) {
                                super.downloadProgress(progress);

                                callback.onProgress(progress.fraction, progress.totalSize);
                            }
                        });

                    }
                })
                //必须设置，更新地址
                .setUpdateUrl("https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json.txt")
                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(false)
                //不显示通知栏进度条
//                .dismissNotificationProgress()
                //是否忽略版本
//                .showIgnoreVersion()
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                .setParams(params)
                //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
                .hideDialogOnDownloading(false)
                //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
                .setTopPic(R.mipmap.lty2)
                //为按钮，进度条设置颜色，默认从顶部图片自动识别。
//                .setThemeColor(ColorUtil.getRandomColor())
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
                .setTargetPath(path)
                //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
//                .setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            updateAppBean
                                    //（必须）是否更新Yes,No
                                    .setUpdate(jsonObject.optString("update"))
                                    //（必须）新版本号，
                                    .setNewVersion(jsonObject.optString("new_version"))
                                    //（必须）下载地址
                                    .setApkFileUrl(jsonObject.optString("apk_file_url"))
                                    //测试下载路径是重定向路径
//                                    .setApkFileUrl("http://openbox.mobilem.360.cn/index/d/sid/3282847")
                                    //（必须）更新内容
                                    .setUpdateLog(jsonObject.optString("update_log"))
                                    //测试内容过度
//                                    .setUpdateLog("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16")
                                    //大小，不设置不显示大小，可以不设置
                                    .setTargetSize(jsonObject.optString("target_size"))
                                    //是否强制更新，可以不设置
                                    .setConstraint(false)
                                    //设置md5，可以不设置
                                    .setNewMd5(jsonObject.optString("new_md51"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
//                                CProgressDialogUtils.showProgressDialog(JavaActivity.this);
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
//                                CProgressDialogUtils.cancelProgressDialog(JavaActivity.this);
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp() {
//                                Toast.makeText(JavaActivity.this, "没有新版本", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
