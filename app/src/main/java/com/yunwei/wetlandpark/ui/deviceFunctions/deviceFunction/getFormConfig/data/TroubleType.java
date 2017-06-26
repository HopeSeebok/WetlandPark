package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.getFormConfig.data;

/**
 * @author CBOK
 * @date 2016/12/9 10:09
 * @description:
 */

public class TroubleType {
    private int Id;
    private int UnitId;
    private String Icon;

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

    private String Name;
}
