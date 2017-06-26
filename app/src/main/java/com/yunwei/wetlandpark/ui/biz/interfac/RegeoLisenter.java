package com.yunwei.wetlandpark.ui.biz.interfac;

import com.yunwei.wetlandpark.entity.RegeoEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac
 * @Description:
 * @date 2016/9/19 11:25
 */
public interface RegeoLisenter {

    void onRegeoStart();

    void onRegeoEnd();

    void onRegeoFailure(int code,String error);

    void onRegeoSuccess(RegeoEntity entity);
}
