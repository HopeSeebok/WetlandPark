package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList.data.source;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.TroubleInfo;

/**
 * @author CBOK
 * @date 2016/11/9 15:01
 * @description:
 */

public interface SearchDeviceDataSource {

    interface GuIDSearchDeviceCallBack {
        void onSearchDataLoaded(DeviceInfo deviceInfo);

        void onSearchDataNotAvailable();

        String getGuID();
    }

    interface TroubleSearchCallBack {
        void onSearchDataLoaded(TroubleInfo troubleInfo);

        void onSearchDataNotAvailable();

        String getID();
    }

    /**
     * RFID查询设施[hezhiWu]
     */
    interface RFIDSearchDeviceCallBack {
        void onSearchDataLoaded(DeviceInfo deviceInfo);

        void onSearchDataNotAvailable();

        void onRFIDNotRelated(String rfid);

        String getRFID();
    }

    void searchDeviceByGuID(Activity activity, GuIDSearchDeviceCallBack callBack);

    void searchTroubleByID(Activity activity, TroubleSearchCallBack callBack);

    void searchByRFID(Activity activity, RFIDSearchDeviceCallBack callBack);
}
