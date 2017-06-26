package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.ui.biz.interfac.UpdateTrackListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/20 15:09
 */
public interface IUpdateTrack {

    void updateTrack(Activity activity, Track track, UpdateTrackListener listener);
}
