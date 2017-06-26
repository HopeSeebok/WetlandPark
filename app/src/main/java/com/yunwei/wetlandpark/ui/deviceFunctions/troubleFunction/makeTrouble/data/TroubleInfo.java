package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data;

import com.yunwei.library.data.Image;

import java.io.Serializable;
import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/2 17:01
 * @description:
 */

public class TroubleInfo implements Serializable{
    private String Code;

    private String Note;

    private double Lat;

    private double Lng;

    private String Addr;

    private String Contact;

    private String Phone;

    private String DangerType;

    private String DeviceId;

    private int MissionLevel;

    private String HandlerName;

    private String HandlerId;

    private List<Image> Imgs ;

    public void setCode(String Code){
        this.Code = Code;
    }
    public String getCode(){
        return this.Code;
    }
    public void setNote(String Note){
        this.Note = Note;
    }
    public String getNote(){
        return this.Note;
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
    public void setDangerType(String DangerType){
        this.DangerType = DangerType;
    }
    public String getDangerType(){
        return this.DangerType;
    }
    public void setDeviceId(String DeviceId){
        this.DeviceId = DeviceId;
    }
    public String getDeviceId(){
        return this.DeviceId;
    }
    public void setMissionLevel(int MissionLevel){
        this.MissionLevel = MissionLevel;
    }
    public int getMissionLevel(){
        return this.MissionLevel;
    }
    public void setHandlerName(String HandlerName){
        this.HandlerName = HandlerName;
    }
    public String getHandlerName(){
        return this.HandlerName;
    }
    public void setHandlerId(String HandlerId){
        this.HandlerId = HandlerId;
    }
    public String getHandlerId(){
        return this.HandlerId;
    }
    public void setImgs(List<Image> Imgs){
        this.Imgs = Imgs;
    }
    public List<Image> getImgs(){
        return this.Imgs;
    }
}