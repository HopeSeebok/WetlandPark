package com.yunwei.wetlandpark.entity.checkingRecord;

import com.yunwei.wetlandpark.entity.Geometry;
import com.yunwei.wetlandpark.entity.Images;

import java.util.List;

/**
 * Created by SEEBOK on 2016/9/12.
 */
public class ArcgisYhdModel {

    private Attributes Attributes;

    private Geometry Geometry;

    private List<Attachs> Attachs ;

    private List<Images> Images ;

    public void setAttributes(Attributes Attributes){
        this.Attributes = Attributes;
    }
    public Attributes getAttributes(){
        return this.Attributes;
    }
    public void setGeometry(Geometry Geometry){
        this.Geometry = Geometry;
    }
    public Geometry getGeometry(){
        return this.Geometry;
    }
    public void setAttachs(List<Attachs> Attachs){
        this.Attachs = Attachs;
    }
    public List<Attachs> getAttachs(){
        return this.Attachs;
    }
    public void setImages(List<Images> Images){
        this.Images = Images;
    }
    public List<Images> getImages(){
        return this.Images;
    }


    public static class Attachs {
        private String Uri;

        private int AttachType;

        public void setUri(String Uri){
            this.Uri = Uri;
        }
        public String getUri(){
            return this.Uri;
        }
        public void setAttachType(int AttachType){
            this.AttachType = AttachType;
        }
        public int getAttachType(){
            return this.AttachType;
        }

    }

    public static class Attributes {
        private String guid;

        private int objectid;

        private double x;

        private double y;

        private double z;

        private int userid;

        private int unitid;

        private String createtime;

        private int pointid;

        private String code;

        private String hdtype;

        private String hdplace;

        private String hddesc;

        private String hdstate;

        private String usrename;

        private String facilitycode;

        private String facilitypointid;

        private String facilitytype;

        private String usertel;

        private String CreateTimeSTR;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public int getObjectid() {
            return objectid;
        }

        public void setObjectid(int objectid) {
            this.objectid = objectid;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getZ() {
            return z;
        }

        public void setZ(double z) {
            this.z = z;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getUnitid() {
            return unitid;
        }

        public void setUnitid(int unitid) {
            this.unitid = unitid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getPointid() {
            return pointid;
        }

        public void setPointid(int pointid) {
            this.pointid = pointid;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getHdtype() {
            return hdtype;
        }

        public void setHdtype(String hdtype) {
            this.hdtype = hdtype;
        }

        public String getHdplace() {
            return hdplace;
        }

        public void setHdplace(String hdplace) {
            this.hdplace = hdplace;
        }

        public String getHddesc() {
            return hddesc;
        }

        public void setHddesc(String hddesc) {
            this.hddesc = hddesc;
        }

        public String getHdstate() {
            return hdstate;
        }

        public void setHdstate(String hdstate) {
            this.hdstate = hdstate;
        }

        public String getUsrename() {
            return usrename;
        }

        public void setUsrename(String usrename) {
            this.usrename = usrename;
        }

        public String getFacilitycode() {
            return facilitycode;
        }

        public void setFacilitycode(String facilitycode) {
            this.facilitycode = facilitycode;
        }

        public String getFacilitypointid() {
            return facilitypointid;
        }

        public void setFacilitypointid(String facilitypointid) {
            this.facilitypointid = facilitypointid;
        }

        public String getFacilitytype() {
            return facilitytype;
        }

        public void setFacilitytype(String facilitytype) {
            this.facilitytype = facilitytype;
        }

        public String getUsertel() {
            return usertel;
        }

        public void setUsertel(String usertel) {
            this.usertel = usertel;
        }

        public String getCreateTimeSTR() {
            return CreateTimeSTR;
        }

        public void setCreateTimeSTR(String createTimeSTR) {
            CreateTimeSTR = createTimeSTR;
        }
    }

}
