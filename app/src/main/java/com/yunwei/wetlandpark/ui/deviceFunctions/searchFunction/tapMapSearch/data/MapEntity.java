package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.data;

import java.io.Serializable;

/**
 * @author CBOK
 * @date 2016/12/5 22:40
 * @description:
 */

public class MapEntity implements Serializable{

    /*共有字段*/
    private int layerID;
    private String id;
    /*隐患图层特有字段*/
    private int emergencyLevel;
    private int troubleStatus;
    private String troubleType;
    /*设施图层特有字段*/
    private String code;


    public int getLayerID() {
        return layerID;
    }

    public void setLayerID(int layerID) {
        this.layerID = layerID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEmergencyLevel() {
        return emergencyLevel;
    }

    public void setEmergencyLevel(int emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    public int getTroubleStatus() {
        return troubleStatus;
    }

    public void setTroubleStatus(int troubleStatus) {
        this.troubleStatus = troubleStatus;
    }

    public String getTroubleType() {
        return troubleType;
    }

    public void setTroubleType(String troubleType) {
        this.troubleType = troubleType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
