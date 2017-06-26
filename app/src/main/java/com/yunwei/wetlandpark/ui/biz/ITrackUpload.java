package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.ui.biz.interfac.TrackUploadListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/9 10:28
 */
public interface ITrackUpload {

    void uploadTrack(Activity activity, TrackEntity entity, TrackUploadListener listener);
}
