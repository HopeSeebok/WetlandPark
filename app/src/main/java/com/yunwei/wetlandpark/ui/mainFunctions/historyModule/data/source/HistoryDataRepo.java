package com.yunwei.wetlandpark.ui.mainFunctions.historyModule.data.source;

import com.yunwei.baidu.BaiduTrack;
import com.yunwei.baidu.BaiduTrackListener;
import com.yunwei.baidu.HistoryTrackData;
import com.yunwei.library.utils.IStringUtils;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TrackPointsTable;
import com.yunwei.wetlandpark.greedao.TrackPointsTableDao;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/22 18:03
 * @description:
 */

public class HistoryDataRepo implements HistoryDataSource {

    private HistoryDataRepo() {
    }

    private static HistoryDataRepo INSTANCE;

    public static HistoryDataRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HistoryDataRepo();
        }
        return INSTANCE;
    }

    @Override
    public void queryBaiduService(int startTime, int endTime, GetDataCallBack<HistoryTrackData> getDataCallBack) {
        /*纠偏选项*/
        String processOption = "need_denoise=1,need_vacuate=1,need_mapmatch=1";
        BaiduTrack.getInstance().queryTrackHistory(ZNAPPlication.getInstance(), IStringUtils.toInt(BuildConfig.BAIDU_SERVICE_ID),
                "wt_park_" + ISpfUtil.getValue(Constants.USER_NAME_KEY, "").toString(), 0, 1, processOption, startTime,
                endTime, 5000, 1, new BaiduTrackListener() {
                    @Override
                    public void onQueryHistoryTrackSuccess(HistoryTrackData entity) {
                        getDataCallBack.onDataLoaded(entity);
                    }

                    @Override
                    public void onQueryHistoryTrackFailure(String str) {
                        getDataCallBack.onDataNotAvailable();
                    }

                    @Override
                    public void onQueryDistanceCallback(String str) {

                    }
                });
    }

    @Override
    public void queryTablePoints(String cacheID, QueryLocalCallBack<TrackPointsTable> queryLocalCallBack) {

    }

    @Override
    public void saveTrackPoints(TrackPointsTable trackPointsTable) {
        TrackPointsTableDao trackPointsTableDao = ZNAPPlication.getDaoSession().getTrackPointsTableDao();
        if (trackPointsTable.getId() != null) {
            trackPointsTableDao.update(trackPointsTable);
        } else {
            trackPointsTableDao.insert(trackPointsTable);
        }
    }

}
