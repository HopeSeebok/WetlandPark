package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data.source;

import com.yunwei.baidu.HistoryTrackData;
import com.yunwei.wetlandpark.greedao.TrackPointsTable;

/**
 * @author CBOK
 * @date 2016/12/21 16:26
 * @description:
 */

public interface HistoryDataSource {

    interface GetDataCallBack<T>{
        void onDataLoaded(T data);

        void onDataNotAvailable();
    }

    interface QueryLocalCallBack<T>{
        void onGotData(T data);

        void onDataEmpty();
    }

    void queryBaiduService(int startTime, int endTime, GetDataCallBack<HistoryTrackData> getDataCallBack);

    void queryTablePoints(String cacheID, QueryLocalCallBack<TrackPointsTable> queryLocalCallBack);

    void saveTrackPoints(TrackPointsTable trackPointsTable);

}
