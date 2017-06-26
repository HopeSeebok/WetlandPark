package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.shootTrouble;

import com.yunwei.wetlandpark.greedao.TroubleShooterTable;
import com.yunwei.wetlandpark.ui.deviceFunctions.BaseViewPro;

/**
 * @author CBOK
 * @date 2016/12/6 17:25
 * @description:
 */

public interface ShootTroubleContract {
    interface View extends BaseViewPro<Presenter> {
        void showUploadingDialog();
        void dismissUploadingDialog();
        void showUploadSuccessMsgAndFinishActivity();
        void showUploadFailedMsg();
        void showSaveSuccessMsg(String msg);
        void showSaveFailedMsg();
        TroubleShooterTable getUIEntity();
    }

    interface Presenter{
        void save();
        void submit();
    }
}
