package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data;


import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;

import java.io.Serializable;

/**
 * @package com.yunwei.water.entity
 * @description 线段实体类
 * @author yangdu
 * @date 2016/10/27
 * @time 上午9:35
 * @version
 **/
public class LineSegmentEntity implements Serializable{

    //线段
    private Polyline polyline;

    //开始节点
    private Point startPoint;

    //结束节点
    private Point endPoint;

    //颜色
    private int color;

    //半径
    private int radius;

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public LineSegmentEntity() {
    }

    public LineSegmentEntity(Polyline polyline, Point startPoint, Point endPoint, int color, int radius) {
        this.polyline = polyline;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.color = color;
        this.radius = radius;
    }
}
