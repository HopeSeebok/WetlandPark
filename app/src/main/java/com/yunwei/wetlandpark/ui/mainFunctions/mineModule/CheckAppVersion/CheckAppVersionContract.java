package com.yunwei.wetlandpark.ui.mainFunctions.mineModule.CheckAppVersion;

import com.yunwei.wetlandpark.ui.view.BaseView;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions.mineModule
 * @Description:
 * @date 2016/11/17 11:09
 */

public interface CheckAppVersionContract {

    interface View extends BaseView{
        void showRequestFail();

        void showIsLatestVersion();

        void showCheckingDialog();

        void dismissCheckingDialog();
    }

    interface Presenter{
        void checkAppVersion();
    }
}
