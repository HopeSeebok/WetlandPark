package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble.data;

import com.yunwei.library.data.Image;

import java.io.Serializable;
import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/6 17:34
 * @description:
 */

public class TroubleShooter implements Serializable{
    private String MissionDetailId;

    private String DangerId;

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
    public void setDangerId(String DangerId){
        this.DangerId = DangerId;
    }
    public String getDangerId(){
        return this.DangerId;
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
