package com.yunwei.wetlandpark.ui.view;

import com.yunwei.wetlandpark.entity.UserInfoEntity;

/**
 * @Package: com.yunwei.zaina.ui.view
 * @Description:
 * @author: Aaron
 * @date: 2016-06-08
 * @Time: 15:23
 * @version: V1.0
 */
public interface LoginView extends BaseView {

    void onLoginStart();
    void onLoginEnd();
    String getUserName();
    String getPassword();
    void onLoginSuccess(UserInfoEntity entity);
    void onLoginFailure(int code,String msg);
}
