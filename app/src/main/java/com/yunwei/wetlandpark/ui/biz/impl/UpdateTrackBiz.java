package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.yunwei.wetlandpark.greedao.Track;
import com.yunwei.wetlandpark.ui.biz.IUpdateTrack;
import com.yunwei.wetlandpark.ui.biz.interfac.UpdateTrackListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl
 * @Description:
 * @date 2016/9/20 15:10
 */
public class UpdateTrackBiz implements IUpdateTrack {

    @Override
    public void updateTrack(final Activity activity, final Track track, final UpdateTrackListener listener) {
        listener.onUpdateTrackStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    ZNAPPlication.getInstance().getDaoSession().getTrackDao().update(track);
                    listener.onUpdateTrackSuccess();
                } catch (Exception e) {
                    listener.onUpdateTrackFailure(401, "修改失败!");
                } finally {
                    listener.onUpdateTrackEnd();
                }
            }
        }).start();
    }
}
