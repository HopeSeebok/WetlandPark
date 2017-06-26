package com.yunwei.wetlandpark.ui.biz.interfac.task;

import com.yunwei.wetlandpark.entity.UserInfoEntity;

/**
 * Created by zls on 2016/9/22.
 *
 * @Description:
 */

public interface TaskChangeStatusRequestListener {
    void onTaskChangeStatusRequestStart();
    void onTaskChangeStatusRequestEnd();
    void onTaskChangeStatusRequestSuccess(UserInfoEntity entity);
    void onTaskChangeStatusRequestFailed(int code, String msg);
}
