package com.yunwei.wetlandpark.ui.biz.interfac;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz.interfac
 * @Description:
 * @date 2016/8/25 14:21
 */
public interface EventSubmitListener {

    void onEventStart();
    void onEventEnd();
    void onEventSuccess();
    void onEventFailure();
}
