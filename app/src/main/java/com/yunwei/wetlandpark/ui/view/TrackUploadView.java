package com.yunwei.wetlandpark.ui.view;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/9 10:22
 */
public interface TrackUploadView extends BaseView {

    void onStartTrackUpload();

    void onEndTrackUpload();

    void onFailureTrackUpload(int code, String error);

    void onSuccessTrackUpload(int code, String msg);
}
