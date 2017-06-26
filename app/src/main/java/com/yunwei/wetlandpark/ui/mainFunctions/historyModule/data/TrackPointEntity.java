package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data;

import java.io.Serializable;

/**
 * @author CBOK
 * @date 2016/12/21 16:20
 * @description:
 */

public class TrackPointEntity implements Serializable{
    public TrackPointEntity(double x, double y, long locTime) {
        this.x = x;
        this.y = y;
        this.locTime = locTime;
    }

    private double x;
    private double y;
    /**
     * 采集时间
     */
    private long locTime;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public long getLocTime() {
        return locTime;
    }

    public void setLocTime(long locTime) {
        this.locTime = locTime;
    }
}
