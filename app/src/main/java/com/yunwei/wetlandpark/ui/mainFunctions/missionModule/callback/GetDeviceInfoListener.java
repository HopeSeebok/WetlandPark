package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.callback;

import com.yunwei.wetlandpark.greedao.Facility;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac.task
 * @Description:
 * @date 2016/9/27
 * @changeby:
 */

public interface GetDeviceInfoListener {
    void success(Facility facility);
    void failure();
}
