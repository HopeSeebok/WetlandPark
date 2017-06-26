package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice.data.source;

import com.yunwei.wetlandpark.greedao.MaintainDeviceTable;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice.data.MaintainDeviceEntity;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.deviceFunctions.deviceFunction.maintainDevice.data.source
 * @Description:
 * @date 2016/11/17 15:03
 */

public interface MaintainDeviceSource {

    interface UploadCallBack{
        void onUploadSuccess();
        void onUploadFailed();
    }

    interface SaveCallBack{
        void onUpdateSuccess();
        void onSaveSuccess(long id);
        void onSaveFailed();
    }

    void upload(MaintainDeviceEntity entity, UploadCallBack callBack);

    void save(MaintainDeviceTable entity, SaveCallBack callBack);

}
