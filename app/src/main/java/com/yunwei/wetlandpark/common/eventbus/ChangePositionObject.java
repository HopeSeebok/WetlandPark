package com.yunwei.wetlandpark.common.eventbus;

/**
 * Created by SEEBOK on 2016/9/19.
 */
public class ChangePositionObject {
    private double lng;
    private double lat;
    private String address;

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
