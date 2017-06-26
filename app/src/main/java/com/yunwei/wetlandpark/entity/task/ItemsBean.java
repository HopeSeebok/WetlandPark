
package com.yunwei.wetlandpark.entity.task;

import java.util.List;

/**
 * Created by longma on 2016/7/11.
 */

public class ItemsBean {
    private String CurrentId;

    public String getCurrentId() {
        return CurrentId;
    }

    public void setCurrentId(String currentId) {
        CurrentId = currentId;
    }

    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    /**
     * WorkTaskPoints : [{"Device":{"XhsDeviceType":1,"TaskId":"sample string 1","No":"sample string 2","RFID":"sample string 3","DeviceType":1,"Location":{"Lng":1.1,"Lat":2.1,"Name":"sample string 3","UpdateTime":"2016-07-06 17:02:57"}}},{"Device":{"XhsDeviceType":1,"TaskId":"sample string 1","No":"sample string 2","RFID":"sample string 3","DeviceType":1,"Location":{"Lng":1.1,"Lat":2.1,"Name":"sample string 3","UpdateTime":"2016-07-06 17:02:57"}}}]
     * TaskId : sample string 1
     * No : sample string 2
     * TaskDesc : sample string 3
     * Remarks : sample string 4
     * BackReason : sample string 5
     * TaskUrgent : 1
     * TaskType : 1
     * WorkTaskType : 1
     * TaskStatus : 0
     * OrderTime : 2016-07-06 17:02:57
     * ReceiveTime : 2016-07-06 17:02:57
     * BackTime : 2016-07-06 17:02:57
     * RecycleTime : 2016-07-06 17:02:57
     * DealTime : 2016-07-06 17:02:57
     * FinishTime : 2016-07-06 17:02:57
     * CheckTime : 2016-07-06 17:02:57
     * Ower : {"TaskId":"sample string 1","Name":"sample string 2"}
     * Executor : {"TaskId":"sample string 1","Name":"sample string 2"}
     * Audit : {"IsAudit":true,"Auditor":{"TaskId":"sample string 1","Name":"sample string 2"},"AuditTime":"2016-07-06 17:02:57"}
     * expires_in 任务时间
     Images
     */

    private  int IsHistory;//标志是否历史  0：新任务 1：历史
    private  String HistoryTime;//
    private  int Expries;//任务时间

    private String TaskId;
    private String No;
    private String TaskDesc;
    private String Remarks;
    private String BackReason;
    private String TaskUrgent;
    private String TaskType;
    private String WorkTaskType;
    private int TaskStatus;
    private String OrderTime;
    private String ReceiveTime;
    private String BackTime;
    private String RecycleTime;
    private String DealTime;
    private String FinishTime;
    private String CheckTime;
    /**
     * TaskId : sample string 1
     * Name : sample string 2
     */

    private OwerBean Ower;
    /**
     * TaskId : sample string 1
     * Name : sample string 2
     */

    private ExecutorBean Executor;
    /**
     * IsAudit : true
     * Auditor : {"TaskId":"sample string 1","Name":"sample string 2"}
     * AuditTime : 2016-07-06 17:02:57
     */

    private AuditBean Audit;


    /**
     * Device : {"XhsDeviceType":1,"TaskId":"sample string 1","No":"sample string 2","RFID":"sample string 3","DeviceType":1,"Location":{"Lng":1.1,"Lat":2.1,"Name":"sample string 3","UpdateTime":"2016-07-06 17:02:57"}}
     */


    private List<WorkTaskPointsBean> WorkTaskPoints;

    public int getExpries() {
        return Expries;
    }

    public void setExpries(int expries) {
        Expries = expries;
    }

    private List<ImagesBean> Images;

    public List<ImagesBean> getImages() {
        return Images;
    }

    public void setImages(List<ImagesBean> images) {
        Images = images;
    }

    public String getHistoryTime() {
        return HistoryTime;
    }

    public void setHistoryTime(String historyTime) {
        HistoryTime = historyTime;
    }

    public int getIsHistory() {
        return IsHistory;
    }

    public void setIsHistory(int isHistory) {
        IsHistory = isHistory;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String Id) {
        this.TaskId = Id;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String No) {
        this.No = No;
    }

    public String getTaskDesc() {
        return TaskDesc;
    }

