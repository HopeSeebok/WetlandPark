package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.rFIDSearch;

import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.view.BaseView;

/**
 * @author CBOK
 * @date 2016/12/7 1:45
 * @description:
 */

public class RFIDSearchContract {

    public interface View extends BaseView {
        void showDeviceInfo(DeviceInfo deviceInfo);

        void showRFIDSearchFail();

        void showLoadingDialog();

        void dismissLoadingDialog();

        void showLinkDeviceDialog();

        String getRFID();
    }

    public interface Presenter {
        void searchByRFID();
    }
}
