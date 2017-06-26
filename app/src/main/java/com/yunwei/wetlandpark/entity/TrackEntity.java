package com.yunwei.wetlandpark.entity;

import java.io.Serializable;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.entity
 * @Description:
 * @date 2016/9/9 9:52
 */
public class TrackEntity implements Serializable{

    private String StartTimeSTR;

    private String EndTimeSTR;

    private int FootPrintID;

    private int UserID;

    private String FootDesc;

    private String CreateTime;

    private String StartTime;

    private String EndTime;

    private double FootDistance;

    private double Duration;

    private int UnitId;

    public String getStartTimeSTR() {
        return StartTimeSTR;
    }

    public void setStartTimeSTR(String startTimeSTR) {
        StartTimeSTR = startTimeSTR;
    }

    public String getEndTimeSTR() {
        return EndTimeSTR;
    }

    public void setEndTimeSTR(String endTimeSTR) {
        EndTimeSTR = endTimeSTR;
    }

    public int getFootPrintID() {
        return FootPrintID;
    }

    public void setFootPrintID(int footPrintID) {
        FootPrintID = footPrintID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getFootDesc() {
        return FootDesc;
    }

    public void setFootDesc(String footDesc) {
        FootDesc = footDesc;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public double getFootDistance() {
        return FootDistance;
    }

    public void setFootDistance(double footDistance) {
        FootDistance = footDistance;
    }

    public double getDuration() {
        return Duration;
    }

    public void setDuration(double duration) {
        Duration = duration;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }
}
