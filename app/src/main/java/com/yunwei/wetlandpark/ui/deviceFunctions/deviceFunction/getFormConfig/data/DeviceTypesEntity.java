package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/11/7 21:39
 * @description:
 */

public class DeviceTypesEntity {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item{
        private int Id;
        private int UnitId;
        private String Icon;
        private String Name;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getUnitId() {
            return UnitId;
        }

        public void setUnitId(int unitId) {
            UnitId = unitId;
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
    }
}
