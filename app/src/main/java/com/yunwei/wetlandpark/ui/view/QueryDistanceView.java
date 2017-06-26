package com.yunwei.wetlandpark.ui.view;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/28 15:54
 */

public interface QueryDistanceView extends BaseView {

    void onQueryDisStart();
    void onQueryDisEnd();
    void onQueryDisSuccess(double distance);
    void onQueryDisFailure(int code,String err);
}