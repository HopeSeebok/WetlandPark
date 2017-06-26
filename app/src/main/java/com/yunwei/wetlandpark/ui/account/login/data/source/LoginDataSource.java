package com.yunwei.wetlandpark.ui.account.login.data.source;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account.data.source
 * @Description:登录
 * @date 2016/11/15 11:25
 */

public interface LoginDataSource {

    interface LoginCallBack{
        void onLoginStart();
        void onLoginSuccess(Object o);
        void onLoginFailed(String msg);
        void onLoginEnd();
    }

    void login(String[] loginInfo, LoginCallBack LoginCallBack);
}
