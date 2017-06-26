package com.yunwei.wetlandpark.entity.checkingRecord;

import java.util.List;

/**
 * Created by SEEBOK on 2016/9/12.
 */
public class WorkRecordRemote{

    private String TaskId;

    private String TaskPointId;

    private String WorkDesc;

    private String WorkState;

    private String WorkReason;

    private String WorkResult;

    private String ContactTel;

    private String FacilityCode;

    private String WorkAddress;

    private String Workers;

    private String WorkRemark;

    private int RecordType;

    private ArcgisYhdModel ArcgisYhdModel;

    private List<com.yunwei.wetlandpark.entity.Images> Images;

    public List<com.yunwei.wetlandpark.entity.Images> getImages() {
        return Images;
    }

    public void setImages(List<com.yunwei.wetlandpark.entity.Images> images) {
        Images = images;
    }


    public void setTaskId(String TaskId) {
        this.TaskId = TaskId;
    }

    public String getTaskId() {
        return this.TaskId;
    }

    public void setTaskPointId(String TaskPointId) {
        this.TaskPointId = TaskPointId;
    }

    public String getTaskPointId() {
        return this.TaskPointId;
    }

    public void setRecordType(int RecordType) {
        this.RecordType = RecordType;
    }

    public int getRecordType() {
        return this.RecordType;
    }



    public void setWorkDesc(String WorkDesc) {
        this.WorkDesc = WorkDesc;
    }

    public String getWorkDesc() {
        return this.WorkDesc;
    }



    public void setWorkAddress(String WorkAddress) {
        this.WorkAddress = WorkAddress;
    }

    public String getWorkAddress() {
        return this.WorkAddress;
    }

    public void setArcgisYhdModel(ArcgisYhdModel ArcgisYhdModel) {
        this.ArcgisYhdModel = ArcgisYhdModel;
    }

    public ArcgisYhdModel getArcgisYhdModel() {
        return this.ArcgisYhdModel;
    }

    public String getWorkState() {
        return WorkState;
    }

    public void setWorkState(String workState) {
        WorkState = workState;
    }

    public String getWorkReason() {
        return WorkReason;
    }

    public void setWorkReason(String workReason) {
        WorkReason = workReason;
    }

    public String getWorkResult() {
        return WorkResult;
    }

    public void setWorkResult(String workResult) {
        WorkResult = workResult;
    }

    public String getContactTel() {
        return ContactTel;
    }

    public void setContactTel(String contactTel) {
        ContactTel = contactTel;
    }

    public String getFacilityCode() {
        return FacilityCode;
    }

    public void setFacilityCode(String facilityCode) {
        FacilityCode = facilityCode;
    }

    public String getWorkers() {
        return Workers;
    }

    public void setWorkers(String workers) {
        Workers = workers;
    }

    public String getWorkRemark() {
        return WorkRemark;
    }

    public void setWorkRemark(String workRemark) {
        WorkRemark = workRemark;
    }
}
