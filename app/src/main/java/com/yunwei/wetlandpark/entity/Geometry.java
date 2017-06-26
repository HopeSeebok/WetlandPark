package com.yunwei.wetlandpark.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lx on 2016/7/26.
 */
public class Geometry implements Serializable{
    @SerializedName("x")
    private double x;

    @SerializedName("y")
    private double y;

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
}