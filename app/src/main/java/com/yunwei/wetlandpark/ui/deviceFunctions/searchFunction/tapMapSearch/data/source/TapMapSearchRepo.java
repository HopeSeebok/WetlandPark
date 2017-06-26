package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.data.source;

import android.app.Activity;

import com.esri.core.tasks.identify.IdentifyResult;
import com.esri.core.tasks.identify.IdentifyTask;
import com.yunwei.wetlandpark.BuildConfig;

/**
 * @author CBOK
 * @date 2016/12/5 22:51
 * @description:
 */

public class TapMapSearchRepo implements TapMapSearchDataSource{
    private TapMapSearchRepo() {
    }

    private static TapMapSearchRepo INSTANCE;

    public static TapMapSearchRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TapMapSearchRepo();
        }
        return INSTANCE;
    }
    @Override
    public void tapMapSearch(final Activity activity, final TapMapSearchDeviceCallBack callBack) {
        final String MAP_LAYER_SEARCH_URL = BuildConfig.MAP_LAYER_SEARCH_URL;
        final IdentifyTask identifyTask = new IdentifyTask(MAP_LAYER_SEARCH_URL);
        new Thread() {
            @Override
            public void run() {
                try {
                    final IdentifyResult[] results = identifyTask.execute(callBack.getTapSearchParameters());
                    if (results == null || results.length <= 0) {
                        return;
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSearchDataLoaded(results);
                        }
                    });
                } catch (Exception e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSearchDataNotAvailable();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
