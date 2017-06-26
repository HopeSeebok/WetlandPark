package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice.data;

import com.yunwei.library.data.Image;

import java.util.List;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.deviceFunctions.deviceFunction.maintainDevice.data
 * @Description:
 * @date 2016/11/17 15:03
 */

public class MaintainDeviceEntity {

    private String MissionDetailId;

    private String DeviceId;

    private int DeviceStatus;

    private double WorkLat;

    private double WorkLng;

    private String WorkAddr;

    private String Note;

    private List<Image> WorkImgs ;

    public void setMissionDetailId(String MissionDetailId){
        this.MissionDetailId = MissionDetailId;
    }
    public String getMissionDetailId(){
        return this.MissionDetailId;
    }
    public void setDeviceId(String DeviceId){
        this.DeviceId = DeviceId;
    }
    public String getDeviceId(){
        return this.DeviceId;
    }
    public void setDeviceStatus(int DeviceStatus){
        this.DeviceStatus = DeviceStatus;
    }
    public int getDeviceStatus(){
        return this.DeviceStatus;
    }
    public void setWorkLat(double WorkLat){
        this.WorkLat = WorkLat;
    }
    public double getWorkLat(){
        return this.WorkLat;
    }
    public void setWorkLng(double WorkLng){
        this.WorkLng = WorkLng;
    }
    public double getWorkLng(){
        return this.WorkLng;
    }
    public void setWorkAddr(String WorkAddr){
        this.WorkAddr = WorkAddr;
    }
    public String getWorkAddr(){
        return this.WorkAddr;
    }
    public void setNote(String Note){
        this.Note = Note;
    }
    public String getNote(){
        return this.Note;
    }
    public void setWorkImgs(List<Image> WorkImgs){
        this.WorkImgs = WorkImgs;
    }
    public List<Image> getWorkImgs(){
        return this.WorkImgs;
    }

}