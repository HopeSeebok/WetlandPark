package com.yunwei.wetlandpark.ui.view;

import com.esri.core.geometry.Point;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/20 14:28
 */
public interface QueryLocationTrackPointView extends BaseView{

    void onQueryPointStart();

    void onQueryPointEnd();

    void onQueryPointFailure(int code,String error);

    void onQueryPointSuccess(List<Point> list);
}
