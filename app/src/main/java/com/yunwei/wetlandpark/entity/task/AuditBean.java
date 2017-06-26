package com.yunwei.wetlandpark.entity.task;

/**
 * Created by longma on 2016/7/11.
 */

public class AuditBean {
    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    private boolean IsAudit;
    /**
     * AuditorId : sample string 1
     * Name : sample string 2
     */

    private AuditorBean Auditor;
    private String AuditTime;

    public boolean isIsAudit() {
        return IsAudit;
    }

    public void setIsAudit(boolean IsAudit) {
        this.IsAudit = IsAudit;
    }

    public AuditorBean getAuditor() {
        return Auditor;
    }

    public void setAuditor(AuditorBean Auditor) {
        this.Auditor = Auditor;
    }

    public String getAuditTime() {
        return AuditTime;
    }

    public void setAuditTime(String AuditTime) {
        this.AuditTime = AuditTime;
    }

//    public static class AuditorBean extends DataSupport implements Parcelable {
//        @SerializedName("Id")
//        @Expose
//        private String AuditorId;
//        private String Name;
//
//        public String getAuditorId() {
//            return AuditorId;
//        }
//
//        public void setAuditorId(String Id) {
//            this.AuditorId = Id;
//        }
//
//        public String getName() {
//            return Name;
//        }
//
//        public void setName(String Name) {
//            this.Name = Name;
//        }
//
//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeString(this.AuditorId);
//            dest.writeString(this.Name);
//        }
//
//        public AuditorBean() {
//        }
//
//        protected AuditorBean(Parcel in) {
//            this.AuditorId = in.readString();
//            this.Name = in.readString();
//        }
//
//        public static final Creator<AuditorBean> CREATOR = new Creator<AuditorBean>() {
//            public AuditorBean createFromParcel(Parcel source) {
//                return new AuditorBean(source);
//            }
//
//            public AuditorBean[] newArray(int size) {
//                return new AuditorBean[size];
//            }
//        };
//    }

    private ItemsBean itemsBean;

    public ItemsBean getItemsBean() {
        return itemsBean;
    }

    public void setItemsBean(ItemsBean itemsBean) {
        this.itemsBean = itemsBean;
    }

    private String TaskId;

    public String getTaskId() {
        return TaskId;
    }
    public void setTaskId(String taskId) {
        TaskId = taskId;
    }
}

