package com.yunwei.wetlandpark.ui.biz.interfac;


import com.esri.core.geometry.Point;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/23 11:47
 */
public interface QueryBaiduTrackHistoryListener {

    void onQueryBaiduTrackStart();

    void onQueryBaiduTrackEnd();

    void onQueryBaiduTrackSuccess(List<Point> list,double distance);

    void onQueryBaiduTrackFailure(String err);
}
