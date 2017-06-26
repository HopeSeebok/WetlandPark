package com.yunwei.wetlandpark.ui.view;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/20 15:01
 */
public interface UpdateTrackView extends BaseView {

    void onUpdateTrackStart();
    void onUpdateTrackEnd();
    void onUpdateTrackFailure(int code,String errod);
    void onUpdateTrackSuccess();
}
