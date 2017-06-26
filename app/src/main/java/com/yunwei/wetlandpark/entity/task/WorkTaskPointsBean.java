package com.yunwei.wetlandpark.entity.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by longma on 2016/7/11.
 */

public class WorkTaskPointsBean  {
    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    /**
     * Lng : 1.1
     * Lat : 2.1
     * Name : sample string 3
     * UpdateTime : 2016-07-06 17:02:57
     "hdtype": "sample string 1",
     "hddesc": "sample string 2",
     "State": "sample string 4"
     "TaskPointId" string
     "IsUpload" 是否上传
     "IsComplete" 是否完成 0:未完成 1：完成
     */
    private String XhsDeviceType;
    private String hddesc;
    private String State;
    private String hdtype;
    @SerializedName("Id")
    @Expose
    private String DeviceId;

    private String TaskPointId;
    private String No;
    private String RFID;
    private String DeviceType;

    private LocationBean Location;

    int IsUpload;
    int IsComplete;
//    public static class LocationBean extends DataSupport{
//        private double Lng;
//        private double Lat;
//        private String Name;
//        private String UpdateTime;
//
//        public double getLng() {
//            return Lng;
//        }
//
//        public void setLng(double Lng) {
//            this.Lng = Lng;
//        }
//
//        public double getLat() {
//            return Lat;
//        }
//
//        public void setLat(double Lat) {
//            this.Lat = Lat;
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
//        public String getUpdateTime() {
//            return UpdateTime;
//        }
//
//        public void setUpdateTime(String UpdateTime) {
//            this.UpdateTime = UpdateTime;
//        }
//    }


    public int isUpload() {
        return IsUpload;
    }

    public void setUpload(int upload) {
        IsUpload = upload;
    }

    public int isComplete() {
        return IsComplete;
    }

    public void setComplete(int complete) {
        IsComplete = complete;
    }

    public String getTaskPointId() {
        return TaskPointId;
    }

    public void setTaskPointId(String taskPointId) {
        TaskPointId = taskPointId;
    }

    public String getHdtype() {
        return hdtype;
    }

    public void setHdtype(String hdtype) {
        this.hdtype = hdtype;
    }

    public String getHddesc() {
        return hddesc;
    }

    public void setHddesc(String hddesc) {
        this.hddesc = hddesc;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getXhsDeviceType() {
        return XhsDeviceType;
    }

    public void setXhsDeviceType(String XhsDeviceType) {
        this.XhsDeviceType = XhsDeviceType;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String Id) {
        this.DeviceId = Id;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String No) {
        this.No = No;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String DeviceType) {
        this.DeviceType = DeviceType;
    }

    public LocationBean getLocation() {
        return Location;
    }

    public void setLocation(LocationBean Location) {
        this.Location = Location;
    }


//    public static class DeviceBean extends DataSupport{
//        private int XhsDeviceType;
//        @SerializedName("Id")
//        @Expose
//        private String DeviceId;
//
//        private String No;
//        private String RFID;
//        private int DeviceType;
//        /**
//         * Lng : 1.1
//         * Lat : 2.1
//         * Name : sample string 3
//         * UpdateTime : 2016-07-06 17:02:57
//         */
//
//        private LocationBean Location;
//
//        public static class LocationBean extends DataSupport{
//            private double Lng;
//            private double Lat;
//            private String Name;
//            private String UpdateTime;
//
//            public double getLng() {
//                return Lng;
//            }
//
//            public void setLng(double Lng) {
//                this.Lng = Lng;
//            }
//
//            public double getLat() {
//                return Lat;
//            }
//
//            public void setLat(double Lat) {
//                this.Lat = Lat;
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
//            public String getUpdateTime() {
//                return UpdateTime;
//            }
//
//            public void setUpdateTime(String UpdateTime) {
//                this.UpdateTime = UpdateTime;
//            }
//        }
//
//        public int getXhsDeviceType() {
//            return XhsDeviceType;
//        }
//
//        public void setXhsDeviceType(int XhsDeviceType) {
//            this.XhsDeviceType = XhsDeviceType;
//        }
//
//        public String getDeviceId() {
//            return DeviceId;
//        }
//
//        public void setDeviceId(String Id) {
//            this.DeviceId = Id;
//        }
//
//        public String getNo() {
//            return No;
//        }
//
//        public void setNo(String No) {
//            this.No = No;
//        }
//
//        public String getRFID() {
//            return RFID;
//        }
//
//        public void setRFID(String RFID) {
//            this.RFID = RFID;
//        }
//
//        public int getDeviceType() {
//            return DeviceType;
//        }
//
//        public void setDeviceType(int DeviceType) {
//            this.DeviceType = DeviceType;
//        }
//
//        public LocationBean getLocation() {
//            return Location;
//        }
//
//        public void setLocation(LocationBean Location) {
//            this.Location = Location;
//        }
//
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

