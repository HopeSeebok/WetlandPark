package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.entity.TrackDetailEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/18 17:12
 */
public interface QueryTrackDetailListener {

    void onQueryTrackDetailStart();
    void onQueryTrackDetailEnd();
    void onQueryTrackDetailFailure(int code,String error);
    void onQueryTrackDetailSuccess(TrackDetailEntity entity);
}
