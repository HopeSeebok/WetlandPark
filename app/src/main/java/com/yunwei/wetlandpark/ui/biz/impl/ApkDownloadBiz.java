package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.view.View;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.ui.biz.IApkDownload;
import com.yunwei.wetlandpark.utils.IFileUtils;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.dialog.LoadingDialog;
import com.yunwei.library.http.LiteHttp.DownloadRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

import java.io.File;
import java.text.DecimalFormat;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.biz
 * @Description:ApK下载业务处理类
 * @date 2016/8/1 10:26
 */
public class ApkDownloadBiz implements IApkDownload, DownloadRequestCallBack {
    private final String TAG = getClass().getSimpleName();

    private Activity activity;
    private LoadingDialog dialog;

    @Override
    public void downloadApk(Activity activity, String url) {
        this.activity = activity;
        LiteHttpManage.downloadRequest(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), url, IFileUtils.getAPKDownloadDir(), this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        dialog = (LoadingDialog) DialogFactory.createLoadingDialog(activity, "已下载0%", true);
        dialog.setProgressVisibility(View.GONE);
    }

    @Override
    public void onSuccess(File file, Response response) {
        ILog.d(TAG, "onUploadSuccess==" + response.getHttpStatus().getCode());
        if (response.getHttpStatus().getCode() == 200) {
            IUtils.installAPK(activity, file.getAbsolutePath());
        }
    }

    @Override
    public void onDownloadloading(AbstractRequest<File> request, long total, long len) {
        ILog.d(TAG, "ondownloadloading=== total==" + new DecimalFormat("######0").format(len * 100 / total));
        dialog.setTipText("下载" + new DecimalFormat("######0").format(len * 100 / total) + "%");
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        ILog.e(TAG, "onfailure==" + response.getHttpStatus().getCode() + ", error==" + e.getMessage());
        ToastUtil.showToast(activity,"下载失败!");
    }

    @Override
    public void onEnd(Response response) {
        DialogFactory.dimissDialog(dialog);
    }
}
