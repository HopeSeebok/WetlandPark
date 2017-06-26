package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.QueryBaiduTrackHistoryListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/23 11:47
 */
public interface IQueryBaiduTrackHistory {

    void queryBaiduTrack(Activity activity, int startTime, int endTime, QueryBaiduTrackHistoryListener listener);
}
