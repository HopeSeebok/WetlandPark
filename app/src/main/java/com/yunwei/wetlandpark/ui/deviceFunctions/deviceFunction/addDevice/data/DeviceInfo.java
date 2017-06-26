package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data;

import com.yunwei.library.data.Image;

import java.io.Serializable;
import java.util.List;

/**
 * @author CBOK
 * @date 2016/11/9 9:55
 * @description:
 */

public class DeviceInfo implements Serializable{
    private String Id;

    private String Icon;

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

    private String CreateTime;

    private String UpdateTime;

    private String StartAt;

    private String EndAt;

    private String Spec;

    private String UseDept;

    private String Ext;

    private int DeviceStatus;

    private List<Image> Imgs ;

    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return this.Name;
    }
    public void setCode(String Code){
        this.Code = Code;
    }
    public String getCode(){
        return this.Code;
    }
    public void setRFID(String RFID){
        this.RFID = RFID;
    }
    public String getRFID(){
        return this.RFID;
    }
    public void setLat(double Lat){
        this.Lat = Lat;
    }
    public double getLat(){
        return this.Lat;
    }
    public void setLng(double Lng){
        this.Lng = Lng;
    }
    public double getLng(){
        return this.Lng;
    }
    public void setAddr(String Addr){
        this.Addr = Addr;
    }
    public String getAddr(){
        return this.Addr;
    }
    public void setDeviceTypeId(int DeviceTypeId){
        this.DeviceTypeId = DeviceTypeId;
    }
    public int getDeviceTypeId(){
        return this.DeviceTypeId;
    }
    public void setContact(String Contact){
        this.Contact = Contact;
    }
    public String getContact(){
        return this.Contact;
    }
    public void setPhone(String Phone){
        this.Phone = Phone;
    }
    public String getPhone(){
        return this.Phone;
    }
    public void setStartAt(String StartAt){
        this.StartAt = StartAt;
    }
    public String getStartAt(){
        return this.StartAt;
    }
    public void setEndAt(String EndAt){
        this.EndAt = EndAt;
    }
    public String getEndAt(){
        return this.EndAt;
    }
    public void setSpec(String Spec){
        this.Spec = Spec;
    }
    public String getSpec(){
        return this.Spec;
    }
    public void setUseDept(String UseDept){
        this.UseDept = UseDept;
    }
    public String getUseDept(){
        return this.UseDept;
    }
    public void setExt(String Ext){
        this.Ext = Ext;
    }
    public String getExt(){
        return this.Ext;
    }
    public void setImgs(List<Image> Imgs){
        this.Imgs = Imgs;
    }
    public List<Image> getImgs(){
        return this.Imgs;
    }
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getIcon() {
        return Icon;
    }
    public void setIcon(String icon) {
        Icon = icon;
    }
    public String getDeviceTypeName() {
        return DeviceTypeName;
    }
    public void setDeviceTypeName(String deviceTypeName) {
        DeviceTypeName = deviceTypeName;
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
    public int getDeviceStatus() {
        return DeviceStatus;
    }
    public void setDeviceStatus(int deviceStatus) {
        DeviceStatus = deviceStatus;
    }
}
