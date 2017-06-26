package com.yunwei.wetlandpark.ui.biz.interfac;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/28 15:57
 */

public interface QueryDistanceListener {

    void onQueryDisStart();
    void onQueryDisEnd();
    void onQueryDisSuccess(double distance);
    void onQueryDisFailure(int code,String err);

}
