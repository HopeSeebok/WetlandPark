package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.QueryDistanceListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/28 15:57
 */

public interface IQueryDistance {

     void queryDistance(Activity activity,int startTime,int endTime,QueryDistanceListener listener);
}
