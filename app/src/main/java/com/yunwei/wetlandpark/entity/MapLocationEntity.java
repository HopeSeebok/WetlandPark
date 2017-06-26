package com.yunwei.wetlandpark.entity;

import com.esri.core.geometry.Point;
import com.yunwei.map.entity.MPointEntity;
import com.yunwei.map.utils.ILngLatMercator;

import java.io.Serializable;

/**
 * @author WuQianRui
 * @version V1.0
 * @Package com.yunwei.water
 * @Description 用于存储位置信息的自定义实体类
 * @Date 2016/9/30 .
 */
public class MapLocationEntity implements Serializable {

    double mLat = 0.0;
    double mLng = 0.0;
    double x = 0.0;//墨卡托坐标
    double y = 0.0;//墨卡托坐标
    double z= 0.0;//墨卡托坐标
    String address = "";
    String road = "";//路段
    String zone = "";//片区
    String code = "";//相关编码，可以是设施编码，也可以是位置编码
    int extraInt1 = 0;//附加字段
    String extraString1 = "";//附加字段

    public MapLocationEntity() {
    }

    public MapLocationEntity(double lat, double lng) {
        this.mLat = lat;
        this.mLng = lng;
        //经纬转墨卡托
        final MPointEntity mercatorPoint = ILngLatMercator
                .lonLat2WebMercator(lng, lat);
        Point point = new Point(mercatorPoint.getX(), mercatorPoint.getY());
        this.x=point.getX();
        this.y=point.getY();
    }

    //要进行赋值的时候要清除之前留下来的数据
    public void clear(){
        this.code="";
        this.address="";
        this.road="";
        this.zone="";
        this.mLng=0.0;
        this.mLat=0.0;
        this.x=0.0;
        this.y=0.0;
        this.z=0.0;
        this.extraInt1=0;
        this.extraString1="";

    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getExtraInt1() {
        return extraInt1;
    }

    public void setExtraInt1(int extraInt1) {
        this.extraInt1 = extraInt1;
    }

    public String getExtraString1() {
        return extraString1;
    }

    public void setExtraString1(String extraString1) {
        this.extraString1 = extraString1;
    }
}
