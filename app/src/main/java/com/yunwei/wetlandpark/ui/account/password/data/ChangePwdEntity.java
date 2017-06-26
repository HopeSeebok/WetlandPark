package com.yunwei.wetlandpark.ui.account.password.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.entity
 * @Description: 修改密码实体
 * @date 2016/10/31 15:38
 */

public class ChangePwdEntity {

    @SerializedName("OldPassword")
    private String oldPassword;

    @SerializedName("NewPassword")
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
