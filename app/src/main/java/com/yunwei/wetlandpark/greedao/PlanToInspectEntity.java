package com.yunwei.wetlandpark.greedao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PLAN_TO_INSPECT_ENTITY".
 */
public class PlanToInspectEntity {

    private Long id;
    private String PlanId;
    private String PlanName;
    private String UserId;
    private String PlanRule;
    private String StartTime;
    private String EndTime;
    private String Executor;
    private String Note;
    private String Codes;
    private String Rfids;
    private Integer revint1;
    private Integer revint2;
    private Integer revint3;
    private String revstr1;
    private String revstr2;
    private String revstr3;

    public PlanToInspectEntity() {
    }

    public PlanToInspectEntity(Long id) {
        this.id = id;
    }

    public PlanToInspectEntity(Long id, String PlanId, String PlanName, String UserId, String PlanRule, String StartTime, String EndTime, String Executor, String Note, String Codes, String Rfids, Integer revint1, Integer revint2, Integer revint3, String revstr1, String revstr2, String revstr3) {
        this.id = id;
        this.PlanId = PlanId;
        this.PlanName = PlanName;
        this.UserId = UserId;
        this.PlanRule = PlanRule;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.Executor = Executor;
        this.Note = Note;
        this.Codes = Codes;
        this.Rfids = Rfids;
        this.revint1 = revint1;
        this.revint2 = revint2;
        this.revint3 = revint3;
        this.revstr1 = revstr1;
        this.revstr2 = revstr2;
        this.revstr3 = revstr3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanId() {
        return PlanId;
    }

    public void setPlanId(String PlanId) {
        this.PlanId = PlanId;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String PlanName) {
        this.PlanName = PlanName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getPlanRule() {
        return PlanRule;
    }

    public void setPlanRule(String PlanRule) {
        this.PlanRule = PlanRule;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getExecutor() {
        return Executor;
    }

    public void setExecutor(String Executor) {
        this.Executor = Executor;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public String getCodes() {
        return Codes;
    }

    public void setCodes(String Codes) {
        this.Codes = Codes;
    }

    public String getRfids() {
        return Rfids;
    }

    public void setRfids(String Rfids) {
        this.Rfids = Rfids;
    }

    public Integer getRevint1() {
        return revint1;
    }

    public void setRevint1(Integer revint1) {
        this.revint1 = revint1;
    }

    public Integer getRevint2() {
        return revint2;
    }

    public void setRevint2(Integer revint2) {
        this.revint2 = revint2;
    }

    public Integer getRevint3() {
        return revint3;
    }

    public void setRevint3(Integer revint3) {
        this.revint3 = revint3;
    }

    public String getRevstr1() {
        return revstr1;
    }

    public void setRevstr1(String revstr1) {
        this.revstr1 = revstr1;
    }

    public String getRevstr2() {
        return revstr2;
    }

    public void setRevstr2(String revstr2) {
        this.revstr2 = revstr2;
    }

    public String getRevstr3() {
        return revstr3;
    }

    public void setRevstr3(String revstr3) {
        this.revstr3 = revstr3;
    }

}
