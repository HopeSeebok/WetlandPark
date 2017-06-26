package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.showSearchedList;

import com.yunwei.wetlandpark.ui.deviceFunctions.BaseViewPro;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble.data.TroubleInfo;
import com.yunwei.wetlandpark.ui.view.BaseView;

/**
 * @author CBOK
 * @date 2016/11/9 14:39
 * @description:
 */

public interface ShowSearchedListContract {

    interface View extends BaseViewPro<Presenter> {
        void showDeviceInfo(DeviceInfo deviceInfo);

        void showTroubleInfo(TroubleInfo troubleInfo);

        void showIDSearchFailedMsg();

        void showLoadingDialog();

        void dismissLoadingDialog();

        String getID();

        int getLayerID();
    }

    interface Presenter {
        void searchByID();
    }

    /**
     * 查询设施By RFID
     */
    interface RFIDSearchView extends BaseView {
        void showDeviceInfo(DeviceInfo deviceInfo);

        void showRFIDSearchFailur(String error);

        void showLoadingDialog();

        void dismissLoadingDialog();

        void onRFIDNotRelated(String rfid);

        String getRFID();
    }

    interface RFIDSearchPresenter {
        void searchByRFID();
    }
}
