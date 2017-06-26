package com.yunwei.wetlandpark.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.entities
 * @Description:公司列表实体
 * @date 2016/11/1 19:21
 */

public class UnitInfoEntity extends BaseEntity implements Serializable {

    @SerializedName("data")
    private List<UnitEntity> entities;


    public List<UnitEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<UnitEntity> entities) {
        this.entities = entities;
    }

    public class UnitEntity implements Serializable {
        private int Id;
        private String Icon;
        private String Name;
        private double Lat;
        private double Lng;
        private String Addr;
        private Ext Ext;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String icon) {
            Icon = icon;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public double getLat() {
            return Lat;
        }

        public void setLat(double lat) {
            Lat = lat;
        }

        public double getLng() {
            return Lng;
        }

        public void setLng(double lng) {
            Lng = lng;
        }

        public String getAddr() {
            return Addr;
        }

        public void setAddr(String addr) {
            Addr = addr;
        }

        public UnitEntity.Ext getExt() {
            return Ext;
        }

        public void setExt(UnitEntity.Ext ext) {
            Ext = ext;
        }

        public class Ext {

            private int MapVer;

            private String MapUrl;

            private List<String> Map ;

            public void setMapVer(int MapVer) {
                this.MapVer = MapVer;
            }

            public int getMapVer() {
                return this.MapVer;
            }

            public void setMapUrl(String MapUrl) {
                this.MapUrl = MapUrl;
            }

            public String getMapUrl() {
                return this.MapUrl;
            }


            public List<String> getMap() {
                return Map;
            }

            public void setMap(List<String> map) {
                Map = map;
            }
        }
    }
}
