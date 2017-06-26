package com.yunwei.wetlandpark.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lx on 2016/7/26.
 */
public class Attributes implements Serializable{
    @SerializedName("x")
    private double x;

    @SerializedName("y")
    private double y;

    @SerializedName("z")
    private double z;

    @SerializedName("createtime")
    private String createtime;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("remark")
    private String remark;

    @SerializedName("address")
    private String address;

    @SerializedName("status")
    private String status;

    @SerializedName("eventtype")
    private String eventtype;

    @SerializedName("pointtype")
    private String pointtype;

    @SerializedName("handleresults")
    private String handleresults;

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getPointtype() {
        return pointtype;
    }

    public void setPointtype(String pointtype) {
        this.pointtype = pointtype;
    }

    public String getHandleresults() {
        return handleresults;
    }

    public void setHandleresults(String handleresults) {
        this.handleresults = handleresults;
    }
}