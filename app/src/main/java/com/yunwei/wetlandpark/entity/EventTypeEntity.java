package com.yunwei.wetlandpark.entity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.entity
 * @Description:
 * @date 2016/8/19 14:57
 */
public class EventTypeEntity {

    private int Id;

    private String Name;

    private String Icon;

    private String Note;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
