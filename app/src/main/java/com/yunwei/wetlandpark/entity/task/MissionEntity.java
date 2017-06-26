package com.yunwei.wetlandpark.entity.task;
import com.yunwei.wetlandpark.entity.BaseEntity;

import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.hydrant.entity.task
 * @Description:
 * @date 2016/11/2
 * @changeby:
 */

public class MissionEntity extends BaseEntity {


    /**
     * data : {"Id":132,"MissionType":1,"MissionStatus":0,"MissionLevel":0,"MissionMold":0,"Note":"测试推送","Remark":"自动生成:2016/11/17 15:39:46, Id:1","StartAt":"0001-01-01T00:00:00","EndAt":"0001-01-01T00:00:00","Expires":0,"UserName":null,"HandlerName":null,"HandlerId":null,"Items":[{"MissionId":0,"Id":"cfa56f0e844d4d54960d14d00bd0760a","TargetKind":1,"TargetId":"fb3ffe494c274536962b9df7a3849278","TargetCode":null,"TargetType":null,"TargetLat":139972.345827,"TargetLng":155804.291682,"TargetAddr":"","User":null,"HandlerName":null,"Imgs":null},{"MissionId":0,"Id":"0b5a3bdb6f704606a6df5f79be55ae71","TargetKind":1,"TargetId":"7aaeadfecfdd4538968891877a5959cf","TargetCode":null,"TargetType":null,"TargetLat":142284.565924,"TargetLng":159951.000451,"TargetAddr":"a1","User":null,"HandlerName":null,"Imgs":null}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Id : 132
         * MissionType : 1
         * MissionStatus : 0
         * MissionLevel : 0
         * MissionMold : 0
         * Note : 测试推送
         * Remark : 自动生成:2016/11/17 15:39:46, Id:1
         * StartAt : 0001-01-01T00:00:00
         * EndAt : 0001-01-01T00:00:00
         * Expires : 0
         * UserName : null
         * HandlerName : null
         * HandlerId : null
         * Items : [{"MissionId":0,"Id":"cfa56f0e844d4d54960d14d00bd0760a","TargetKind":1,"TargetId":"fb3ffe494c274536962b9df7a3849278","TargetCode":null,"TargetType":null,"TargetLat":139972.345827,"TargetLng":155804.291682,"TargetAddr":"","User":null,"HandlerName":null,"Imgs":null},{"MissionId":0,"Id":"0b5a3bdb6f704606a6df5f79be55ae71","TargetKind":1,"TargetId":"7aaeadfecfdd4538968891877a5959cf","TargetCode":null,"TargetType":null,"TargetLat":142284.565924,"TargetLng":159951.000451,"TargetAddr":"a1","User":null,"HandlerName":null,"Imgs":null}]
         */

        private int Id;
        private int MissionType;
        private int MissionStatus;
        private int MissionLevel;
        private int MissionMold;
        private String Note;
        private String Remark;
        private String StartAt;
        private String EndAt;
        private int Expires;
        private String UserName;
        private String HandlerName;
        private String HandlerId;
        private List<ItemsBean> Items;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getMissionType() {
            return MissionType;
        }

        public void setMissionType(int MissionType) {
            this.MissionType = MissionType;
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

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
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

        public List<ItemsBean> getItems() {
            return Items;
        }

        public void setItems(List<ItemsBean> Items) {
            this.Items = Items;
        }

        public static class ItemsBean {
            /**
             * MissionId : 0
             * Id : cfa56f0e844d4d54960d14d00bd0760a
             * TargetKind : 1
             * TargetId : fb3ffe494c274536962b9df7a3849278
             * TargetCode : null
             * TargetType : null
             * TargetLat : 139972.345827
             * TargetLng : 155804.291682
             * TargetAddr :
             * User : null
             * HandlerName : null
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

            public class ImgsBean {
                String Url;

                public String getUrl() {
                    return Url;
                }

                public ImgsBean setUrl(String url) {
                    Url = url;
                    return this;
                }
            }
        }
    }
}
