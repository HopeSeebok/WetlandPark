package com.yunwei.wetlandpark.ui.biz.interfac.task;

import com.yunwei.wetlandpark.entity.UserInfoEntity;

/**
 * Created by zls on 2016/9/22.
 *
 * @Description:
 */

public interface TaskDetailRequestListener {
    void onTaskDetailRequestStart();
    void onTaskDetailRequestEnd();
    void onTaskDetailRequestSuccess(UserInfoEntity entity);
    void onTaskDetailRequestFailed(int code, String msg);
}