    public void setTaskDesc(String TaskDesc) {
        this.TaskDesc = TaskDesc;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getBackReason() {
        return BackReason;
    }

    public void setBackReason(String BackReason) {
        this.BackReason = BackReason;
    }

    public String getTaskUrgent() {
        return TaskUrgent;
    }

    public void setTaskUrgent(String TaskUrgent) {
        this.TaskUrgent = TaskUrgent;
    }

    public String getTaskType() {
        return TaskType;
    }

    public void setTaskType(String TaskType) {
        this.TaskType = TaskType;
    }

    public String getWorkTaskType() {
        return WorkTaskType;
    }

    public void setWorkTaskType(String WorkTaskType) {
        this.WorkTaskType = WorkTaskType;
    }

    public int getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(int TaskStatus) {
        this.TaskStatus = TaskStatus;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String OrderTime) {
        this.OrderTime = OrderTime;
    }

    public String getReceiveTime() {
        return ReceiveTime;
    }

    public void setReceiveTime(String ReceiveTime) {
        this.ReceiveTime = ReceiveTime;
    }

    public String getBackTime() {
        return BackTime;
    }

    public void setBackTime(String BackTime) {
        this.BackTime = BackTime;
    }

    public String getRecycleTime() {
        return RecycleTime;
    }

    public void setRecycleTime(String RecycleTime) {
        this.RecycleTime = RecycleTime;
    }

    public String getDealTime() {
        return DealTime;
    }

    public void setDealTime(String DealTime) {
        this.DealTime = DealTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String FinishTime) {
        this.FinishTime = FinishTime;
    }

    public String getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(String CheckTime) {
        this.CheckTime = CheckTime;
    }

    public OwerBean getOwer() {
        return Ower;
    }

    public void setOwer(OwerBean Ower) {
        this.Ower = Ower;
    }

    public ExecutorBean getExecutor() {
        return Executor;
    }

    public void setExecutor(ExecutorBean Executor) {
        this.Executor = Executor;
    }

    public AuditBean getAudit() {
        return Audit;
    }

    public void setAudit(AuditBean Audit) {
        this.Audit = Audit;
    }

    public List<WorkTaskPointsBean> getWorkTaskPoints() {
        return WorkTaskPoints;
    }

    public void setWorkTaskPoints(List<WorkTaskPointsBean> WorkTaskPoints) {
        this.WorkTaskPoints = WorkTaskPoints;
    }


    //        public static class OwerBean implements Parcelable {
//            private String TaskId;
//            private String Name;
//
//            public String getTaskId() {
//                return TaskId;
//            }
//
//            public void setTaskId(String TaskId) {
//                this.TaskId = TaskId;
//            }
//
//            public String getName() {
//                return Name;
//            }
//
//            public void setName(String Name) {
//                this.Name = Name;
//            }
//
//
//            @Override
//            public int describeContents() {
//                return 0;
//            }
//
//            @Override
//            public void writeToParcel(Parcel dest, int flags) {
//                dest.writeString(this.TaskId);
//                dest.writeString(this.Name);
//            }
//
//            public OwerBean() {
//            }
//
//            protected OwerBean(Parcel in) {
//                this.TaskId = in.readString();
//                this.Name = in.readString();
//            }
//
//            public static final Creator<OwerBean> CREATOR = new Creator<OwerBean>() {
//                public OwerBean createFromParcel(Parcel source) {
//                    return new OwerBean(source);
//                }
//
//                public OwerBean[] newArray(int size) {
//                    return new OwerBean[size];
//                }
//            };
//        }

//        public static class ExecutorBean implements Parcelable {
//            private String TaskId;
//            private String Name;
//
//            public String getTaskId() {
//                return TaskId;
//            }
//
//            public void setTaskId(String TaskId) {
//                this.TaskId = TaskId;
//            }
//
//            public String getName() {
//                return Name;
//            }
//
//            public void setName(String Name) {
//                this.Name = Name;
//            }
//
//            @Override
//            public int describeContents() {
//                return 0;
//            }
//
//            @Override
//            public void writeToParcel(Parcel dest, int flags) {
//                dest.writeString(this.TaskId);
//                dest.writeString(this.Name);
//            }
//
//            public ExecutorBean() {
//            }
//
//            protected ExecutorBean(Parcel in) {
//                this.TaskId = in.readString();
//                this.Name = in.readString();
//            }
//
//            public static final Creator<ExecutorBean> CREATOR = new Creator<ExecutorBean>() {
//                public ExecutorBean createFromParcel(Parcel source) {
//                    return new ExecutorBean(source);
//                }
//
//                public ExecutorBean[] newArray(int size) {
//                    return new ExecutorBean[size];
//                }
//            };
//        }

//        public static class AuditBean implements Parcelable {
//            private boolean IsAudit;
//            /**
//             * TaskId : sample string 1
//             * Name : sample string 2
//             */
//
//            private AuditorBean Auditor;
//            private String AuditTime;
//
//            public boolean isIsAudit() {
//                return IsAudit;
//            }
//
//            public void setIsAudit(boolean IsAudit) {
//                this.IsAudit = IsAudit;
//            }
//
//            public AuditorBean getAuditor() {
//                return Auditor;
//            }
//
//            public void setAuditor(AuditorBean Auditor) {
//                this.Auditor = Auditor;
//            }
//
//            public String getAuditTime() {
//                return AuditTime;
//            }
//
//            public void setAuditTime(String AuditTime) {
//                this.AuditTime = AuditTime;
//            }
//
//            public static class AuditorBean implements Parcelable {
//                private String TaskId;
//                private String Name;
//
//                public String getTaskId() {
//                    return TaskId;
//                }
//
//                public void setTaskId(String TaskId) {
//                    this.TaskId = TaskId;
//                }
//
//                public String getName() {
//                    return Name;
//                }
//
//                public void setName(String Name) {
//                    this.Name = Name;
//                }
//
//                @Override
//                public int describeContents() {
//                    return 0;
//                }
//
//                @Override
//                public void writeToParcel(Parcel dest, int flags) {
//                    dest.writeString(this.TaskId);
//                    dest.writeString(this.Name);
//                }
//
//                public AuditorBean() {
//                }
//
//                protected AuditorBean(Parcel in) {
//                    this.TaskId = in.readString();
//                    this.Name = in.readString();
//                }
//
//                public static final Creator<AuditorBean> CREATOR = new Creator<AuditorBean>() {
//                    public AuditorBean createFromParcel(Parcel source) {
//                        return new AuditorBean(source);
//                    }
//
//                    public AuditorBean[] newArray(int size) {
//                        return new AuditorBean[size];
//                    }
//                };
//            }
//
//            @Override
//            public int describeContents() {
//                return 0;
//            }
//
//            @Override
//            public void writeToParcel(Parcel dest, int flags) {
//                dest.writeByte(IsAudit ? (byte) 1 : (byte) 0);
//                dest.writeParcelable(this.Auditor, flags);
//                dest.writeString(this.AuditTime);
//            }
//
//            public AuditBean() {
//            }
//
//            protected AuditBean(Parcel in) {
//                this.IsAudit = in.readByte() != 0;
//                this.Auditor = in.readParcelable(AuditorBean.class.getClassLoader());
//                this.AuditTime = in.readString();
//            }
//
//            public static final Creator<AuditBean> CREATOR = new Creator<AuditBean>() {
//                public AuditBean createFromParcel(Parcel source) {
//                    return new AuditBean(source);
//                }
//
//                public AuditBean[] newArray(int size) {
//                    return new AuditBean[size];
//                }
//            };
//        }

//        public static class WorkTaskPointsBean {
//            /**
//             * XhsDeviceType : 1
//             * TaskId : sample string 1
//             * No : sample string 2
//             * RFID : sample string 3
//             * DeviceType : 1
//             * Location : {"Lng":1.1,"Lat":2.1,"Name":"sample string 3","UpdateTime":"2016-07-06 17:02:57"}
//             */
//
//            private DeviceBean Device;
//
//            public DeviceBean getDevice() {
//                return Device;
//            }
//
//            public void setDevice(DeviceBean Device) {
//                this.Device = Device;
//            }
//
//            public static class DeviceBean {
//                private int XhsDeviceType;
//                private String TaskId;
//                private String No;
//                private String RFID;
//                private int DeviceType;
//                /**
//                 * Lng : 1.1
//                 * Lat : 2.1
//                 * Name : sample string 3
//                 * UpdateTime : 2016-07-06 17:02:57
//                 */
//
//                private LocationBean Location;
//
//                public int getXhsDeviceType() {
//                    return XhsDeviceType;
//                }
//
//                public void setXhsDeviceType(int XhsDeviceType) {
//                    this.XhsDeviceType = XhsDeviceType;
//                }
//
//                public String getTaskId() {
//                    return TaskId;
//                }
//
//                public void setTaskId(String TaskId) {
//                    this.TaskId = TaskId;
//                }
//
//                public String getNo() {
//                    return No;
//                }
//
//                public void setNo(String No) {
//                    this.No = No;
//                }
//
//                public String getRFID() {
//                    return RFID;
//                }
//
//                public void setRFID(String RFID) {
//                    this.RFID = RFID;
//                }
//
//                public int getDeviceType() {
//                    return DeviceType;
//                }
//
//                public void setDeviceType(int DeviceType) {
//                    this.DeviceType = DeviceType;
//                }
//
//                public LocationBean getLocation() {
//                    return Location;
//                }
//
//                public void setLocation(LocationBean Location) {
//                    this.Location = Location;
//                }
//
//                public static class LocationBean {
//                    private double Lng;
//                    private double Lat;
//                    private String Name;
//                    private String UpdateTime;
//
//                    public double getLng() {
//                        return Lng;
//                    }
//
//                    public void setLng(double Lng) {
//                        this.Lng = Lng;
//                    }
//
//                    public double getLat() {
//                        return Lat;
//                    }
//
//                    public void setLat(double Lat) {
//                        this.Lat = Lat;
//                    }
//
//                    public String getName() {
//                        return Name;
//                    }
//
//                    public void setName(String Name) {
//                        this.Name = Name;
//                    }
//
//                    public String getUpdateTime() {
//                        return UpdateTime;
//                    }
//
//                    public void setUpdateTime(String UpdateTime) {
//                        this.UpdateTime = UpdateTime;
//                    }
//                }
//            }
//        }

}
