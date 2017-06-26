package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.entity.TrackDetailEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/18 17:10
 */
public interface QueryTrackDetailView extends BaseView{

    void onQueryTrackDetailStart();
    void onQueryTrackDetailEnd();
    void onQueryTrackDetailFailure(int code,String error);
    void onQueryTrackDetailSuccess(TrackDetailEntity entity);
}
