package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.entity.UserInfoEntity;

/**
 * @Package: com.yunwei.zaina.ui.biz.account
 * @Description:
 * @author: Aaron
 * @date: 2016-06-08
 * @Time: 15:29
 * @version: V1.0
 */
public interface LoginListener {

    void onLoginStart();
    void onLoginEnd();
    void loginSuccess(UserInfoEntity entity);
    void loginFailed(int code,String msg);
}
