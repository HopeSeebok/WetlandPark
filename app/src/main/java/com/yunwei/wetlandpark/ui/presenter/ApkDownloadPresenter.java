package com.yunwei.wetlandpark.ui.presenter;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.impl.ApkDownloadBiz;
import com.yunwei.wetlandpark.ui.biz.IApkDownload;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.presenter
 * @Description:
 * @date 2016/8/1 10:46
 */
public class ApkDownloadPresenter {

    private IApkDownload apkDownloadBiz;

    public ApkDownloadPresenter() {
        this.apkDownloadBiz = new ApkDownloadBiz();
    }

    public void download(Activity activity, String url) {
        apkDownloadBiz.downloadApk(activity, url);
    }
}
