package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView;

import com.esri.core.geometry.Point;
import com.yunwei.wetlandpark.ui.view.BaseView;

import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.view.task
 * @Description:
 * @date 2016/9/27
 * @changeby:
 */

public interface TaskLocationFileChildView extends BaseView{
    void setView(List<Point> points, String imagesEntities);
}
