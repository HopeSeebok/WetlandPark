package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.QueryLocationTrackListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/20 14:51
 */
public interface IQueryLocationTrack {

    void queryLocTrack(Activity activity, QueryLocationTrackListener listener);
}
