package com.yunwei.wetlandpark.ui.deviceFunctions.troubleFunction.makeTrouble;

import com.yunwei.wetlandpark.greedao.TroubleTable;
        import com.yunwei.wetlandpark.ui.deviceFunctions.BaseViewPro;

/**
 * @author CBOK
 * @date 2016/12/2 16:58
 * @description:
 */

public interface MakeTroubleContract {

    interface View extends BaseViewPro<Presenter> {
        void showUploadingDialog();
        void dismissUploadingDialog();
        void showUploadSuccessMsgAndFinishActivity();
        void showUploadFailedMsg();
        void showSaveSuccessMsg(String msg);
        void showSaveFailedMsg();
        TroubleTable getTroubleData();
    }

    interface Presenter{
        void save();
        void submit();
    }
}
