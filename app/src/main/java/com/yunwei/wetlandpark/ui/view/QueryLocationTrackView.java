package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.greedao.Track;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/20 14:49
 */
public interface QueryLocationTrackView extends BaseView {

    void onQueryLocTrackStart();

    void onQuerylocTrackEnd();

    void onQueryLocTrackFailure(int code, String error);

    void onQueryLocTrackSuccess(List<Track> track);
}
