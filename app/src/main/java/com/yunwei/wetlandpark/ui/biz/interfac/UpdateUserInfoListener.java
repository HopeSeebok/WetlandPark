package com.yunwei.wetlandpark.ui.biz.interfac;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/13 20:44
 */
public interface UpdateUserInfoListener {

    void onUpdateStart();

    void onUpdateEnd();

    void onUpdateFailure(int code,String msg);

    void onUpdateSuccess();
}
