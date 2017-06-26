package com.yunwei.wetlandpark.entity;

import java.io.Serializable;

/**
 * @package com.yunwei.camera.entity
 * @description 签到签退提交实体
 * @author yangdu
 * @date 16/10/14
 * @time 下午4:41
 * @version V1.0
 **/
public class UserSignEntity implements Serializable{


    /**
     * Id : sample string 1
     * IsSign : true
     * personnels : sample string 3
     * personnelids : sample string 4
     */
    private String Id;//
    private boolean IsSign;//是否已经签到（必填）
    private String personnels;//签到人员名称（签到时必填）
    private String personnelids;//签到人员ID（签到时必填）

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public boolean isIsSign() {
        return IsSign;
    }

    public void setIsSign(boolean IsSign) {
        this.IsSign = IsSign;
    }

    public String getPersonnels() {
        return personnels;
    }

    public void setPersonnels(String personnels) {
        this.personnels = personnels;
    }

    public String getPersonnelids() {
        return personnelids;
    }

    public void setPersonnelids(String personnelids) {
        this.personnelids = personnelids;
    }
}
