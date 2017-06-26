package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.disposableLinkDevice.data.source;

import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/7 0:24
 * @description:
 */

public interface DisposableLinkDataSource {
    interface UploadCallBack{
        void onUploadSuccess();
        void onUploadFailed();
        DeviceInfo getUploadEntity(List<Image> path);
        List<String> getLocalImageList();
    }

    void upload(UploadCallBack uploadCallBack);
}
