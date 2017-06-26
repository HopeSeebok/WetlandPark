package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.disposableLinkDevice;

import com.yunwei.wetlandpark.ui.deviceFunctions.BaseViewPro;
import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;

/**
 * @author CBOK
 * @date 2016/12/7 0:06
 * @description:
 */

public interface DisposableLinkContract {
    interface View extends BaseViewPro<Presenter> {
        void showUploadingDialog();
        void dismissUploadingDialog();
        void showUploadSuccessMsgAndFinishActivity();
        void showUploadFailedMsg();
        DeviceInfo getViewEntity();
    }

    interface Presenter{
        void submit();
    }
}
