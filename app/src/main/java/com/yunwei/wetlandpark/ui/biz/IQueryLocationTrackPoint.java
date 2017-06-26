package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.QueryLocationTrackPointListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/20 14:31
 */
public interface IQueryLocationTrackPoint {

    void queryTrackPoint(Activity activity, long tId, QueryLocationTrackPointListener listener);
}
