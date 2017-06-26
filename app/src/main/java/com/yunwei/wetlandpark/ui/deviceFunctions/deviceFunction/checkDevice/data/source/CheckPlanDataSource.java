package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.data.source;

import com.yunwei.wetlandpark.greedao.CheckPlanTable;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice.data.CheckPlanEntity;

/**
 * @author CBOK
 * @date 2016/11/10 17:28
 * @description:
 */

public interface CheckPlanDataSource {

    interface UploadCallBack {
        void onUploadSuccess();
        void onUploadFailed(String msg);
        CheckPlanEntity getUploadEntity();
        CheckPlanTable getSaveEntity();
    }

    void upload(UploadCallBack uploadCallBack);
}
