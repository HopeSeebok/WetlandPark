package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.data;

/**
 * @author CBOK
 * @date 2016/11/10 19:19
 * @description:
 */

public class CheckPlanEntity {
    private String PlanId;

    private String Date;

    private String StartAt;

    private String EndAt;

    private String[][] Values ;

    private String Note;

    public void setPlanId(String PlanId){
        this.PlanId = PlanId;
    }
    public String getPlanId(){
        return this.PlanId;
    }
    public void setDate(String Date){
        this.Date = Date;
    }
    public String getDate(){
        return this.Date;
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
    public String[][] getValues() {
        return Values;
    }
    public void setValues(String[][] values) {
        Values = values;
    }
    public void setNote(String Note){
        this.Note = Note;
    }
    public String getNote(){
        return this.Note;
    }

}
