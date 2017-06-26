package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.data;

/**
 * @author CBOK
 * @date 2016/12/16 11:56
 * @description:
 */

public class SignInfo {
    private String DeviceId;

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    private double Lat;

    private double Lng;

    private String Addr;

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }
}
