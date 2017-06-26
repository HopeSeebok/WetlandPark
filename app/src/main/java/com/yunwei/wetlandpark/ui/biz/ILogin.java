package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.LoginListener;

/**
 * @Package: com.yunwei.zaina.ui.biz.account
 * @Description:
 * @author: Aaron
 * @date: 2016-06-08
 * @Time: 15:32
 * @version: V1.0
 */
public interface ILogin {
    public void login(Activity activity,String account, String pwd, LoginListener listener);
}
