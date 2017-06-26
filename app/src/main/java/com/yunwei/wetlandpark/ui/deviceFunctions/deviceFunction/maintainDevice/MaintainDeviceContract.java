package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.maintainDevice;

import com.yunwei.wetlandpark.greedao.MaintainDeviceTable;
import com.yunwei.wetlandpark.ui.deviceFunctions.BaseViewPro;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.deviceFunctions.deviceFunction.maintainDevice
 * @Description:
 * @date 2016/11/17 14:54
 */

public interface MaintainDeviceContract {

    interface View extends BaseViewPro<Presenter>{
        void showUploadingDialog();
        void dismissUploadingDialog();
        void showUploadSuccessMsgAndFinishActivity();
        void showUploadFailedMsg(String msg);
        void showSaveSuccessMsg();
        void showSaveFailedMsg(String msg);
        MaintainDeviceTable getMaintainData();
    }

    interface Presenter{
        void save();
        void submit();
    }

}
