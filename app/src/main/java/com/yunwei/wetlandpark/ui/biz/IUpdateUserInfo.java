package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.UpdateUserInfoListener;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz
 * @Description:
 * @date 2016/9/13 20:44
 */
public interface IUpdateUserInfo {

    void updateUserInfo(Activity activity, Object o, UpdateUserInfoListener listener);
    void updateUserPsd(Activity activity, Object o, UpdateUserInfoListener listener);
}
