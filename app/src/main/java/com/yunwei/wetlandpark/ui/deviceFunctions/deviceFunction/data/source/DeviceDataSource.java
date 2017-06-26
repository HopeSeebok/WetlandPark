package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.data.source;

import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.data.SignInfo;

/**
 * @author CBOK
 * @date 2016/12/16 11:56
 * @description:
 */

public interface DeviceDataSource {

    interface UploadCallBack<T>{
        void onUploadSuccess();

        void onUploadFail();

        T getUploadData();
    }

    void signIn(UploadCallBack<SignInfo> uploadCallBack);
}
