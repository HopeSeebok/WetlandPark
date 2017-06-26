package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.greedao.Track;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/20 14:51
 */
public interface QueryLocationTrackListener {

    void onQueryLocTrackStart();
    void onQuerylocTrackEnd();
    void onQueryLocTrackFailure(int code,String error);
    void  onQueryLocTrackSuccess(List<Track> track);
}
