package com.yunwei.wetlandpark.ui.view;


import com.esri.core.geometry.Point;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/23 11:45
 */
public interface QueryBaiduTrackHistoryView extends BaseView {

    void onQueryBaiduTrackStart();

    void onQueryBaiduTrackEnd();

    void onQueryBaiduTrackSuccess(List<Point> list,double distance);

    void onQueryBaiduTrackFailure(String err);
}
