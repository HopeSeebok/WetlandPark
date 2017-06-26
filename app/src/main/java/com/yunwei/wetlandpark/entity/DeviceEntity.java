package com.yunwei.wetlandpark.entity;

import java.io.Serializable;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc
 * @Description:设施详情实体
 * @date 2016/11/1 10:04
 */

public class DeviceEntity implements Serializable {
    private String Id;
    private String Name;
    private String Code;
    private String RFID;
    private double Lat;
    private double Lng;
    private String Addr;
    private int DeviceTypeId;
    private String DeviceTypeName;
    private String Contact;
    private String Phone;
    private String Ext;
    private String CreateTime;
    private String UpdateTime;
    private String StartAt;
    private String EndAt;
    private String Spec;
    private String UseDept;
    private int DeviceStatus;
    private Images Imgs;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

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

    public int getDeviceTypeId() {
        return DeviceTypeId;
    }

    public void setDeviceTypeId(int deviceTypeId) {
        DeviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return DeviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        DeviceTypeName = deviceTypeName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getExt() {
        return Ext;
    }

    public void setExt(String ext) {
        Ext = ext;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public String getStartAt() {
        return StartAt;
    }

    public void setStartAt(String startAt) {
        StartAt = startAt;
    }

    public String getEndAt() {
        return EndAt;
    }

    public void setEndAt(String endAt) {
        EndAt = endAt;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        Spec = spec;
    }

    public String getUseDept() {
        return UseDept;
    }

    public void setUseDept(String useDept) {
        UseDept = useDept;
    }

    public int getDeviceStatus() {
        return DeviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        DeviceStatus = deviceStatus;
    }

    public Images getImgs() {
        return Imgs;
    }

    public void setImgs(Images imgs) {
        Imgs = imgs;
    }
}
