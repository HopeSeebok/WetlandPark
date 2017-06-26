package com.yunwei.wetlandpark.entity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.entity
 * @Description:
 * @date 2016/7/28 9:40
 */
public class RefreshLoactionEntity {

    private int PointID;

    private double X;

    private double Y;

    private double XSource;

    private double YSource;

    private double XMercator;

    private double YMercator;

    private double Z;

    private double Altitude;

    private double Accuracy;

    private double Bearing;

    private double Speed;

    private String Provider;

    private String CreateTime;

    private String CreateTimeSTR;

    private int UserID;

    private long CollectTime;

    public int getPointID() {
        return PointID;
    }

    public void setPointID(int pointID) {
        PointID = pointID;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getXSource() {
        return XSource;
    }

    public void setXSource(double XSource) {
        this.XSource = XSource;
    }

    public double getYSource() {
        return YSource;
    }

    public void setYSource(double YSource) {
        this.YSource = YSource;
    }

    public double getXMercator() {
        return XMercator;
    }

    public void setXMercator(double XMercator) {
        this.XMercator = XMercator;
    }

    public double getYMercator() {
        return YMercator;
    }

    public void setYMercator(double YMercator) {
        this.YMercator = YMercator;
    }

    public double getZ() {
        return Z;
    }

    public void setZ(double z) {
        Z = z;
    }

    public double getAltitude() {
        return Altitude;
    }

    public void setAltitude(double altitude) {
        Altitude = altitude;
    }

    public double getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(double accuracy) {
        Accuracy = accuracy;
    }

    public double getBearing() {
        return Bearing;
    }

    public void setBearing(double bearing) {
        Bearing = bearing;
    }

    public double getSpeed() {
        return Speed;
    }

    public void setSpeed(double speed) {
        Speed = speed;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        Provider = provider;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreateTimeSTR() {
        return CreateTimeSTR;
    }

    public void setCreateTimeSTR(String createTimeSTR) {
        CreateTimeSTR = createTimeSTR;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public long getCollectTime() {
        return CollectTime;
    }

    public void setCollectTime(long collectTime) {
        CollectTime = collectTime;
    }
}
