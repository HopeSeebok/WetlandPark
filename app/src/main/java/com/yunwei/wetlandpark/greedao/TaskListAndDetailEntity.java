package com.yunwei.wetlandpark.greedao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Entity mapped to table "TASK_LIST_AND_DETAIL_ENTITY".
 */
public class TaskListAndDetailEntity implements Parcelable{

    private Long id;
    private Integer TaskId;
    private Integer TaskType;
    private Integer TaskFormType;
    private Integer TaskMold;
    private Integer TaskStatus;
    private Integer TaskLevel;
    private Integer Expires;
    private String UserId;
    private String TaskNote;
    private String OrderPeople;
    private String Excutor;
    private String TaskRemark;
    private String TaskDeviceNos;
    private String ProcessingTime;
    private String AuditTime;
    private String FinishTime;
    private String EndTime;
    private Boolean IsHistory;
    private String HistoryTime;
    private Integer revint1;
    private Integer revint2;
    private Integer revint3;
    private String revstr1;
    private String revstr2;
    private String revstr3;

    public TaskListAndDetailEntity() {
    }

    public TaskListAndDetailEntity(Long id) {
        this.id = id;
    }

    public TaskListAndDetailEntity(Long id, Integer TaskId, Integer TaskType, Integer TaskFormType, Integer TaskMold, Integer TaskStatus, Integer TaskLevel, Integer Expires, String UserId, String TaskNote, String OrderPeople, String Excutor, String TaskRemark, String TaskDeviceNos, String ProcessingTime, String AuditTime, String FinishTime, String EndTime, Boolean IsHistory, String HistoryTime, Integer revint1, Integer revint2, Integer revint3, String revstr1, String revstr2, String revstr3) {
        this.id = id;
        this.TaskId = TaskId;
        this.TaskType = TaskType;
        this.TaskFormType = TaskFormType;
        this.TaskMold = TaskMold;
        this.TaskStatus = TaskStatus;
        this.TaskLevel = TaskLevel;
        this.Expires = Expires;
        this.UserId = UserId;
        this.TaskNote = TaskNote;
        this.OrderPeople = OrderPeople;
        this.Excutor = Excutor;
        this.TaskRemark = TaskRemark;
        this.TaskDeviceNos = TaskDeviceNos;
        this.ProcessingTime = ProcessingTime;
        this.AuditTime = AuditTime;
        this.FinishTime = FinishTime;
        this.EndTime = EndTime;
        this.IsHistory = IsHistory;
        this.HistoryTime = HistoryTime;
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

    public TaskListAndDetailEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getTaskId() {
        return TaskId;
    }

    public TaskListAndDetailEntity setTaskId(Integer taskId) {
        TaskId = taskId;
        return this;
    }

    public Integer getTaskType() {
        return TaskType;
    }

    public TaskListAndDetailEntity setTaskType(Integer taskType) {
        TaskType = taskType;
        return this;
    }

    public Integer getTaskFormType() {
        return TaskFormType;
    }

    public TaskListAndDetailEntity setTaskFormType(Integer taskFormType) {
        TaskFormType = taskFormType;
        return this;
    }

    public Integer getTaskMold() {
        return TaskMold;
    }

    public TaskListAndDetailEntity setTaskMold(Integer taskMold) {
        TaskMold = taskMold;
        return this;
    }

    public Integer getTaskStatus() {
        return TaskStatus;
    }

    public TaskListAndDetailEntity setTaskStatus(Integer taskStatus) {
        TaskStatus = taskStatus;
        return this;
    }

    public Integer getTaskLevel() {
        return TaskLevel;
    }

    public TaskListAndDetailEntity setTaskLevel(Integer taskLevel) {
        TaskLevel = taskLevel;
        return this;
    }

    public Integer getExpires() {
        return Expires;
    }

    public TaskListAndDetailEntity setExpires(Integer expires) {
        Expires = expires;
        return this;
    }

    public String getUserId() {
        return UserId;
    }

    public TaskListAndDetailEntity setUserId(String userId) {
        UserId = userId;
        return this;
    }

    public String getTaskNote() {
        return TaskNote;
    }

    public TaskListAndDetailEntity setTaskNote(String taskNote) {
        TaskNote = taskNote;
        return this;
    }

    public String getOrderPeople() {
        return OrderPeople;
    }

    public TaskListAndDetailEntity setOrderPeople(String orderPeople) {
        OrderPeople = orderPeople;
        return this;
    }

    public String getExcutor() {
        return Excutor;
    }

    public TaskListAndDetailEntity setExcutor(String excutor) {
        Excutor = excutor;
        return this;
    }

    public String getTaskRemark() {
        return TaskRemark;
    }

    public TaskListAndDetailEntity setTaskRemark(String taskRemark) {
        TaskRemark = taskRemark;
        return this;
    }

    public String getTaskDeviceNos() {
        return TaskDeviceNos;
    }

    public TaskListAndDetailEntity setTaskDeviceNos(String taskDeviceNos) {
        TaskDeviceNos = taskDeviceNos;
        return this;
    }

    public String getProcessingTime() {
        return ProcessingTime;
    }

    public TaskListAndDetailEntity setProcessingTime(String processingTime) {
        ProcessingTime = processingTime;
        return this;
    }

    public String getAuditTime() {
        return AuditTime;
    }

    public TaskListAndDetailEntity setAuditTime(String auditTime) {
        AuditTime = auditTime;
        return this;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public TaskListAndDetailEntity setFinishTime(String finishTime) {
        FinishTime = finishTime;
        return this;
    }

    public String getEndTime() {
        return EndTime;
    }

    public TaskListAndDetailEntity setEndTime(String endTime) {
        EndTime = endTime;
        return this;
    }

    public Boolean getHistory() {
        return IsHistory;
    }

    public TaskListAndDetailEntity setHistory(Boolean history) {
        IsHistory = history;
        return this;
    }

    public String getHistoryTime() {
        return HistoryTime;
    }

    public TaskListAndDetailEntity setHistoryTime(String historyTime) {
        HistoryTime = historyTime;
        return this;
    }

    public Integer getRevint1() {
        return revint1;
    }

    public TaskListAndDetailEntity setRevint1(Integer revint1) {
        this.revint1 = revint1;
        return this;
    }

    public Integer getRevint2() {
        return revint2;
    }

    public TaskListAndDetailEntity setRevint2(Integer revint2) {
        this.revint2 = revint2;
        return this;
    }

    public Integer getRevint3() {
        return revint3;
    }

    public TaskListAndDetailEntity setRevint3(Integer revint3) {
        this.revint3 = revint3;
        return this;
    }

    public String getRevstr1() {
        return revstr1;
    }

    public TaskListAndDetailEntity setRevstr1(String revstr1) {
        this.revstr1 = revstr1;
        return this;
    }

    public String getRevstr2() {
        return revstr2;
    }

    public TaskListAndDetailEntity setRevstr2(String revstr2) {
        this.revstr2 = revstr2;
        return this;
    }

    public String getRevstr3() {
        return revstr3;
    }

    public TaskListAndDetailEntity setRevstr3(String revstr3) {
        this.revstr3 = revstr3;
        return this;
    }

    public String getTaskLevelString(int taskLevel){
        //一般0重要1紧急2
        switch (taskLevel) {
            case 0:
                return "一般";
            case 1:
                return "重要";
            case 2:
                return "紧急";
        }
        return "";
    }

    public String getTaskMoldString(int taskMold){
        switch (taskMold) {
            case 0:
                return "临时";
            case 1:
                return "计划";
            case 2:
                return "上报";
        }
        return "";
    }

    public String getTaskTypeString(int taskType){
        //巡查1维护2
        switch (taskType) {
            case 1:
                return "巡查";
            case 2:
                return "维护";
        }
        return "";
    }

    public String getTaskFormTypeString(int taskFormType){
        // 周期计划 = 1,隐患上报 = 2,临时维修 = 3,
        switch (taskFormType) {
            case 1:
                return "周期计划";
            case 2:
                return "隐患上报";
            case 3:
                return "临时维修";
        }
        return "";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.TaskId);
        dest.writeValue(this.TaskType);
        dest.writeValue(this.TaskFormType);
        dest.writeValue(this.TaskMold);
        dest.writeValue(this.TaskStatus);
        dest.writeValue(this.TaskLevel);
        dest.writeValue(this.Expires);
        dest.writeString(this.UserId);
        dest.writeString(this.TaskNote);
        dest.writeString(this.OrderPeople);
        dest.writeString(this.Excutor);
        dest.writeString(this.TaskRemark);
        dest.writeString(this.TaskDeviceNos);
        dest.writeString(this.ProcessingTime);
        dest.writeString(this.AuditTime);
        dest.writeString(this.FinishTime);
        dest.writeString(this.EndTime);
        dest.writeValue(this.IsHistory);
        dest.writeString(this.HistoryTime);
        dest.writeValue(this.revint1);
        dest.writeValue(this.revint2);
        dest.writeValue(this.revint3);
        dest.writeString(this.revstr1);
        dest.writeString(this.revstr2);
        dest.writeString(this.revstr3);
    }

    protected TaskListAndDetailEntity(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.TaskId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.TaskType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.TaskFormType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.TaskMold = (Integer) in.readValue(Integer.class.getClassLoader());
        this.TaskStatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.TaskLevel = (Integer) in.readValue(Integer.class.getClassLoader());
        this.Expires = (Integer) in.readValue(Integer.class.getClassLoader());
        this.UserId = in.readString();
        this.TaskNote = in.readString();
        this.OrderPeople = in.readString();
        this.Excutor = in.readString();
        this.TaskRemark = in.readString();
        this.TaskDeviceNos = in.readString();
        this.ProcessingTime = in.readString();
        this.AuditTime = in.readString();
        this.FinishTime = in.readString();
        this.EndTime = in.readString();
        this.IsHistory = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.HistoryTime = in.readString();
        this.revint1 = (Integer) in.readValue(Integer.class.getClassLoader());
        this.revint2 = (Integer) in.readValue(Integer.class.getClassLoader());
        this.revint3 = (Integer) in.readValue(Integer.class.getClassLoader());
        this.revstr1 = in.readString();
        this.revstr2 = in.readString();
        this.revstr3 = in.readString();
    }

    public static final Creator<TaskListAndDetailEntity> CREATOR = new Creator<TaskListAndDetailEntity>() {
        @Override
        public TaskListAndDetailEntity createFromParcel(Parcel source) {
            return new TaskListAndDetailEntity(source);
        }

        @Override
        public TaskListAndDetailEntity[] newArray(int size) {
            return new TaskListAndDetailEntity[size];
        }
    };
}
