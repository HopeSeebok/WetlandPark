package com.yunwei.wetlandpark.ui.biz.interfac;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/9 10:27
 */
public interface TrackUploadListener {

    void onStartTrackUpload();

    void onEndTrackUpload();

    void onFailureTrackUpload(int code, String error);

    void onSuccessTrackUpload(int code, String msg);
}
