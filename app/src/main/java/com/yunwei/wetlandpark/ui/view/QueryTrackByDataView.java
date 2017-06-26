package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.entity.TrackEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/18 16:59
 */
public interface QueryTrackByDataView extends BaseView {

    void onQueryTrackByDataStart();
    void onQueryTrackByDataEnd();
    void onQueryTrackByDataFailure(int code,String error);
    void onQueryTrackByDataSuccess(List<TrackEntity> list);
}
