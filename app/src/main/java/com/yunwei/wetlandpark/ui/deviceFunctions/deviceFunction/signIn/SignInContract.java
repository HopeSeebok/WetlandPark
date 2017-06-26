package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.signIn;

import com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.addDevice.data.DeviceInfo;
import com.yunwei.wetlandpark.ui.view.BaseView;

/**
 * @author CBOK
 * @date 2016/12/16 13:56
 * @description:
 */

public interface SignInContract {
    interface View {
        void showLoadingDialog();

        void dismissLoadingDialog();

        void showSignInSuccess();

        void showSignInFail();

        DeviceInfo getDeviceInfo();
    }

    interface Presenter{
        void signIn();

    }
}
