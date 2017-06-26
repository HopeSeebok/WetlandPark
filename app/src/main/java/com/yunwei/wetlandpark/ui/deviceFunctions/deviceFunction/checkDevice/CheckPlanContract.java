package com.yunwei.wetlandpark.ui.deviceFunctions.deviceFunction.checkDevice;

import com.yunwei.wetlandpark.greedao.CheckPlanTable;
import com.yunwei.wetlandpark.greedao.PlanToInspectEntity;
import com.yunwei.wetlandpark.ui.deviceFunctions.BaseViewPro;

/**
 * @author CBOK
 * @date 2016/11/10 16:47
 * @description:
 */

public interface CheckPlanContract {
    interface View extends BaseViewPro<Presenter> {
        void showNoPlanMsg();
        void showQueryDialog();
        void dismissQueryDialog();
        void showUploadingDialog();
        void dismissUploadingDialog();
        void showUploadSuccessMsgAndFinishActivity();
        void showUploadFailedMsg(String msg);
        CheckPlanTable getPlanData();
        void showScannedPoint(int index);
        void showScannedFailMsg(String msg);
    }

    interface Presenter {
        void upload();
        void searchScannedRFID(String tid);
        int getCheckPointCount();
        PlanToInspectEntity getCheckPlan();
    }
}
