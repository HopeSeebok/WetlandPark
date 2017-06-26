package com.yunwei.wetlandpark.ui.biz.interfac;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/20 15:03
 */
public interface UpdateTrackListener {

    void onUpdateTrackStart();

    void onUpdateTrackEnd();

    void onUpdateTrackFailure(int code, String errod);

    void onUpdateTrackSuccess();
}
