package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.entity.TrackEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/18 17:01
 */
public interface QueryTrackByDataListener {

    void onQueryTrackByDataStart();
    void onQueryTrackByDataEnd();
    void onQueryTrackByDataFailure(int code,String error);
    void onQueryTrackByDataSuccess(List<TrackEntity> list);
}
