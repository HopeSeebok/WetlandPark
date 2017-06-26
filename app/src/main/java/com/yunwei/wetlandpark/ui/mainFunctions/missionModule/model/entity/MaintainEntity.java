package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model.entity;

import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions.missionModule.model.entity
 * @Description:
 * @date 2016/11/30
 * @changeby:
 */

public class MaintainEntity {


    /**
     * success : true
     * message : 操作成功
     * data : {"Items":[{"MissionId":0,"Id":"d6bd821caf3745e2a83e428df49bd49c","TargetKind":2,"TargetId":"58b0a84f5d194159bcc8fa2be6fd40d1","TargetCode":"321","TargetType":null,"TargetLat":139246.194061,"TargetLng":161307.757698,"TargetAddr":"","User":"超级管理员","HandlerName":"28220aadd5184cf6973c7e924a58d5a5","Imgs":null}],"MissionType":0,"Id":1100,"MissionStatus":1,"MissionLevel":2,"MissionFromType":2,"MissionMold":0,"Note":"233222","Remark":"233222","StartAt":"0001-01-01T00:00:00","EndAt":"0001-01-01T00:00:00","Expires":0,"User":"管理员444","HandlerName":"超级管理员","HandlerId":"28220aadd5184cf6973c7e924a58d5a5","CreateTime":"2016-11-30T16:12:34.895525","UpdateTime":"2016-11-30T16:12:35.240119"}
     */

    private boolean success;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Items : [{"MissionId":0,"Id":"d6bd821caf3745e2a83e428df49bd49c","TargetKind":2,"TargetId":"58b0a84f5d194159bcc8fa2be6fd40d1","TargetCode":"321","TargetType":null,"TargetLat":139246.194061,"TargetLng":161307.757698,"TargetAddr":"","User":"超级管理员","HandlerName":"28220aadd5184cf6973c7e924a58d5a5","Imgs":null}]
         * MissionType : 0
         * Id : 1100
         * MissionStatus : 1
         * MissionLevel : 2
         * MissionFromType : 2
         * MissionMold : 0
         * Note : 233222
         * Remark : 233222
         * StartAt : 0001-01-01T00:00:00
         * EndAt : 0001-01-01T00:00:00
         * Expires : 0
         * User : 管理员444
         * HandlerName : 超级管理员
         * HandlerId : 28220aadd5184cf6973c7e924a58d5a5
         * CreateTime : 2016-11-30T16:12:34.895525
         * UpdateTime : 2016-11-30T16:12:35.240119
         */

        private int MissionType;
        private int Id;
        private int MissionStatus;
        private int MissionLevel;
        private int MissionFromType;
        private int MissionMold;
        private String Note;
        private String Remark;
        private String StartAt;
        private String EndAt;
        private int Expires;
        private String User;
        private String HandlerName;
        private String HandlerId;
        private String CreateTime;
        private String UpdateTime;
        private List<ItemsBean> Items;

        public int getMissionType() {
            return MissionType;
        }

        public void setMissionType(int MissionType) {
            this.MissionType = MissionType;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getMissionStatus() {
            return MissionStatus;
        }

        public void setMissionStatus(int MissionStatus) {
            this.MissionStatus = MissionStatus;
        }

        public int getMissionLevel() {
            return MissionLevel;
        }

        public void setMissionLevel(int MissionLevel) {
            this.MissionLevel = MissionLevel;
        }

        public int getMissionFromType() {
            return MissionFromType;
        }

        public void setMissionFromType(int MissionFromType) {
            this.MissionFromType = MissionFromType;
        }

        public int getMissionMold() {
            return MissionMold;
        }

        public void setMissionMold(int MissionMold) {
            this.MissionMold = MissionMold;
        }

        public String getNote() {
            return Note;
        }

        public void setNote(String Note) {
            this.Note = Note;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
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

        public int getExpires() {
            return Expires;
        }

        public void setExpires(int Expires) {
            this.Expires = Expires;
        }

        public String getUser() {
            return User;
        }

        public void setUser(String User) {
            this.User = User;
        }

        public String getHandlerName() {
            return HandlerName;
        }

        public void setHandlerName(String HandlerName) {
            this.HandlerName = HandlerName;
        }

        public String getHandlerId() {
            return HandlerId;
        }

        public void setHandlerId(String HandlerId) {
            this.HandlerId = HandlerId;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public List<ItemsBean> getItems() {
            return Items;
        }

        public void setItems(List<ItemsBean> Items) {
            this.Items = Items;
        }

        public static class ItemsBean {
            /**
             * MissionId : 0
             * Id : d6bd821caf3745e2a83e428df49bd49c
             * TargetKind : 2
             * TargetId : 58b0a84f5d194159bcc8fa2be6fd40d1
             * TargetCode : 321
             * TargetType : null
             * TargetLat : 139246.194061
             * TargetLng : 161307.757698
             * TargetAddr :
             * User : 超级管理员
             * HandlerName : 28220aadd5184cf6973c7e924a58d5a5
             * Imgs : null
             */

            private int MissionId;
            private String Id;
            private int TargetKind;
            private String TargetId;
            private String TargetCode;
            private String TargetType;
            private double TargetLat;
            private double TargetLng;
            private String TargetAddr;
            private String User;
            private String HandlerName;
            private List<ImgsBean> Imgs;

            public int getMissionId() {
                return MissionId;
            }

            public void setMissionId(int MissionId) {
                this.MissionId = MissionId;
            }

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public int getTargetKind() {
                return TargetKind;
            }

            public void setTargetKind(int TargetKind) {
                this.TargetKind = TargetKind;
            }

            public String getTargetId() {
                return TargetId;
            }

            public void setTargetId(String TargetId) {
                this.TargetId = TargetId;
            }

            public String getTargetCode() {
                return TargetCode;
            }

            public void setTargetCode(String TargetCode) {
                this.TargetCode = TargetCode;
            }

            public String getTargetType() {
                return TargetType;
            }

            public void setTargetType(String TargetType) {
                this.TargetType = TargetType;
            }

            public double getTargetLat() {
                return TargetLat;
            }

            public void setTargetLat(double TargetLat) {
                this.TargetLat = TargetLat;
            }

            public double getTargetLng() {
                return TargetLng;
            }

            public void setTargetLng(double TargetLng) {
                this.TargetLng = TargetLng;
            }

            public String getTargetAddr() {
                return TargetAddr;
            }

            public void setTargetAddr(String TargetAddr) {
                this.TargetAddr = TargetAddr;
            }

            public String getUser() {
                return User;
            }

            public void setUser(String User) {
                this.User = User;
            }

            public String getHandlerName() {
                return HandlerName;
            }

            public void setHandlerName(String HandlerName) {
                this.HandlerName = HandlerName;
            }

            public List<ImgsBean> getImgs() {
                return Imgs;
            }

            public void setImgs(List<ImgsBean> Imgs) {
                this.Imgs = Imgs;
            }

            public static class ImgsBean {
              String url;

                public String getUrl() {
                    return url;
                }

                public ImgsBean setUrl(String url) {
                    this.url = url;
                    return this;
                }
            }

        }
    }
}
