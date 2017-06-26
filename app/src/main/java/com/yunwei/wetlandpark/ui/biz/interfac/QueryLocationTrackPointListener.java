package com.yunwei.wetlandpark.ui.biz.interfac;

import com.esri.core.geometry.Point;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/20 14:31
 */
public interface QueryLocationTrackPointListener {

    void onQueryPointStart();

    void onQueryPointEnd();

    void onQueryPointFailure(int code,String error);

    void onQueryPointSuccess(List<Point> list);
}
