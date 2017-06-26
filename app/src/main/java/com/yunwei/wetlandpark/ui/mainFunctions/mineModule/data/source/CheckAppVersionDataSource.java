package com.yunwei.wetlandpark.ui.mainFunctions.mineModule.data.source;

import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.data.AppVersionEntity;

import java.io.File;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions.mineModule.data.source
 * @Description:
 * @date 2016/11/17 11:01
 */

public interface CheckAppVersionDataSource {

    interface CheckAppVersionCallBack {
        void onCheckAppSuccess(AppVersionEntity entity);

        void onCheckAppDataNotAvailable();
    }

    void checkAppVersion(CheckAppVersionCallBack callBack);

    interface DownloadCallBack{
        String getDownloadURL();

        void onDownloadProgress(int percent);

        void onDownloadComplete(File file);

        void onDownloadDataNotAvailable();
    }

    void downloadApk(DownloadCallBack downloadCallBack);
}
