package com.yunwei.wetlandpark.ui.view;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.view
 * @Description:
 * @date 2016/8/25 14:04
 */
public interface EventSubmitView extends BaseView{

    void onEventStart();
    void onEventEnd();
    void onEventSuccess();
    void onEventFailure();
}
