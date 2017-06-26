package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.EventPointListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz
 * @Description:
 * @date 2016/8/26 15:58
 */
public interface IEventPoint {
    void reqEventPoint(Activity activity, String eventId, EventPointListener listener);
}
