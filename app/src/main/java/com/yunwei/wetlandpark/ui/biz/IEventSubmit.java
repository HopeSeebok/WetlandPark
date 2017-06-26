package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.EventSubmitListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz
 * @Description:
 * @date 2016/8/25 14:21
 */
public interface IEventSubmit {

    void submitEvent(Activity activity, Object object, EventSubmitListener listener);
}
