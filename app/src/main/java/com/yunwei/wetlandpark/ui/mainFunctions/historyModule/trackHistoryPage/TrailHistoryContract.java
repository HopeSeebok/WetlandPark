package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.trackHistoryPage;

import com.yunwei.wetlandpark.ui.view.BaseView;

/**
 * @author CBOK
 * @date 2016/12/21 16:11
 * @description:
 */

public interface TrailHistoryContract {

    interface View extends BaseView{
        void showLoadingDialog();

        void dismissLoadingDialog();

        void showTrailResult(String cacheID);

        void showFailMessage();
    }

    interface Presenter{
        void searchTrail(long startTime, long endTime);
    }
}
