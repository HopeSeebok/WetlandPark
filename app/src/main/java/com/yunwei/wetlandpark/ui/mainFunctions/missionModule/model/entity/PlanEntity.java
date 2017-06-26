package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.entity;

import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions.missionModule.model.entity
 * @Description:
 * @date 2016/12/1
 * @changeby:
 */

public class PlanEntity {


    /**
     * success : false
     * message : 操作成功
     * data : [{"Id":"4b09c35c22e04338b8fbf602c8fc5b68","PlanType":1,"No":"ff","Name":"ff","StartAt":"2016-12-05T00:00:00","EndAt":"2016-12-09T00:00:00","Scheduler":[0,120,360,600,720,840,960,1080,1200,1320],"PersonName":null,"InspectionPointNum":0,"Note":"","Devices":[{"Id":"b5fe8130a78a4c7b99cb62f903c1f117","Code":"D1","RFID":"654321","Lat":140889.590163,"Lng":155842.510196}]},{"Id":"57a25fc7dca0466a8b4e5383b338adc1","PlanType":1,"No":"D1","Name":"D1","StartAt":"2016-12-01T00:00:00","EndAt":"2016-12-27T00:00:00","Scheduler":[0,120,360,600,720,840,960,1080,1200,1320],"PersonName":null,"InspectionPointNum":0,"Note":"","Devices":[{"Id":"b5fe8130a78a4c7b99cb62f903c1f117","Code":"D1","RFID":"654321","Lat":140889.590163,"Lng":155842.510196},{"Id":"6c05dba357af4f9eb47b01c41dfe8469","Code":"D4","RFID":"xxxxxx","Lat":133411.705389,"Lng":176549.552705}]},{"Id":"09970e9d4ce24f7ebad0335af47ffa3d","PlanType":1,"No":"3","Name":"3","StartAt":"2016-12-05T00:00:00","EndAt":"2016-12-06T00:00:00","Scheduler":[0,120,360,600,720,840,960,1080,1200,1320],"PersonName":null,"InspectionPointNum":0,"Note":"","Devices":[{"Id":"6c05dba357af4f9eb47b01c41dfe8469","Code":"D4","RFID":"xxxxxx","Lat":133411.705389,"Lng":176549.552705}]}]
     */

    private boolean success;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Id : 4b09c35c22e04338b8fbf602c8fc5b68
         * PlanType : 1
         * No : ff
         * Name : ff
         * StartAt : 2016-12-05T00:00:00
         * EndAt : 2016-12-09T00:00:00
         * Scheduler : [0,120,360,600,720,840,960,1080,1200,1320]
         * PersonName : null
         * InspectionPointNum : 0
         * Note :
         * Devices : [{"Id":"b5fe8130a78a4c7b99cb62f903c1f117","Code":"D1","RFID":"654321","Lat":140889.590163,"Lng":155842.510196}]
         */

        private String Id;
        private int PlanType;
        private String No;
        private String Name;
        private String StartAt;
        private String EndAt;
        private String PersonName;
        private int InspectionPointNum;
        private String Note;
        private List<Integer> Scheduler;
        private List<DevicesBean> Devices;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public int getPlanType() {
            return PlanType;
        }

        public void setPlanType(int PlanType) {
            this.PlanType = PlanType;
        }

        public String getNo() {
            return No;
        }

        public void setNo(String No) {
            this.No = No;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getStartAt() {
            return StartAt;
        }

        public void setStartAt(String StartAt) {
            this.StartAt = StartAt;
        }

        public String getEndAt() {
            return EndAt;
        }

        public void setEndAt(String EndAt) {
            this.EndAt = EndAt;
        }

        public String getPersonName() {
            return PersonName;
        }

        public void setPersonName(String PersonName) {
            this.PersonName = PersonName;
        }

        public int getInspectionPointNum() {
            return InspectionPointNum;
        }

        public void setInspectionPointNum(int InspectionPointNum) {
            this.InspectionPointNum = InspectionPointNum;
        }

        public String getNote() {
            return Note;
        }

        public void setNote(String Note) {
            this.Note = Note;
        }

        public List<Integer> getScheduler() {
            return Scheduler;
        }

        public void setScheduler(List<Integer> Scheduler) {
            this.Scheduler = Scheduler;
        }

        public List<DevicesBean> getDevices() {
            return Devices;
        }

        public void setDevices(List<DevicesBean> Devices) {
            this.Devices = Devices;
        }

        public static class DevicesBean {
            /**
             * Id : b5fe8130a78a4c7b99cb62f903c1f117
             * Code : D1
             * RFID : 654321
             * Lat : 140889.590163
             * Lng : 155842.510196
             */

            private String Id;
            private String Code;
            private String RFID;
            private double Lat;
            private double Lng;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public String getRFID() {
                return RFID;
            }

            public void setRFID(String RFID) {
                this.RFID = RFID;
            }

            public double getLat() {
                return Lat;
            }

            public void setLat(double Lat) {
                this.Lat = Lat;
            }

            public double getLng() {
                return Lng;
            }

            public void setLng(double Lng) {
                this.Lng = Lng;
            }
        }
    }
}
