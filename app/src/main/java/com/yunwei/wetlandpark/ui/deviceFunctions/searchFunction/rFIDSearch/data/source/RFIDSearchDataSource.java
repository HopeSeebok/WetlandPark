package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.rFIDSearch.data.source;

import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;

/**
 * @author CBOK
 * @date 2016/12/7 1:56
 * @description:
 */

public interface RFIDSearchDataSource {

    interface RFIDSearchCallBack {
        void onSearchDataLoaded(DeviceInfo deviceInfo);

        void onRFIDNotLinked();

        void onSearchDataNotAvailable();

        String getRFID();
    }

    void searchByRFID(RFIDSearchCallBack callBack);
}
