package com.yunwei.wetlandpark.ui.view;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.view
 * @Description:
 * @date 2016/9/13 20:42
 */
public interface UpdateUserInfoView extends BaseView{

    void onUpdateStart();

    void onUpdateEnd();

    void onUpdateFailure(int code,String msg);

    void onUpdateSuccess();
}
