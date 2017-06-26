package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.data.source;

import android.app.Activity;

import com.esri.core.tasks.identify.IdentifyParameters;
import com.esri.core.tasks.identify.IdentifyResult;

/**
 * @author CBOK
 * @date 2016/12/5 22:51
 * @description:
 */

public interface TapMapSearchDataSource {

    interface TapMapSearchDeviceCallBack {
        void onSearchDataLoaded(IdentifyResult[] results);

        void onSearchDataNotAvailable();

        IdentifyParameters getTapSearchParameters();
    }

    void tapMapSearch(Activity activity, TapMapSearchDeviceCallBack callBack);
}
